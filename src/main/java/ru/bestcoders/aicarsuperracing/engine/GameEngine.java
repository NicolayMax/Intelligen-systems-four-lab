package ru.bestcoders.aicarsuperracing.engine;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.entities.GameObject;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameEngine {

    private final int WIDTH = 800;
    private final int HIGHT = 600;
    private boolean running;

    private Pane root;
    private List<GameObject> gameObjects;
    private LevelMap levelMap;
    // example
    private GameObject car = new Car(64,64);

    public GameEngine() {
        root = new Pane();
        root.setPrefSize(WIDTH, HIGHT);
        gameObjects = new ArrayList<>();
        levelMap = new LevelMap();
        levelMap.drawTiles(root);

        // example
        car.setRotate(-90.0);
        root.getChildren().add(car);
    }

    public void init() {
        running = true;
        System.out.println("Init game objects");
    }

    public void handleInput(HashSet<KeyCode> keySet) {
        if (keySet.contains(KeyCode.ESCAPE)) {
            cleanup();
            running = false;
        }
        if (keySet.contains(KeyCode.UP)) {
            // example
            car.setTranslateX(car.getTranslateX() + 2);
        }
        if (keySet.contains(KeyCode.DOWN)) {
            // example
            car.setTranslateX(car.getTranslateX() - 2);
        }
    }

    public void render() {
        // Render objects
    }

    public void cleanup() {
        // Clean data
    }

    public Parent getPane() {
        return root;
    }

    public boolean isRunning() {
        return running;
    }
}
