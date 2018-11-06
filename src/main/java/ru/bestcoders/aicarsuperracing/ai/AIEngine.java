package ru.bestcoders.aicarsuperracing.ai;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

public class AIEngine {
    private Sequence s;
    private LevelMap levelMap;
    private boolean end;

    public AIEngine(LevelMap levelMap){
        this.levelMap = levelMap;
        s = new Sequence();
        end = false;
    }

    public void init(){
        s.loadRoutes();
    }

    public void play(Pane root, Car...cars) {
        /*while (!end){
            for (Car instanceOfCar : cars){
                if (levelMap.map[instanceOfCar.getPosY()][instanceOfCar.getPosX()]!=1) {
                    instanceOfCar.move();
                    //instanceOfCar.setLayoutX(instanceOfCar.getLayoutX() + 10);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
                else {
                    end=true;
                }
            }
        }*/
        ct = new CarThread[cars.length];
        t = new Thread[cars.length];

        for (int i=0;i<cars.length;i++) {
            ct[i] = new CarThread(cars[i], levelMap);
        }
        for (int i=0;i<cars.length;i++) {
            t[i]= new Thread(ct[i]);
        }
        for (int i=0;i<cars.length;i++) {
            t[i].start();
        }
    }
    private CarThread[]ct;
    private Thread[]t;
}
