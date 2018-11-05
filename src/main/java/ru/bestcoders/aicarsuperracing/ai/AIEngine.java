package ru.bestcoders.aicarsuperracing.ai;

public class AIEngine {
    public Sequence s;

    public AIEngine(){
        s = new Sequence();
    }

    public void init(){
        s.loadRoutes();
    }
}
