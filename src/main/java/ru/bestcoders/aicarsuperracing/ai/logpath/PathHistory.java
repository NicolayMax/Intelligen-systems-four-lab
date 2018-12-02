package ru.bestcoders.aicarsuperracing.ai.logpath;

import java.util.ArrayList;

public class PathHistory {
    public ArrayList<Data>record;

    public PathHistory(){
        record = new ArrayList<>();
    }

    public void makeRecord(int x_current, int y_current, double w_forward, double w_left, double w_right, double w_backwards, int x_next, int y_next){
        record.add(new Data(x_current, y_current, w_forward, w_left, w_right, w_backwards, x_next, y_next));
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
