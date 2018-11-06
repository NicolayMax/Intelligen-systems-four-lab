package ru.bestcoders.aicarsuperracing.ai;

import ru.bestcoders.aicarsuperracing.utils.IXMLParser;
import ru.bestcoders.aicarsuperracing.utils.XMLRouteParser;

import java.util.ArrayList;
import java.util.Arrays;

public class Sequence {
    private int n;
    private IXMLParser parser;
    private ArrayList<Integer>seq1;         //отвечает за маршрут1
    private ArrayList<Integer>seq2;         //отвечает за маршрут2

    public Sequence(){
        n = 3;
    }

    public void loadRoutes() {
        parser = new XMLRouteParser(n);
        parser.parse("./src/main/resources/xml/alg1.xml");
        seq1 = parser.getResults();
        //System.out.println(Arrays.toString(seq1.toArray()));

        parser = new XMLRouteParser(n);
        parser.parse("./src/main/resources/xml/alg2.xml");
        seq2 = parser.getResults();
        //System.out.println(Arrays.toString(seq2.toArray()));
    }

    public ArrayList<Integer>getSeq(int i){
        if (i == 0) {
            return seq1;
        }
        else {
            return seq2;
        }
    }

    public int getN(){
        return n;
    }
}
