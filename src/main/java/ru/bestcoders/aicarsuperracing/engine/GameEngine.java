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
    private HashSet<KeyCode> prevKeys = new HashSet<>();

    Car car;

    public GameEngine() {
        root = new Pane();
        root.setPrefSize(WIDTH, HIGHT);
        gameObjects = new ArrayList<>();
        levelMap = new LevelMap();
        levelMap.drawTiles(root);

        // example
        car = new Car(1,0, Car.Direction.DOWN, levelMap);
        Car secondCar = new Car(2,2, Car.Direction.LEFT, levelMap);
        Car thirdCar = new Car(5,2, Car.Direction.RIGHT, levelMap);
        levelMap.placeCar(car);
        levelMap.placeCar(secondCar);
        levelMap.placeCar(thirdCar);
        root.getChildren().add(car);
        root.getChildren().add(secondCar);
        root.getChildren().add(thirdCar);
    }

    public void init() {
        running = true;
        System.out.println("Init game objects");
    }

    public void handleInput(HashSet<KeyCode> keySet, HashSet<KeyCode> prevKeys) {
        if (keySet.contains(KeyCode.ESCAPE)) {
            cleanup();
            running = false;
        }
        if (keySet.contains(KeyCode.UP) && !prevKeys.contains(KeyCode.UP)) {
            car.move();
        }
        if (keySet.contains(KeyCode.LEFT) && !prevKeys.contains(KeyCode.LEFT)) {
            car.rotateLeft();
        }
        if (keySet.contains(KeyCode.RIGHT) && !prevKeys.contains(KeyCode.RIGHT)) {
            car.rotateRight();
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
