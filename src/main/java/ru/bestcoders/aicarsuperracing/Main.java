package ru.bestcoders.aicarsuperracing;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ru.bestcoders.aicarsuperracing.ai.AIEngine;
import ru.bestcoders.aicarsuperracing.engine.GameEngine;

import java.util.HashSet;

public class Main extends Application {

    private GameEngine game;
    private HashSet<KeyCode> keySet;
    private HashSet<KeyCode> prevKeys;
    private AIEngine aie;

    // Init resources here
    public void startGame() {
        keySet = new HashSet<>();
        prevKeys = new HashSet<>();

        game = new GameEngine();
        game.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        startGame();

        primaryStage.setTitle("Ai Super Racing Game 4000");
        primaryStage.setScene(new Scene(game.getPane()));
        primaryStage.show();

        // Add keyboard listeners
        primaryStage.getScene().setOnKeyPressed(event -> {
            keySet.add(event.getCode());
        });

        primaryStage.getScene().setOnKeyReleased(event -> {
            keySet.remove(event.getCode());
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
