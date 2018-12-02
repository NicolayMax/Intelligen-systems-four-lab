package ru.bestcoders.aicarsuperracing.ai.logpath;

public class Data {
    /*<
            X координата текущего перекрестка,
            Y координата текущего перекрестка,
            вес движения вперед,
            вес движения влево,
            вес движения вправо,
            вес движения назад,
            X координата следующего перекрестка,
            Y координата следующего перекрестка
     >*/
    private int x_current;
    private int y_current;
    private double w_forward;
    private double w_left;
    private double w_right;
    private double w_backwards;
    private int x_next;
    private int y_next;

    public Data(){
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }
    public Data(int x_current, int y_current, double w_forward, double w_left, double w_right, double w_backwards, int x_next, int y_next) {
        this.x_current = x_current;
        this.y_current = y_current;
        this.w_forward = w_forward;
        this.w_left = w_left;
        this.w_right = w_right;
        this.w_backwards = w_backwards;
        this.x_next = x_next;
        this.y_next = y_next;
    }

    public int getX_current() {
        return x_current;
    }

    public void setX_current(int x_current) {
        this.x_current = x_current;
    }

    public int getY_current() {
        return y_current;
    }

    public void setY_current(int y_current) {
        this.y_current = y_current;
    }

    public double getW_forward() {
        return w_forward;
    }

    public void setW_forward(double w_forward) {
        this.w_forward = w_forward;
    }

    public double getW_left() {
        return w_left;
    }

    public void setW_left(double w_left) {
        this.w_left = w_left;
    }

    public double getW_right() {
        return w_right;
    }

    public void setW_right(double w_right) {
        this.w_right = w_right;
    }

    public double getW_backwards() {
        return w_backwards;
    }

    public void setW_backwards(double w_backwards) {
        this.w_backwards = w_backwards;
    }

    public int getX_next() {
        return x_next;
    }

    public void setX_next(int x_next) {
        this.x_next = x_next;
    }

    public int getY_next() {
        return y_next;
    }

    public void setY_next(int y_next) {
        this.y_next = y_next;
    }
}
