package ru.bestcoders.aicarsuperracing.engine;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.ai.AIEngine;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.entities.Construction;
import ru.bestcoders.aicarsuperracing.entities.GameObject;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameEngine {

    private int WIDTH;
    private int HEIGHT;
    private boolean running;

    private Pane root;
    private List<GameObject> gameObjects;
    private LevelMap levelMap;
    private HashSet<KeyCode> prevKeys = new HashSet<>();
    private AIEngine aie;

    private Car car;
    private Car secondCar;
    private Car thirdCar;

    public GameEngine() {
        root = new Pane();
        levelMap = new LevelMap();
        levelMap.drawTiles(root);
        levelMap.drawCheckpoints(root);
        WIDTH = levelMap.map[0].length*64;
        HEIGHT = levelMap.map.length*64;

        root.setPrefSize(WIDTH, HEIGHT);
        gameObjects = new ArrayList<>();

        // example
        car = new Car(1,0, Car.Direction.DOWN, levelMap);
        secondCar = new Car(19,2, Car.Direction.LEFT, levelMap);
        thirdCar = new Car(5,6, Car.Direction.UP, levelMap);

        levelMap.placeCar(car);
        levelMap.placeCar(secondCar);
        levelMap.placeCar(thirdCar);
        root.getChildren().add(car);
        root.getChildren().add(secondCar);
        root.getChildren().add(thirdCar);

        aie = new AIEngine(levelMap);

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
        if (keySet.contains(KeyCode.ENTER)) {
            aie.init();
            aie.play(secondCar, thirdCar);
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

    public void cleanup() {
        // Clean data
    }

    public Parent getPane() {
        return root;
    }

    public boolean isRunning() {
        return running;
    }

    public void mouseClick(int sceneX, int sceneY) {
        sceneX /= 64;
        sceneY /= 64;
        addObject(new Construction(sceneX, sceneY, levelMap));
    }

    public void addObject(GameObject object) {
        if (levelMap.setGameObject(object)) {
            gameObjects.add(object);
            root.getChildren().add(object);
        } else {
            levelMap.removeGameObject(object.getPosX(), object.getPosY());
            root.getChildren().remove(object);
            gameObjects.remove(object);
        }
    }
}
