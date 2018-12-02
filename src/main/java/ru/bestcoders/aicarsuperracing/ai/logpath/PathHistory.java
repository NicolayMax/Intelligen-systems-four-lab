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
}
