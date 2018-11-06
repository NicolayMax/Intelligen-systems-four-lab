package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;

import java.util.ArrayList;

public class CarThread implements Runnable{
    private Car car;
    private boolean end;
    private LevelMap levelMap;
    private ArrayList<Integer> breakingPoints;
    private Integer counterOfBreakingPoints;
    private Integer nextPoint;

    private int next_i, next_j;

    private double bias;

    public CarThread(Car car, LevelMap levelMap, ArrayList<Integer> breakingPoints){
        this.car = car;
        end = false;
        this.levelMap = levelMap;
        this.breakingPoints = breakingPoints;
        counterOfBreakingPoints = 0;
        bias = 0;
    }
    public void run(){

        while (!end){
            if (counterOfBreakingPoints<breakingPoints.size())
                nextPoint = breakingPoints.get(counterOfBreakingPoints);

            for (int i = 0; i < levelMap.mapForBreakingPoints.length; i++) {
                for (int j = 0; j < levelMap.mapForBreakingPoints[i].length; j++) {
                    if (levelMap.mapForBreakingPoints[i][j] == nextPoint) {
                        next_i = i;
                        next_j = j;
                    }
                }
            }
            System.out.println("______________________________________________________________");
            System.out.println("Current point: " + "i = "+car.getPosY()+", j = "+car.getPosX());
            System.out.println("Next point = " + nextPoint+", next_i = "+next_i+", next_j = "+next_j);
            System.out.println("______________________________________________________________");

            if (counterOfBreakingPoints<breakingPoints.size()) {
                if (levelMap.mapForBreakingPoints[car.getPosY()][car.getPosX()]==nextPoint){
                    counterOfBreakingPoints++;
                }
                else{
                    if (levelMap.map[car.getPosY()][car.getPosX()]!=1) {
                        makeStep();
                    }
                    else{
                        if (car.getDirection() == Car.Direction.LEFT){
                            bias = 0;
                        }
                        else if (car.getDirection() == Car.Direction.DOWN){
                            bias = 90;
                        }
                        else if (car.getDirection() == Car.Direction.UP){
                            bias = -90;
                        }
                        else if (car.getDirection() == Car.Direction.RIGHT){
                            bias = 180;
                        }
                        double angle = Math.atan2(/*Math.abs(*/car.getPosY()-next_i/*)*/, /*Math.abs(*/car.getPosX()-next_j/*)*/) / Math.PI * 180 + bias;
                        System.out.println("angle = " + angle);
                        if ((angle>=45)&&(angle<135)){
                            turnRight();
                        }
                        else if (((angle>=-45)&&(angle<45))){
                            //do nothing
                        }
                        else if (
                                (((angle>=-135)&&(angle<-45))) || (angle>180)
                                )
                        {
                            turnLeft();
                        }
                        else if (
                                (((angle>=-180)&&(angle<-135))) || (((angle>=135)&&(angle<180)))
                                )
                        {
                            turnLeft();
                            turnLeft();
                        }
                        makeStep();
                    }
                }

                /*
                else {
                    for (int i = 0; i < levelMap.mapForBreakingPoints.length; i++) {
                        for (int j = 0; j < levelMap.mapForBreakingPoints[i].length; j++) {
                            if (levelMap.mapForBreakingPoints[i][j] == nextPoint) {
                                nextX = j;
                                nextY = i;
                            }
                        }
                    }
                    //


                }*/
            }
            else {
                end = true;
            }

            /*if (levelMap.map[car.getPosY()][car.getPosX()]!=1) {
                makeStep();
            }
            else {
                if (counterOfBreakingPoints<breakingPoints.size()) {
                    nextPoint = breakingPoints.get(counterOfBreakingPoints);

                    for (int i = 0; i < levelMap.mapForBreakingPoints.length; i++) {
                        for (int j = 0; j < levelMap.mapForBreakingPoints[i].length; j++) {
                            if (levelMap.mapForBreakingPoints[i][j] == nextPoint) {
                                nextX = j;
                                nextY = i;
                            }
                        }
                    }
                    counterOfBreakingPoints++;
                    System.out.println("Current point:" + "X = "+car.getPosY()+", Y = "+car.getPosX());
                    System.out.println("Next point = " + nextPoint+", nextX = "+nextX+", nextY = "+nextY);


                    makeStep();
                }
                else{
                    end = true;
                }
            }*/
        }
    }

    public void makeStep(){
        car.move();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    private void turnLeft(){
        car.rotateLeft();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    private void turnRight(){
        car.rotateRight();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    /*public static int getFirst(int[][] arr) {
        if (arr.length == 0)
            throw new IllegalArgumentException(); // пустой массив, кидаем исключение
        // случай для всех пустых вложенных массивов не рассматриваю.
        int result = Integer.MIN_VALUE;

        for (int[] i : arr) {
            for (int j : i)
                result = Math.max(result, j);
        }

        return result;
    }*/
}
