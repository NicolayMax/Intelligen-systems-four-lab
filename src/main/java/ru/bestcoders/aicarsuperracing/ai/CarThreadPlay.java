package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.ai.logpath.Data;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CarThreadPlay implements Runnable {
    private Car car;
    private LevelMap levelMap;
    private ArrayList<Data>record;
    private Logger l;

    private volatile boolean running;

    private int counter;

    public CarThreadPlay(Car car, LevelMap levelMap, ArrayList<Data>record) {
        running = true;
        this.car = car;
        this.levelMap = levelMap;
        this.record = record;
        l = Logger.getLogger("main");

        counter = 0;
    }
    @Override
    public void run() {
        while (running){
            if (levelMap.map[car.getPosY()][car.getPosX()]!=1) {
                makeStep();
            }
            else{
                if (
                    (record.get(counter).getW_forward() > record.get(counter).getW_left())&&
                    (record.get(counter).getW_forward() > record.get(counter).getW_right())&&
                    (record.get(counter).getW_forward() > record.get(counter).getW_backwards())
                    )
                {
                    makeStep();
                }
                else if (
                        (record.get(counter).getW_left() > record.get(counter).getW_forward())&&
                        (record.get(counter).getW_left() > record.get(counter).getW_right())&&
                        (record.get(counter).getW_left() > record.get(counter).getW_backwards())
                ) {
                    turnLeft();
                    makeStep();
                }
                else if (
                        (record.get(counter).getW_right() > record.get(counter).getW_forward())&&
                        (record.get(counter).getW_right() > record.get(counter).getW_left())&&
                        (record.get(counter).getW_right() > record.get(counter).getW_backwards())
                ) {
                    turnRight();
                    makeStep();
                }
                else if (
                        (record.get(counter).getW_backwards() > record.get(counter).getW_forward())&&
                        (record.get(counter).getW_backwards() > record.get(counter).getW_left())&&
                        (record.get(counter).getW_backwards() > record.get(counter).getW_right())
                ) {
                    turnLeft();
                    turnLeft();
                    makeStep();
                }
                counter++;
            }
        }
    }

    public void makeStep(){
        car.move();
        l.info("Совершен шаг вперед");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    private void turnLeft(){
        car.rotateLeft();
        l.info("Совершен поворот налево");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    private void turnRight(){
        car.rotateRight();
        l.info("Совершен поворот направо");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }
}
