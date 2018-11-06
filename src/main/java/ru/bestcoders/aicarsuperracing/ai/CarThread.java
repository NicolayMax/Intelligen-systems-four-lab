package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

public class CarThread implements Runnable{
    private Car car;
    private boolean end;
    private LevelMap levelMap;

    public CarThread(Car car, LevelMap levelMap){
        this.car = car;
        end = false;
        this.levelMap = levelMap;
    }
    public void run(){
        while (!end){
            if (levelMap.map[car.getPosY()][car.getPosX()]!=1) {
                car.move();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
            else {
                end=true;
            }
        }
    }
}
