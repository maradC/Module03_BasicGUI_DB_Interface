package com.example.module03_basicgui_db_interface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;


public class DB_Application extends Application {

    public static void main(String[] args) {
        launch();
    }


    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showScene1();

    }

    private void showScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            Scene scene = new Scene(root, 850, 560);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("db_interface_gui.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            currentScene.getStylesheets().add("style.css");
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {


                Scene newScene = new Scene(newRoot, 850, 560);
                primaryStage.setScene(newScene);

            });

            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu dbMenu = new Menu("DB");

        MenuItem addItem = new MenuItem("Add Person");
        MenuItem editItem = new MenuItem("Edit Person");
        MenuItem DeleteItem = new MenuItem("Delete Person");
        MenuItem UploadPictureItem = new MenuItem("Upload Picture");

            addItem.setOnAction(e -> openAddForm());
            editItem.setOnAction(e -> openEditForm());
            DeleteItem.setOnAction(e -> handleDelete());
            UploadPictureItem.setOnAction(e -> uploadProfilePicture());

            // Add items to Database Menu
            dbMenu.getItems().addAll(addItem, editItem, DeleteItem, UploadPictureItem);

        }
        private void openAddForm() {
            System.out.println("Open Add Form");

        }

        // Placeholder method to open the "Edit Person" form
        private void openEditForm () {
            System.out.println("Open Edit Form");

        }

        private void handleDelete(){
            System.out.println("Delete Person");
        }

    // Upload profile picture method
    private void uploadProfilePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            // Assuming you have a Person object, set the profile picture
            Person person = new Person();
            person.setProfilePicture(selectedFile.getAbsolutePath());
            System.out.println("Profile picture set to: " + person.getProfilePicture());
        }
    }
}