package ru.bestcoders.aicarsuperracing;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.bestcoders.aicarsuperracing.ai.AIEngine;
import ru.bestcoders.aicarsuperracing.engine.GameEngine;
import ru.bestcoders.aicarsuperracing.utils.LogHandler;

import java.util.HashSet;
import java.util.logging.Logger;

public class Main extends Application {

    private GameEngine game;
    private HashSet<KeyCode> keySet;
    private HashSet<KeyCode> prevKeys;
    private AIEngine aie;

    private LogHandler logHandler;
    private Logger logger = Logger.getLogger("main");

    // Init resources here
    public void startGame() {
        keySet = new HashSet<>();
        prevKeys = new HashSet<>();

        game = new GameEngine();
        game.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //init logger
        logHandler = logHandler.getInstance();
        logger.addHandler(logHandler);
        logger.info("Application started");

        startGame();

        primaryStage.setTitle("Ai Super Racing Game 4000");
        primaryStage.setScene(new Scene(game.getPane()));
        primaryStage.show();

        //close
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        // Add keyboard listeners
        primaryStage.getScene().setOnKeyPressed(event -> {
            keySet.add(event.getCode());
        });

        primaryStage.getScene().setOnKeyReleased(event -> {
            keySet.remove(event.getCode());
        });

        primaryStage.getScene().setOnMouseClicked(event -> {
            game.mouseClick((int)event.getSceneX(), (int)event.getSceneY());
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.isRunning()) {
                    game.handleInput(keySet, prevKeys);
                    prevKeys.clear();
                    prevKeys.addAll(keySet);
                } else {
                    // Exit game
                    primaryStage.close();
                }
            }
        };
        timer.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    // Entry point
    public static void main(String[] args) {
        launch(args);
    }

}
