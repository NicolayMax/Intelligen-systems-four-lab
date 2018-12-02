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

    public void play(Car...cars) {
        ct = new CarThread[cars.length];
        t = new Thread[cars.length];

        for (int i=0;i<cars.length;i++) {
            ct[i] = new CarThread(cars[i], levelMap, s.getSeq(i));
        }
        for (int i=0;i<cars.length;i++) {
            t[i]= new Thread(ct[i]);
        }
        for (int i=0;i<cars.length;i++) {
            t[i].start();
        }
    }
    public void pause(int number){
        for (int i=0;i<number;i++) {
            ct[i].pause();
        }
    }
    public void resume(int number){
        for (int i=0;i<number;i++) {
            ct[i].resume();
        }
    }
    public void stop(int number){
        for (int i=0;i<number;i++) {
            ct[i].stop();
        }
    }
    private CarThread[]ct;
    private Thread[]t;
}
