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

    public CarThread(Car car, LevelMap levelMap, ArrayList<Integer> breakingPoints){
        this.car = car;
        end = false;
        this.levelMap = levelMap;
        this.breakingPoints = breakingPoints;
        counterOfBreakingPoints = 0;
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
                if (counterOfBreakingPoints<breakingPoints.size()) {
                    nextPoint = breakingPoints.get(counterOfBreakingPoints);
                    System.out.println("Next point = " + nextPoint);
                }
                else{
                    end = true;
                }
                counterOfBreakingPoints++;
            }
        }
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
