package ru.bestcoders.aicarsuperracing.utils;

import java.util.ArrayList;

public interface IXMLParser<T>{
    public void parse(String pathName);
    public ArrayList<T>getResults();
}
