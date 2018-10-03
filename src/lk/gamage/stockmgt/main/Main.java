

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Logger logger = Logger.getLogger("");
        FileHandler fileHandler = new FileHandler("Error.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/gamage/stockmgt/view/SplashScreen.fxml"));
        Scene temp = new Scene(parent);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        boolean add = primaryStage.getIcons().add(new Image("/lk/gamage/stockmgt/assest/Electrical_96px.png"));
        primaryStage.setScene(temp);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
