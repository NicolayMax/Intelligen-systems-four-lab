package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.ai.logpath.PathHistory;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;
import ru.bestcoders.aicarsuperracing.utils.XMLSaver;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CarThread implements Runnable{
    private Car car;
    private boolean end;
    private LevelMap levelMap;
    private ArrayList<Integer> breakingPoints;
    private Integer counterOfBreakingPoints;
    private Integer nextPoint;
    private PathHistory ph;

    private int next_i, next_j;

    private double bias;
//________________________________________________
    private boolean canMove;

    private double backwardsCounter;
    private double forwardCounter;
    private double leftCounter;
    private double rightCounter;

    private int lastMove = 1;

    private boolean firstCrossPassed;
    private int counterOfX;
    private int lastCrossCoorX;
    private int lastCrossCoorY;
    private Logger l;

    private int memMove;

    public CarThread(Car car, LevelMap levelMap, ArrayList<Integer> breakingPoints){
        this.car = car;
        end = false;
        this.levelMap = levelMap;
        this.breakingPoints = breakingPoints;
        counterOfBreakingPoints = 0;
        bias = 0;
        canMove = true;

        backwardsCounter = 0.5;
        forwardCounter = 0.5;
        leftCounter = 0.5;
        rightCounter = 0.5;

        lastMove = 1; /* 1 - вперед последнее движение
                         2 - влево последнее движение
                         3 - вправо последнее движение
                         4 - назад последнее движение
                        */

        counterOfX = 0;
        firstCrossPassed = false;
        l = Logger.getLogger("main");
        ph = new PathHistory();
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
                    /*System.out.println("forwardCounter="+forwardCounter);
                    System.out.println("leftCounter="+leftCounter);
                    System.out.println("rightCounter="+rightCounter);
                    System.out.println("backwardsCounter="+backwardsCounter);*/
                    if (levelMap.map[car.getPosY()][car.getPosX()]!=1) {
                        makeStep();
                        if (!canMove){
                            l.info("Движение невозможно");
                            l.info("Принято решение развернуться");
                            turnLeft();
                            turnLeft();
                            makeStep();

                            if (lastMove == 1){
                                forwardCounter-=0.25;
                                l.info("Корректировка: уменьшены веса движения вперед: "+forwardCounter);
                            }
                            else if (lastMove == 2){
                                leftCounter-=0.25;
                                l.info("Корректировка: уменьшены веса движения влево: "+leftCounter);
                            }
                            else if (lastMove == 3){
                                rightCounter-=0.25;
                                l.info("Корректировка: уменьшены веса движения вправо: "+rightCounter);
                            }
                            else if (lastMove == 4){
                                backwardsCounter-=0.25;
                                l.info("Корректировка: уменьшены веса движения назад: "+backwardsCounter);
                            }

                        }
                    }
                    else{//если находимся на перекрестке
                        l.info("Перекресток!");
                        counterOfX++;
                        //-----------------------------------------------------------------------------------------------
                        System.out.println("firstCrossPassed="+firstCrossPassed);
                        System.out.println("lastCrossCoorX="+lastCrossCoorX+", car.getPosY() = "+car.getPosY());
                        System.out.println("lastCrossCoorY="+lastCrossCoorY+", car.getPosX() = "+car.getPosX());
                        System.out.println("lastmove="+lastMove);
                        if  (firstCrossPassed &&
                                (
                                    (lastCrossCoorX!=car.getPosY()) || (lastCrossCoorY!=car.getPosX())
                                )
                            )
                        {
                            l.info("Успешное достижение следующего перекрестка");
                            if (lastMove == 1){
                                forwardCounter+=0.25;
                                l.info("Корректировка: увеличены веса движения вперед: "+forwardCounter);
                            }
                            else if (lastMove == 2){
                                leftCounter+=0.25;
                                l.info("Корректировка: увеличены веса движения влево: "+leftCounter);
                            }
                            else if (lastMove == 3){
                                rightCounter+=0.25;
                                l.info("Корректировка: увеличены веса движения вправо: "+rightCounter);
                            }
                            else if (lastMove == 4){
                                backwardsCounter+=0.25;
                                l.info("Корректировка: увеличены веса движения назад: "+backwardsCounter);
                            }
                            l.info("Корректировка: увеличены веса движения назад: "+backwardsCounter);
                            ph.makeRecord(lastCrossCoorX, lastCrossCoorY, forwardCounter, leftCounter, rightCounter, backwardsCounter, car.getPosY(),car.getPosX());
                            System.out.println("ph:"+lastCrossCoorX+","+lastCrossCoorY+","+forwardCounter+","+leftCounter+","+rightCounter+","+backwardsCounter+","+car.getPosY()+","+car.getPosX());
                            l.info("Веса движения вперед = "+forwardCounter+", веса движения влево = "+leftCounter+", веса движения вправо = "+rightCounter+", веса движения назад = "+backwardsCounter);

                            forwardCounter = 0.5;
                            leftCounter = 0.5;
                            rightCounter = 0.5;
                            backwardsCounter = 0.5;
                            l.info("Сброс весов");


                            for (int i = 0; i < levelMap.visitedMap.length; i++) {          //обнуляем посещенные точки
                                for (int j = 0; j < levelMap.visitedMap[i].length; j++) {
                                    levelMap.visitedMap[i][j] = 0;
                                }
                            }
                        }
                        if (levelMap.visitedMap[car.getPosY()][car.getPosX()] == 0){
                            l.info("Перекресток посещается впервые");
                            levelMap.visitedMap[car.getPosY()][car.getPosX()] = 1;
                            lastCrossCoorX = car.getPosY();
                            lastCrossCoorY = car.getPosX();
                            //------
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
                            if ((angle>=45)&&(angle<135)){              //поворот вправо
                                l.info("Принято решение повернуть направо: угол цели = "+angle);
                                turnRight();
                                lastMove = 3;
                            }
                            else if (((angle>=-45)&&(angle<45)) || ((angle > 315)&&(angle)<=360)) {       //прямо
                                l.info("Принято решение двигаться прямо: угол цели = "+angle);
                                lastMove = 1;
                            }
                            else if (
                                    ((angle>=-135)&&(angle<-45)) || ((angle>180)&& (angle<=315))
                            )
                            {
                                l.info("Принято решение повернуть налево: угол цели = "+angle);
                                turnLeft();
                                lastMove = 2;                           //влево

                            }
                            else if (
                                    (((angle>=-180)&&(angle<-135))) || (((angle>=135)&&(angle<=180)))
                            )
                            {
                                /*if(
                                    (forwardCounter==0.5)&&
                                    (leftCounter==0.5)&&
                                    (rightCounter==0.5)&&
                                    (backwardsCounter==0.5)
                                ){
                                    turnRight();
                                    lastMove = 3;
                                }
                                else{
                                    turnLeft();
                                    turnLeft();
                                    lastMove = 4; //поворот на 180
                                }*/
                                if (counterOfX > 1){
                                    l.info("Принято решение развернуться: угол цели = "+angle);
                                    turnLeft();
                                    turnLeft();
                                    lastMove = 4;
                                }
                                else{
                                    l.info("Принято решение повернуть налево: разворот невозможен");
                                    turnLeft();
                                    lastMove = 2;
                                }

                            }
                            makeStep();

                            //-----
                        }
                        else{//на этом перекрестке уже были
                            l.info("Перекресток посещается не в первый раз");
                            l.info("Агоритм обхода в глубину слева направо запущен");
                            counterOfX=0;
                            /*int randomNumber = 1 + (int) (Math.random() * 2); // сл.число [1;2]
                            System.out.println("randomNumber = "+randomNumber);*/

                            /*if (randomNumber == 1){

                            }
                            else if (randomNumber == 2){

                            }*/

                            System.out.println("forwardCounter="+forwardCounter);
                            System.out.println("leftCounter="+leftCounter);
                            System.out.println("rightCounter="+rightCounter);
                            System.out.println("backwardsCounter="+backwardsCounter);

                            memMove = lastMove;
                            l.info("Реализация обхода слева направо по ветвям дерева относительно точки въезда на перекресток");

                            if (lastMove == 3){     //обход дерева в глубину
                                if (leftCounter == 0.5){
                                    l.info("Принято решение двигаться прямо");
                                    lastMove = 2;
                                    makeStep();
                                }
                                else if (forwardCounter == 0.5){
                                    l.info("Принято решение повернуть направо");
                                    turnRight();
                                    lastMove = 1;
                                    makeStep();
                                }
                                else if (backwardsCounter == 0.5){
                                    l.info("Принято решение повернуть налево");
                                    turnLeft();
                                    lastMove = 4;
                                    makeStep();
                                }
                                else {
                                    end = true;
                                    l.info("Некуда ехать");
                                }
                            }
                            else if (lastMove == 1){
                                if (leftCounter == 0.5){
                                    l.info("Принято решение повернуть направо");
                                    turnRight();
                                    lastMove = 2;
                                    makeStep();
                                }
                                else if (rightCounter == 0.5){
                                    l.info("Принято решение повернуть налево");
                                    turnLeft();
                                    lastMove = 3;
                                    makeStep();
                                }
                                else if (backwardsCounter == 0.5){
                                    l.info("Принято решение двигаться прямо");
                                    lastMove = 4;
                                    makeStep();
                                }
                                else {
                                    end = true;
                                    l.info("Некуда ехать");
                                }
                            }
                            else if (lastMove == 2){
                                if (forwardCounter == 0.5){
                                    l.info("Принято решение повернуть налево");
                                    turnLeft();
                                    lastMove = 1;
                                    makeStep();
                                }
                                else if (rightCounter == 0.5){
                                    l.info("Принято решение двигаться прямо");
                                    lastMove = 3;
                                    makeStep();
                                }
                                else if (backwardsCounter == 0.5){
                                    l.info("Принято решение повернуть направо");
                                    turnRight();
                                    lastMove = 4;
                                    makeStep();
                                }
                                else {
                                    end = true;
                                    l.info("Некуда ехать");
                                }
                            }
                            else if (lastMove == 4){
                                if (leftCounter == 0.5){
                                    l.info("Принято решение повернуть налево");
                                    turnLeft();
                                    lastMove = 2;
                                    makeStep();
                                }
                                else if (forwardCounter == 0.5){
                                    l.info("Принято решение двигаться прямо");
                                    lastMove = 1;
                                    makeStep();
                                }
                                else if (rightCounter == 0.5){
                                    l.info("Принято решение повернуть направо");
                                    turnRight();
                                    lastMove = 3;
                                    makeStep();
                                }
                                else {
                                    end = true;
                                    l.info("Некуда ехать");
                                }
                            }
                        }
                        firstCrossPassed = true;
                        //-----------------------------------------------------------------------------------------------
                    }
                }
            }
            else {
                end = true;
            }
        }
        //saveXML
        XMLSaver.saveToFile(ph.record, "algorithm.xml");
    }

    public void makeStep(){
        canMove = car.move2();
        l.info("Совершен шаг вперед");
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
}
