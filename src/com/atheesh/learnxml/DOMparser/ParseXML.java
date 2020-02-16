package com.atheesh.learnxml.DOMparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ParseXML {

    public static void main(String args[]) {

        try {
            File inputFile = new File("src/resources/ParseTest.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("student");//get all the student nodes
            System.out.println("----------------------------");

            for (int n = 0; n < nList.getLength(); n++) {
                Node mainNode = nList.item(n);
                System.out.println("\nCurrent Node name :"+mainNode.getNodeName() );

                //check whether the current node is an element node or not
                if (mainNode.getNodeType() == Node.ELEMENT_NODE) {
                    //convert mainNode node to element (java casting)
                    Element eElement = (Element) mainNode;
                    System.out.println(mainNode.getNodeName()+" roll no : "+ eElement.getAttribute("rollno"));

                    System.out.println("First Name : "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : "+ eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Marks : "+ eElement.getElementsByTagName("marks").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
