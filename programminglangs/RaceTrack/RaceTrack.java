// Jasmin Reynoso
// Assignment 8 RaceTrack.java
// CMSC 403
//
//

//package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class RaceTrack extends Application {
	
	final public String filePath = "/Users/jasminreynoso/Desktop/ass8pra/sportive-car.png";
	public static CarThread c1;
	public static CarThread c2;
	public static CarThread c3;
	Rectangle laneOne;
	Rectangle laneTwo;
	Rectangle laneThree;
	Button startBtn;
	Button pauseBtn;
	Button resetBtn;
	Image ci1;
    Image ci2;
    Image ci3;
	ImageView ci1iv;  
    ImageView ci2iv;  
    ImageView ci3iv;   
	boolean paused;
	boolean started;


	@Override
	public void start(Stage primaryStage) throws FileNotFoundException{
		paused = false; 
		started = false; //sets default values when race is not ongoing

		setRacetrack(); 
        Group root = new Group(ci1iv, ci2iv, ci3iv, laneOne, laneTwo, laneThree, startBtn, pauseBtn, resetBtn);
		ci1iv.toFront();
		ci2iv.toFront();
		ci3iv.toFront();//ensures cars are in front of lanes for gui
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
        	
			@Override
			public void handle(ActionEvent event) {
				startBtn();
				}
        });
        
        pauseBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				pauseBtn();
			}
				
        });
        resetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
				resetBtn();
				} catch (FileNotFoundException f) {

				}

			}
        });

        Scene scene = new Scene(root, 500, 200, Color.GRAY);
        primaryStage.setScene(scene);
		scene.setFill(Color.WHITE);
		primaryStage.setResizable(false);
        primaryStage.setTitle("Richmond Raceway");
        primaryStage.show();  
	}
        
   /* name: setRacetrack
	does: places the cars, sets start, pause, and reset button, and sets
	rectangles to be placed in gui window
	*/
	public void setRacetrack( ) throws FileNotFoundException {

        startBtn = new Button();
        pauseBtn = new Button();
        resetBtn = new Button(); //creates buttons for gui
        
        c1 = new CarThread(1);
        c2 = new CarThread(2);
        c3 = new CarThread(3); //creates thread for each car
        
		laneOne = new Rectangle();
		laneTwo = new Rectangle();
		laneThree = new Rectangle(); //creates rectangles for lane on track

		ci1 = new Image(new FileInputStream(filePath));  
        ci2 = new Image(new FileInputStream(filePath));  
        ci3 = new Image(new FileInputStream(filePath));  //getting image of car
        ci1iv = new ImageView(ci1);  
        ci2iv = new ImageView(ci2);  
        ci3iv = new ImageView(ci3);   

		setCar(ci1iv, 0, 0);
        setCar(ci2iv, 0, 50);
        setCar(ci3iv, 0, 100); //sets starting locations of cars

		startBtn.setText("Start");
		startBtn.setLayoutX(120);
		startBtn.setLayoutY(15);

		pauseBtn.setText("Pause");
		pauseBtn.setLayoutX(220);
		pauseBtn.setLayoutY(15);

		resetBtn.setText("Reset");
		resetBtn.setLayoutX(320);
		resetBtn.setLayoutY(15); //sets location and text of buttons

		laneOne.setX(62.0); 
		laneOne.setY(53.0); 
		laneOne.setWidth(370.0); 
		laneOne.setHeight(15.0);

		laneTwo.setX(62.0); 
		laneTwo.setY(103.0); 
		laneTwo.setWidth(370.0); 
		laneTwo.setHeight(15.0);

		laneThree.setX(62.0); 
		laneThree.setY(153.0); 
		laneThree.setWidth(370.0); 
		laneThree.setHeight(15.0); //sets dimensions for lanes

		laneOne.setFill(Color.GRAY);
		laneTwo.setFill(Color.GRAY);
		laneThree.setFill(Color.GRAY); 
	}

    /* name: setCar
	parameters: ImageView iv representing car image, int xCoord representing 
	horizontal distance change in pixels, int yCoord representing vertical
	distance change in pixels
	does: moves the car along the lane
	*/
	public void setCar(ImageView  iv, int xCoord, int yCoord) {
		iv.setX(20 + xCoord); 
        iv.setY(40 + yCoord); 
        iv.setFitHeight(40);
        iv.setFitWidth(60); 
        iv.setPreserveRatio(true); 
	}



	/* name: startBtn
	does: checks if race has not started, if true, starts all threads
	to begin race, and sets started to true if the race has been paused,
	notify all threads to resume.
	*/
	public void startBtn() {
		if(!started && !c1.isAlive()) {
			c1.start();
			c2.start();
			c3.start();
			started = true;
			} else if(paused && started) {
					paused = false;
				} else if(!started && paused) {
					paused = false;
				}
	}
	
	/* name: pauseBtn
	does: checks if race has been started, if it has, calls on all car
	threads to wait and sets paused to true
	*/
	public void pauseBtn() {
			paused = true;
	}



	/* name: resetBtn
	does: sets car images back to original position, calls on pause to ensure threads
	are not running, sets started and won back to original false values, sets cars
	distances travelled back to 20pixels
	*/
	public void resetBtn() throws FileNotFoundException {
		setCar(ci1iv, 0, 0);
        setCar(ci2iv, 0, 50);
        setCar(ci3iv, 0, 100); //sets starting locations of cars
		pauseBtn();
		started = false;
		c1.distanceTravelled = 20;
		c2.distanceTravelled = 20;
		c3.distanceTravelled = 20;
		
	}



	/* name: alert
	parameters: String winner
	does: creates an information alert window notifying user of the
	car that won 
	*/
	public void alert(String winner) {
		Platform.runLater(() -> {
			Alert a = new Alert(AlertType.INFORMATION);
		a.setContentText("Car " + winner + " wins!");
		a.showAndWait(); });
			}




	public static void main(String[] args) {  
	    launch(args);  
	}  
	
	public class CarThread extends Thread {
		int car;
		String t;
		int distanceTravelled;
		int limit = 370; //limit of lanes
		Lock lock = new ReentrantLock();
		
		public CarThread(int num) {
			car = num;
			if(num == 1) {
				t = "one";

			} else if(num == 2) {
				t = "two";
			} else {
				t = "three";
			}
			distanceTravelled = 20;
		}
		
		@Override
		public void run() {
			Random random = new Random();
			int add = 0;
			while(true && lock.tryLock()) { 
				if(!paused) {
				lock.lock();
				add = random.nextInt(11);
				distanceTravelled += add;
				if(distanceTravelled >= limit) {
					pauseBtn();
					alert(t);
				}
					lock.unlock();
					Platform.runLater(() -> {
					if(car == 1) {
						setCar(ci1iv, distanceTravelled, 0);
					} else if(car == 2) {
						setCar(ci2iv, distanceTravelled, 50);

					} else if(car == 3) {
						setCar(ci3iv, distanceTravelled, 100);
					}
					});
				}
					try {
						sleep(50);
					} catch (InterruptedException e) {
				}
				}

		
}
}
}

	

