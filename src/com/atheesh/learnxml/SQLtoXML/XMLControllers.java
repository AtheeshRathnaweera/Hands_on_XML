package com.atheesh.learnxml.SQLtoXML;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLControllers {

    public static Document createANewDocument() {
        Document createdDoc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            createdDoc = dBuilder.newDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return createdDoc;
    }

    public static Element createANewElement(Document doc, String elementName) {
        return doc.createElement(elementName);
    }

    public static Attr createANewAttribute(Document doc, String attrName, String attrValue){
        Attr newAttr = doc.createAttribute(attrName);
        newAttr.setValue(attrValue);
        return newAttr;
    }

    public static Text createANewTextNode(Document doc, String data){
        return doc.createTextNode(data);
    }

    public static void writeTheContentToXML(Document doc,String pathName){

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pathName));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
