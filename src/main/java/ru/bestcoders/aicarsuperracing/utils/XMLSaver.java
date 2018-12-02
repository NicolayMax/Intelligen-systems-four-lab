package ru.bestcoders.aicarsuperracing.utils;

import org.w3c.dom.*;
import ru.bestcoders.aicarsuperracing.ai.logpath.Data;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLSaver {

     public static void saveToFile(List<Data> record, String fileName) {
         try {
             DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
             Document document = documentBuilder.newDocument();

             Element root = document.createElement("AIBase");
             document.appendChild(root);

             for (Data data: record) {
                 Element dataBase = document.createElement("record");

                 dataBase.setAttribute("x_current", Integer.toString(data.getX_current()));
                 dataBase.setAttribute("y_current", Integer.toString(data.getY_current()));
                 dataBase.setAttribute("w_forward", Double.toString(data.getW_forward()));
                 dataBase.setAttribute("w_left", Double.toString(data.getW_left()));
                 dataBase.setAttribute("w_right", Double.toString(data.getW_right()));
                 dataBase.setAttribute("w_backwards", Double.toString(data.getW_backwards()));
                 dataBase.setAttribute("x_next", Integer.toString(data.getX_next()));
                 dataBase.setAttribute("y_next", Integer.toString(data.getY_next()));

                 root.appendChild(dataBase);
             }

             // create the xml file
             //transform the DOM Object to an XML File
             TransformerFactory transformerFactory = TransformerFactory.newInstance();
             Transformer transformer = transformerFactory.newTransformer();
             DOMSource domSource = new DOMSource(document);
             StreamResult streamResult = new StreamResult(new File(fileName));

             transformer.transform(domSource, streamResult);
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     public static List<Data> readFromFile(String filename) {
         List<Data> record = new ArrayList<>();
         try {
             File fXmlFile = new File(filename);
             DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
             Document doc = dBuilder.parse(fXmlFile);
             doc.getDocumentElement().normalize();
             NodeList nList = doc.getElementsByTagName("record");

             for (int temp = 0; temp < nList.getLength(); temp++) {
                 Element e = (Element)nList.item(temp);

                 record.add(new Data(Integer.parseInt(e.getAttribute("x_current")),
                         Integer.parseInt(e.getAttribute("y_current")),
                         Double.parseDouble(e.getAttribute("w_forward")),
                         Double.parseDouble(e.getAttribute("w_left")),
                         Double.parseDouble(e.getAttribute("w_right")),
                         Double.parseDouble(e.getAttribute("w_backwards")),
                         Integer.parseInt(e.getAttribute("x_next")),
                         Integer.parseInt(e.getAttribute("y_next"))));
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

         return record;
     }
}
