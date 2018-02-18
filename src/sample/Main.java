package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    Stage mainWindow;


    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        mainWindow=primaryStage;

        GridPane layout=new GridPane();

        Label username=new Label("Username: ");
        Label password=new Label("Password: ");

        TextField usernameText=new javafx.scene.control.TextField();
        usernameText.setPromptText("username");

        PasswordField passwordText=new PasswordField();
        passwordText.setPromptText("password");

        GridPane.setConstraints(username,0,0);
        GridPane.setConstraints(usernameText,1,0);
        GridPane.setConstraints(password,0,1);
        GridPane.setConstraints(passwordText,1,1);


        File f = new File("test.db");
        if(!f.exists() && !f.isDirectory()) {
            Controller.createDataBase();
        }

        Button bt1=new Button("log in");
        bt1.setOnAction(e->{

        Boolean ret=Controller.login(usernameText.getText(),passwordText.getText());

        if(ret) display(mainWindow);

        if(!ret)    System.out.println("No user found");

        });


        Button create=new Button("Create new user");


        GridPane.setConstraints(bt1,0,3);
        GridPane.setConstraints(create,1,3);
        layout.getChildren().addAll(bt1,username,password,usernameText,passwordText,create);

        Scene scene=new Scene(layout);

        create.setOnAction(e->{
            GridPane createLayout=new GridPane();
            Label usr=new Label("Enter your username");
            Label pss=new Label("Enter your password");
            TextField createUserName=new TextField();
            PasswordField createPassword=new PasswordField();
            Button createButton=new Button("Create");

            GridPane.setConstraints(usr,0,0);
            GridPane.setConstraints(createUserName,1,0);
            GridPane.setConstraints(pss,0,1);
            GridPane.setConstraints(createPassword,1,1);
            GridPane.setConstraints(createButton,1,2);
            createLayout.getChildren().addAll(usr,pss,createUserName,createPassword,createButton);

            Scene secondScene=new Scene(createLayout);
            mainWindow.setScene(secondScene);

            createButton.setOnAction(d->{
                Controller.CreateNewUser(createUserName.getText(),createPassword.getText());
                mainWindow.setScene(scene);
            });

        });

        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void display(Stage stage){
        StackPane layout=new StackPane();
        Image img = new Image("ttt.png");
        ImageView imgView = new ImageView(img);
        layout.getChildren().add(imgView);
        Scene scene=new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
