package com.demo.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml")); //用于加载fxml文档
        primaryStage.setTitle("Hello World");   //设置窗口标题
        primaryStage.setScene(new Scene(root, 820, 480));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
