// Jasmin Reynoso
// Fall 2020 CMSC 257
/* $begin shellmain */
#include "csapp.h"
#define MAXARGS   128

/* Function prototypes */
void eval(char *cmdline);
int parseline(char *buf, char **argv);
int builtin_command(char **argv); 
void exit();
void pid();
void ppid();
void cd(char *argv);
void help();
void handle();
char *userPrompt = "my257sh";
int main(int argc, char *argv[]) 
{
    char cmdline[MAXLINE]; /* Command line */
    if(argc == 2) {
	printf("%s -p <prompt>\n", argv[0]);
	exit(0);
		  }
	else if(argc == 3)
	{
	if(strcmp(argv[1], "-p"))
	{
	printf("%s -p <prompt>\n", argv[0]);
	exit(0);
	}
	userPrompt = argv[2];
	}
	signal(SIGINT, handle);
    while (1) {
	/* Read */
	printf("%s> ", userPrompt);                   
	Fgets(cmdline, MAXLINE, stdin); 
	if (feof(stdin))
	    exit(0);

	/* Evaluate */
	eval(cmdline);
   } 
} 

/* $end shellmain */
  
/* $begin eval */
/* eval - Evaluate a command line */
void eval(char *cmdline) 
{
    char *argv[MAXARGS]; /* Argument list execv() */
    char buf[MAXLINE];   /* Holds modified command line */
    int bg;              /* Should the job run in bg or fg? */
    pid_t pid;           /* Process id */
    
    strcpy(buf, cmdline);
    bg = parseline(buf, argv); 
    if (argv[0] == NULL)  
	return;   /* Ignore empty lines */

    if (!builtin_command(argv)) { 
        if ((pid = Fork()) == 0) {   /* Child runs user job */
            if (execvp(argv[0], argv) < 0) {
                printf("%s: Command not found.\n", argv[0]);
                exit(0);
            }
        }

	/* Parent waits for foreground job to terminate */
	if (!bg) {
	    int status;
	    if (waitpid(pid, &status, 0) < 0)
		unix_error("waitfg: waitpid error");
		int exited = WEXITSTATUS(status);
		printf("Child exit status: %d\n", exited);
    }
    return;
}
}
/* If first arg is a builtin command, run it and return true */
int builtin_command(char **argv) 
{
    if (!strcmp(argv[0], "quit")) /* quit command */
	exit(0);  
    if (!strcmp(argv[0], "&"))    /* Ignore singleton & */
	return 1;
    if (!strcmp(argv[0], "pid"))
	{
	printf("%d \n", getpid());
	return 1;
	}
    if (!strcmp(argv[0], "ppid"))
        {
        printf("%d \n", getppid());
        return 1;
	}
    if(!strcmp(argv[0], "help"))
	{
	help();
	}
    if(!strcmp(argv[0], "exit"))
	{
	raise(SIGTERM);
    if(!strcmp(argv[0], "cd"))
	{
	if(argv[1] == NULL)
	{
	char current[MAXARGS];
	getcwd(current, sizeof(current));
	printf("Directory: %s\n", current);
	return 1;
	}
	else if(argv[1] != NULL)
	{
	chdir(argv[1]);
	return 1;	
	}
	}
   	}
    return 0;                     /* Not a builtin command */
}
/* $end eval */

/* $begin parseline */
/* parseline - Parse the command line and build the argv array */
int parseline(char *buf, char **argv) 
{
    char *delim;         /* Points to first space delimiter */
    int argc;            /* Number of args */
    int bg;              /* Background job? */

    buf[strlen(buf)-1] = ' ';  /* Replace trailing '\n' with space */
    while (*buf && (*buf == ' ')) /* Ignore leading spaces */
	buf++;

    /* Build the argv list */
    argc = 0;
    while ((delim = strchr(buf, ' '))) {
	argv[argc++] = buf;
	*delim = '\0';
	buf = delim + 1;
	while (*buf && (*buf == ' ')) /* Ignore spaces */
            buf++;
    }
    argv[argc] = NULL;
    
    if (argc == 0)  /* Ignore blank line */
	return 1;

    /* Should the job run in the background? */
    if ((bg = (*argv[argc-1] == '&')) != 0)
	argv[--argc] = NULL;

    return bg;
}
/* $end parseline */
/* $begin handle interrupt */
void handle(int signal)
{
printf("\n %s>", userPrompt);
fflush(stdout);
return;
}
/* $end handle interrupt */
/* $begin help */
void help() 
{
	printf("Developer Name:%s\n", "Jasmin Reynoso");
	printf("Built in commands: exit, pid, ppid, cd, help, ls, cd\n");
	printf("Refer to man for more info.\n");
}
/*$end help */
