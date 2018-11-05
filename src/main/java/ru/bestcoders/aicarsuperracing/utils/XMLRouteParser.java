package ru.bestcoders.aicarsuperracing.utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import alexh.weak.Dynamic;
import alexh.weak.XmlDynamic;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import java.io.*;
import java.util.ArrayList;

/* Материал по библиотеке
 * https://stackoverflow.com/questions/39943821/how-to-get-value-of-xml-tag-with-different-attributes
 * https://github.com/alexheretic/dynamics
 * */

public class XMLRouteParser implements IXMLParser<Integer>{
    private int n;
    private ArrayList<Integer>seq;
    private boolean firstFound;

    private Document doc;
    private String stringFile;

    private String nextString;
    private Integer nextInteger;

    public XMLRouteParser(int n){
        this.n = n;
        seq = new ArrayList<>();
        firstFound = false;
    }

    public void parse(String pathName){
        seq.clear();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(pathName));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            stringFile = writer.getBuffer().toString().replaceAll("\n|\r|\t", "");
            //System.out.println(stringFile);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        Dynamic sFile = new XmlDynamic(stringFile);
        while (seq.size() < n){
            if (!firstFound) {
                findNextNode(sFile, "П0", 35, 37);
                firstFound = true;
                seq.add(nextInteger);

            }
            else{
                findNextNode(sFile, nextString, 94, 96);
                seq.add(nextInteger);
            }
        }
    }

    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    private void findNextNode(Dynamic dyn, String compString, int delimiter1, int delimiter2){
        String uncut = dyn.get("AIBase")
                .children()
                .filter(parameter -> parameter.get("@name").asString().equals(compString))
                .findAny()
                .map(parameter -> parameter.asString())
                .get();
        //System.out.println(uncut);
        nextString = uncut.substring(delimiter1, delimiter2);
        nextInteger = Integer.valueOf(uncut.substring(delimiter1+1, delimiter2));
    }

    public ArrayList<Integer>getResults(){
        return seq;
    }
}
