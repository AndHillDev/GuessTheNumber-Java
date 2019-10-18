package com.germandolllover.guessthenumber;
	
import com.germandolllover.guessthenumber.utils.AppData;
import com.germandolllover.guessthenumber.utils.Resources;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	public static AppData appData = new AppData("Guess The Number (Javaedition)","1.0.0",640,480,false,false,false);
	Resources resources = new Resources();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,appData.getAppWidth(),appData.getAppHeight());
			scene.getStylesheets().add(getClass().getResource(resources.getCssFile()).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(getClass().getResource(resources.getIconFile()).toExternalForm()));
			primaryStage.setTitle(appData.getAppName()+" V"+appData.getAppVersion());
			primaryStage.setFullScreen(appData.isFullScreen());
			primaryStage.setMaximized(appData.isMaximized());
			primaryStage.setResizable(appData.isResizeable());
			primaryStage.setOnCloseRequest((ae) -> {
	            Platform.exit();
	            System.exit(0);
	        });
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
