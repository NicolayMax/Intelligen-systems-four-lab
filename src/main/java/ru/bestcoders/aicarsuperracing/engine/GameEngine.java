package ru.bestcoders.aicarsuperracing.engine;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.ai.AIEngine;
import ru.bestcoders.aicarsuperracing.ai.AIPlay;
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
    private AIPlay aip;

    private Car thirdCar;
    private boolean paused;

    public GameEngine() {
        paused = false;

        root = new Pane();
        levelMap = new LevelMap();
        levelMap.drawTiles(root);
        levelMap.drawCheckpoints(root);
        WIDTH = levelMap.map[0].length*64;
        HEIGHT = levelMap.map.length*64;

        root.setPrefSize(WIDTH, HEIGHT);
        gameObjects = new ArrayList<>();

        thirdCar = new Car(3,5, Car.Direction.RIGHT, levelMap);
        //levelMap.placeCar(secondCar);
        levelMap.placeCar(thirdCar);
        //root.getChildren().add(secondCar);
        root.getChildren().add(thirdCar);

        aie = new AIEngine(levelMap);
        aie.play();
        //aip = new AIPlay(levelMap);
    }

    public void init() {
        running = true;
        System.out.println("Init game objects");
    }

    public void startLearning(){
        aie.init();
        aie.play(/*secondCar,*/thirdCar);
    }
    public void pauseLearning() {
        if (!paused){
            aie.pause(1);       //number - количество обучаеммых машин
            paused = true;
        }
        else{
            aie.resume(1);  //number - количество обучаеммых машин
            paused = false;
        }
    }
    public void predictedRun(){                //работает только для одной машины,
        //aie.stop(1);        //number - количество обучаеммых машин

        /* код который сбрасывает levelmap и расставляет машинки заново
        ...
        ...
        ...
        */
        thirdCar.setPosX(3);
        thirdCar.setPosY(5);
        thirdCar.setDirection(Car.Direction.RIGHT);

        aip = new AIPlay(levelMap);
        aip.init();
        aip.play(/*secondCar,*/thirdCar);
    }

    public void handleInput(HashSet<KeyCode> keySet, HashSet<KeyCode> prevKeys) {
        if (keySet.contains(KeyCode.ESCAPE)) {
            cleanup();
            running = false;
        }
        //if (keySet.contains(KeyCode.ENTER)) {
        //   aie.init();
        //    aie.play(/*secondCar,*/thirdCar);
        //}
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
