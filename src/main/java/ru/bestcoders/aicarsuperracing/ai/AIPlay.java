package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.ai.logpath.Data;
import ru.bestcoders.aicarsuperracing.entities.Car;
import ru.bestcoders.aicarsuperracing.level.LevelMap;
import ru.bestcoders.aicarsuperracing.utils.XMLSaver;

import java.util.ArrayList;

public class AIPlay {
    private LevelMap levelMap;
    private ArrayList<Data>record;
    private CarThreadPlay[]ct;
    private Thread[]t;

    public AIPlay(LevelMap levelMap){
        this.levelMap = levelMap;
    }

    public void init(){
        record = (ArrayList<Data>) XMLSaver.readFromFile("algorithm.xml");
        //showHistory();
    }

    public void play(Car...cars) {
        ct = new CarThreadPlay[cars.length];
        t = new Thread[cars.length];

        for (int i=0;i<cars.length;i++) {
            ct[i] = new CarThreadPlay(cars[i], levelMap, record);
        }
        for (int i=0;i<cars.length;i++) {
            t[i]= new Thread(ct[i]);
        }
        for (int i=0;i<cars.length;i++) {
            t[i].start();
        }
    }

    public void showHistory(){
        for (Data data : record) {
            System.out.println("_________________");
            System.out.println("data.x_current = \t" + data.getX_current());
            System.out.println("data.y_current = \t" + data.getY_current());
            System.out.println("data.w_forward = \t" + data.getW_forward());
            System.out.println("data.w_left = \t" + data.getW_left());
            System.out.println("data.w_right = \t" + data.getW_right());
            System.out.println("data.w_backwards = \t" + data.getW_backwards());
            System.out.println("data.x_next = \t" + data.getX_next());
            System.out.println("data.y_next = \t" + data.getY_next());
        }
    }
}
