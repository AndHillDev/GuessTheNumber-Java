package com.germandolllover.guessthenumber;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.controlsfx.control.StatusBar;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;

public class MainController {
	// Define Formular components
	@FXML BorderPane borderpane;
	@FXML Button newgame;
	@FXML Button okbutton;
	@FXML AnchorPane gamepanel;
	@FXML RadioMenuItem easy;
	@FXML RadioMenuItem medium;
	@FXML RadioMenuItem hard;
	@FXML Text thequestion;
	@FXML TextField guessedit;
	@FXML ProgressBar progress;
	@FXML Text attemptsDisplay;
	@FXML ToggleGroup difficulty;
	
	// Private variables
	private StatusBar statusbar;
	private Text date;
	private Text time;
	private Separator sepi1;
	private Separator sepi2;
	private Separator sepi3;
	
	// Public variables
	public static Timer timer1 = new Timer();
	public SimpleDateFormat dFormat = new SimpleDateFormat("dd.MM.YYYY");
	public SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm:ss");
	public static Random random;
	public static int number;
	public static int attempts;
	public static int multiplier;
	
	// Initialisation events
	public void initialize() {
		// Create new Statusbar
		statusbar = new StatusBar();
		// Create a random
		random = new Random();
		// Init number with 0 at the beginning
		number = 0;
		// Init attempts with 10 at the beginning
		attempts = 10;
		// Set gamepanel to unvisible
		gamepanel.setVisible(false);
		// Set Progress To 100%
		progress.setProgress(1);
		// Create new Separator to the Statusbar
		sepi1 = new Separator(Orientation.VERTICAL);
		// Set padding for the seperator
		sepi1.setPadding(new Insets(5,5,5,5));
		// Create new Separator to the Statusbar
		sepi2 = new Separator(Orientation.VERTICAL);
		// Set padding for the seperator
		sepi2.setPadding(new Insets(5,5,5,5));
		// Create new Separator to the Statusbar
		sepi3 = new Separator(Orientation.VERTICAL);
		// Set padding for the seperator
		sepi3.setPadding(new Insets(5,5,5,5));
		// Create Text date
		date = new Text("00.00.0000");
		// Create Text time
		time = new Text("00:00:00");
		// Set default statusbar text to nothing
		statusbar.setText("");
		// Add Copyright info to the Statusbar
		statusbar.getLeftItems().add(new Text("Copyright © 2019 GermanDollLover"));
		// Add Separator to the Statusbar
		statusbar.getLeftItems().add(sepi1);
		// Add Date to the Statusbar
		statusbar.getLeftItems().add(date);
		// Add Separator to the Statusbar
		statusbar.getLeftItems().add(sepi2);
		// Add Time to the Statusbar
		statusbar.getLeftItems().add(time);
		// Add Separator to the Statusbar
		statusbar.getLeftItems().add(sepi3);
		// Add Statusbar to the bottom panel of the borderpane
		borderpane.bottomProperty().set(statusbar);
		
		// Setting time and date
		date.setText(dFormat.format(new Date()));
		time.setText(tFormat.format(new Date()));
		
		timer1.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
	
				// Setting the new time and date every second
				date.setText(dFormat.format(new Date()));
				time.setText(tFormat.format(new Date()));
				
			}
			
		}, 100, 100);
		
	}
	
	// Asking user if the app should be terminated
	public void doExit() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application?");
		alert.setHeaderText(null);
		alert.initStyle(StageStyle.UTILITY);
		alert.setContentText("Are you sure that you want to exit "+Main.appData.getAppName()+" V"+Main.appData.getAppVersion()+"?");
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && result.get() == ButtonType.OK) {
			timer1.cancel();
			Platform.exit();
			System.exit(0);
		}
		
	}
	
	// Create new Game
	public void doNewGame() {
			
		if(easy.isSelected()) {	
			number = random.nextInt(100);
			thequestion.setText("Can you guess the number between 0 and 100 which is randomized generated? You have 10 attempts!");
		} else if (medium.isSelected()) {
			number = random.nextInt(250);
			thequestion.setText("Can you guess the number between 0 and 250 which is randomized generated? You have 10 attempts!");
		} else {
			number = random.nextInt(500);
			thequestion.setText("Can you guess the number between 0 and 500 which is randomized generated? You have 10 attempts!");
		}
		
		multiplier = random.nextInt(9999);
		attempts=10;
		attemptsDisplay.setText(attempts+" attempts left");
		progress.setProgress(1);
		gamepanel.setVisible(true);
		guessedit.setDisable(false);
		guessedit.requestFocus();
		
	}

	// Enter the guess and check if is right or wrong
	public void doEnterGuess() {
		
		String regex = "[0-9]+";
		
		if (guessedit.getText().matches(regex)) {
			int guess = Integer.parseInt(guessedit.getText());	
			
			if (attempts >= 1) {
				
				if(guess == number) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Right");
					alert.setHeaderText(null);
					alert.setContentText("You guessed right! Your score is: "+(multiplier*attempts));
					alert.showAndWait();
					guessedit.clear();
					gamepanel.setVisible(false);
				} else if (guess > number) {
					// You guessed wrong, the number is smaller
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wrong");
					alert.setHeaderText(null);
					alert.setContentText("You guessed wrong, the number is smaller");
					alert.showAndWait();
					progress.setProgress(progress.getProgress() - 0.1);
					attempts = attempts - 1;
					attemptsDisplay.setText(attempts+" attempts left");
				} else if (guess < number) {
					// You guessed wrong, the number is bigger
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Wrong");
					alert.setHeaderText(null);
					alert.setContentText("You guessed wrong, the number is bigger");
					alert.showAndWait();
					progress.setProgress(progress.getProgress() - 0.1);
					attempts = attempts - 1;
					attemptsDisplay.setText(attempts+" attempts left");
				} else {
					// Unknown error
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("An unknown error has occurred.");
					alert.showAndWait();
				}
				
				guessedit.requestFocus();
				guessedit.clear();
				if (attempts <= 0) {
					guessedit.setDisable(true);
					okbutton.setDisable(true);
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("You used all your attempts.");
					alert.showAndWait();
					gamepanel.setVisible(false);
				}
				
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No attempts");
				alert.setHeaderText(null);
				alert.setContentText("You have no attempts left");
				alert.showAndWait();
				guessedit.setDisable(true);
				okbutton.setDisable(true);
			}
			
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No numbers");
			alert.setHeaderText(null);
			alert.setContentText("What you entered was not a number! Only numbers are allowed!");
			alert.showAndWait();
			guessedit.clear();
			guessedit.requestFocus();
		}
	}
	
	// Show Homepage
	public void showHomepage() {
		openUrl("https://www.germandolllover.com");
	}
	
	// Show program informations
	public void showProgramInfo() {
		
		String programInfo;
		programInfo = "Programmed and designed by Andreas Hiller"+"\n";
		programInfo += "Copyright © 2019 GermanDollLover"+"\n";
		programInfo += "All rights reserved"+"\n\n";
		programInfo += "WWW: https://www.germandolllover.com";
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Program Information");
		alert.setHeaderText(Main.appData.getAppName()+" V"+Main.appData.getAppVersion());
		alert.setContentText(programInfo);
		alert.showAndWait();
	}
	
	// Open a URL in the default web browser
	public void openUrl(String uri) {
		 Desktop d=Desktop.getDesktop();
		 try {
			d.browse(new URI(uri));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	// Check for Updates
	public void checkForUpdates() {
		
	}
	
	public void cancelGame() {
		gamepanel.setVisible(false);
	}
 }

