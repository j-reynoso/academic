# Jasmin Reynoso
# Feb 08 2022
# eliza.py
# This program acts as a psychotherapist for the user by providing
# basic responses according to what the user has typed to the program.
# Examples:
# user:"I feel sad" eliza:"WHY DO YOU FEEL SAD?"
# user:"I miss my mom" eliza:"ARE YOU CLOSE TO YOUR MOM?"
# user:"My dad died" eliza:"I AM SO SORRY FOR YOUR LOSS."
# user:"She is so rude" eliza:"ARE YOU SURE SHE IS SO RUDE?"
# In order to appropriately respond, eliza will assess the user
# input, and if certain keywords are detected, she will flip the
# pronouns or verbs if needed and add statements, often questioning,
# and add the original user input back with the appropriate adjustments.
# In some cases, she simply provides a response asking for clarification,
# or encourages the user to delve deeper into their thoughts. She may also
# just provide support emotionally, such as if there was a death or if she
# detects an apologetic tone from her user.
# Usage instructions:
# Run from terminal
# python3 eliza.py

import re
import random

# global variables
active_session = 0
name = "undeclared"
help_dict = {
    "my": "YOUR ",
    "I": "YOU ",
    "I'm": "YOU ARE ",
    "me": " YOU",
    "am": " ARE ",
    "was": "WERE ",
    "myself": "YOURSELF"
}


def prompt():
    if active_session > 0:
        take_input()
    elif active_session == 0:
        introduction()


def prompt_for_name():
    global name
    name_prompts = ["IN ORDER TO HELP, I'D LIKE TO CALL YOU BY YOUR NAME",
                    "PLEASE GIVE ME YOUR NAME, SO I CAN BETTER HELP YOU.",
                    "WHO ARE YOU?", "DO YOU MIND TELLING ME WHO YOU ARE?",
                    "WHAT SHOULD I CALL YOU?", "WHAT IS YOUR NAME?"]

    print(random.choice(name_prompts))
    name_response = input()
    possible_name_preface = [re.compile(".*[I|i]'?m"), re.compile(".*[I|i] am"), re.compile(".*[C|c]all me"),
                             re.compile(".*[M|m]y name is"), re.compile(".*[M|m]y name'?s")]
    for regex in possible_name_preface:
        if regex.match(name_response):
            sentence_parts = re.split(regex, name_response)
            name = sentence_parts[1].strip().upper()
            print("HELLO " + name + ", WHAT IS THE ISSUE TODAY?")
            prompt()

    if (name == "undeclared"):
        prompt_for_name()


def introduction():
    global active_session
    print("HELLO, I AM ELIZA, YOUR THERAPIST TODAY.")
    active_session = 1
    prompt_for_name()


def take_input():
    patient_response = input()
    keyword_detection(patient_response)


def keyword_detection(patient_response):
    patient_response = patient_response.upper()
    if re.search(r"(EXIT)|(GOOD)?BYE", patient_response):
        print("GOODBYE, HAVE A LOVELY DAY.")
        quit()
    responses_created = []

    # keywords detecting mention of family
    family_nouns = [re.compile("MY MOM"), re.compile("MY DAD"), re.compile("MY MOTHER"),
                    re.compile("MY FATHER"), re.compile("MY BROTHER"), re.compile("MY SISTER"),
                    re.compile("MY GRAND[PM]A"), re.compile("MY UNCLE"), re.compile("MY AUNT"),
                    re.compile("MY COUSIN"), re.compile("MY FAMILY"), re.compile("MY BOYFRIEND"),
                    re.compile("MY GIRLFRIEND"), re.compile("MY HUSBAND"), re.compile("MY WIFE")]
    family_responses = ["TELL ME MORE ABOUT YOUR RELATIONSHIP WITH FNOUN.", "DO YOU THINK ABOUT FNOUN OFTEN?",
                        "WHAT DO YOU THINK ABOUT FNOUN?", "HOW DOES FNOUN MAKE YOU FEEL?", "ARE YOU CLOSE TO FNOUN?"]

    # keywords detecting special verbs such as want, wish, crave, etc
    special_verbs = [re.compile("I WANT"), re.compile("I WISH"), re.compile("I CRAVE"),
                     re.compile("I DESIRE"), re.compile("I MISS"), re.compile("I HATE"),
                     re.compile("I FEEL"), re.compile("I BRING")
                     ]
    special_responses = ["TELL ME MORE ABOUT WHY SVERB", "HOW LONG HAVE SVERB?",
                         "WHY DO YOU THINK SVERB?", "LET'S TALK ABOUT WHY SVERB.",
                         "WHY DO SVERB?", "WHEN DID YOU FIRST REALIZE SVERB?"]

    # keywords detecting mention of other people
    generic_statements = [re.compile('\\bSHE\\b'), re.compile('\\bHE\\b'),
                          re.compile('\\bTHEY\\b')]
    generic_responses = ["HOW DOES IT MAKE YOU FEEL THAT STMT?", "ARE YOU SURE STMT?",
                         "SO WHAT DOES IT MEAN IF STMT?", "DO YOU THINK IT IS INTERESTING THAT STMT?",
                         "DO YOU THINK IT IS NORMAL THAT STMT?", "WHY DO YOU THINK STMT?"]

    # keywords detecting indefinite pronouns such as everyone, anybody, etc
    indefinite_pronouns = [re.compile("EVERYONE"),
                           re.compile("ANYBODY"),
                           re.compile("EVERYBODY"),
                           re.compile("ANYONE")]
    indefinite_responses = ["WHO DO YOU REALLY MEAN BY PRONOUN?", "I AM SURE THAT'S NOT TRUE FOR PRONOUN.",
                            "TELL ME WHO YOU REALLY MEAN WHEN YOU SAY PRONOUN."]

    # keyword detecting I am --- statements
    iam_statement = re.compile("I AM")

    you_responses = ["TELL ME MORE ABOUT THAT."]

    # keyword detecting mentioning of death
    dead_keyword = [re.compile("IS DEAD"), re.compile("HAS DIED")]
    dead_responses = ["I AM SO SORRY FOR YOUR LOSS", "MY CONDOLENCES. I HOPE YOU FIND RELIEF SOON."]

    # keyword detecting apologetic tone in user
    sorry_keywords = [re.compile("(I AM).*SORRY"), re.compile("I APOLOGIZE")]
    sorry_responses = ["PLEASE DO NOT APOLOGIZE, YOU ARE IN  A SAFE SPACE.",
                       "DO NOT WORRY THAT I AM JUDGING YOU, I AM HERE TO HELP."]

    # keyword detecting question response
    answered_questions = [re.compile("^NO"), re.compile("^YES\s"), re.compile("^NOT AT ALL"),
                          re.compile("^ABSOLUTELY"), re.compile("^ABSOLUTELY NOT")]
    answered_responses = ["TELL ME, HAVE YOU ALWAYS BEEN SO QUICK TO CONCLUDE?",
                          "YOU SEEM TO BE VERY QUICK TO ASSUME.",
                          "I WANT YOU TO REALLY THINK ABOUT YOUR THOUGHT PROCESS THERE."]

    # keyword detecting user referring to eliza
    you_pronouns = re.compile("YOUR?")
    you_responses = ["WE ARE TALKING ABOUT YOU, NOT ME.",
                     "YOU ARE THE PATIENT, LET'S TALK ABOUT YOU.",
                     "THIS IS NOT ABOUT ME."]

    no_keyword_responses = ["INTERESTING, LET'S MOVE ONTO SOMETHING ELSE NOW.", "I AM NOT SURE I UNDERSTAND,"
                                                                                " CAN YOU SAY IT DIFFERENTLY?",
                            "CAN YOU TRY TO SAY THAT A DIFFERENT WAY?", "WHAT DOES"
                                                                        " THAT SUGGEST?",
                            "SO WHAT DOES THAT MEAN TO YOU?", "WHY DO YOU SAY THAT?",
                            "WOULD YOU SAY THAT YOU HAVE PSYCHOLOGICAL PROBLEMS?"]

    info_responses = ["DOES THAT SAY ANYTHING ABOUT YOU?", "HOW DOES THAT MAKE YOU FEEL?",
                      "DOES THAT AFFECT YOUR LIFE AT ALL?", "HOW DOES THAT IMPACT YOU?",
                      "IS THAT A NORMAL THING FOR SOMEONE TO SAY?", "THAT IS INTERESTING."]

    # searching user input to find keywords, if found, makes appropriate adjustment
    # to original statements depending on keyword and concatenates to response statement,
    # or provides response statement without adjustment
    if you_pronouns.search(patient_response):
        # if regex found, add generated response to list
        responses_created.append(random.choice(you_responses))
    elif iam_statement.search(patient_response):
        split_sentence = re.split(iam_statement, patient_response)
        valuable = split_sentence[1]
        responses_created.append("WHY ARE YOU" + split_sentence[1] + "?")
    for sregex in sorry_keywords:
        if sregex.search(patient_response):
            responses_created.append(random.choice(sorry_responses))
    for qregex in answered_questions:
        if qregex.search(patient_response):
            responses_created.append(random.choice(answered_responses))
    for dregex in dead_keyword:
        if dregex.search(patient_response):
            responses_created.append(random.choice(dead_responses))
    for fregex in family_nouns:
        if fregex.search(patient_response):
            token = re.findall(fregex, patient_response)
            token[0] = check_for_pronouns(token[0])
            responses_created.append(random.choice(family_responses).replace("FNOUN", token[0]))
        #    print(random.choice(family_responses).replace("FNOUN", token[0]))
    for svregex in special_verbs:
        if svregex.search(patient_response):
            token = re.findall(svregex, patient_response)
            deconstructed_sentence = re.split(svregex, patient_response)
            token[0] = check_for_pronouns(token[0])
            # print(token)
            deconstructed_sentence[1] = check_for_pronouns(deconstructed_sentence[1])
            # print(deconstructed_sentence[1])
            responding = random.choice(special_responses)
            tense = check_tense(responding)
            if tense == 0:
                post = ''
            elif tense == 1:
                if token[0][-1] == 'E':
                    post = 'D'
                elif 'FEEL' in token[0]:
                    token[0] = token[0].replace("FEEL", "FELT")
                    post = ''
                else:
                    post = 'ED'
            special_response = token[0] + post + deconstructed_sentence[1]
            responses_created.append(responding.replace("SVERB", special_response))
        #   print(responding.replace("SVERB", special_response))
    for gsregex in generic_statements:
        if gsregex.search(patient_response):
            token = re.findall(gsregex, patient_response)
            #   print(token)
            deconstructed_sentence = re.split(gsregex, patient_response)
            #   print(deconstructed_sentence)
            deconstructed_sentence[1] = check_for_pronouns(deconstructed_sentence[1])
            # deconstructed_sentence[1] = check_for_verbs(deconstructed_sentence[1])
            responding = random.choice(generic_responses)
            generic_response = token[0] + deconstructed_sentence[1]
            responses_created.append(responding.replace("STMT", generic_response))
        #   print(responding.replace("STMT", generic_response))
    for ipregex in indefinite_pronouns:
        if ipregex.search(patient_response):
            token = re.findall(ipregex, patient_response)
            responding = random.choice(indefinite_responses)
            responses_created.append(responding.replace("PRONOUN", token[0]))
        #   print(responding.replace("PRONOUN", token[0]))

    if len(responses_created) == 0:
        # if no keywords found above, check for I pronoun to flip statement
        if re.search(r'\bI\b', patient_response):
            patient_response = check_for_pronouns(patient_response)
            patient_response = check_for_verbs(patient_response)
            print(patient_response + "? " + random.choice(info_responses))
        else:
            # or else output vague/clarifying response
            print(random.choice(no_keyword_responses))
    else:
        # if regex was found and statement to respond with have been generated, print random one from list
        print(random.choice(responses_created))
    # go back to user input
    prompt()


# checks if statement being output is in past tense to change tense of verb
def check_tense(text):
    if text.startswith("HOW LONG HAVE"):
        return 1
    else:
        return 0


# checks for pronouns to flip
def check_for_pronouns(text):
    if re.search(r'\bMY\b', text):
        text = re.sub(r'\bMY\b', help_dict["my"], text)
    if re.search(r'\bI\b', text):
        text = re.sub(r'\bI\b', help_dict["I"], text)
    if re.search(r'\bIM\b', text):
        text = re.sub(r'\bI\b', help_dict["IM"], text)
    if re.search(r'\bME\b', text):
        text = re.sub(r'\bME\b', help_dict["me"], text)
    if re.search(r'\bMYSELF\b', text):
        text = re.sub(r'\bMYSELF\b', help_dict["myself"], text)
    return text


# checks for verbs to flip
def check_for_verbs(text):
    if re.search(r'\bAM\b', text):
        text = re.sub(r'\bAM\b', help_dict["am"], text)
    if re.search(r'\bWAS\b', text):
        text = re.sub(r'\bWAS\b', help_dict["was"], text)
    return text


prompt()
