package com.atheesh.learnxml.DOMparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class QueryXML {

    public static void main(String args[]){

        try{
            File inputFile = new File("src/resources/QueryTest.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.print("Root element: ");
            System.out.println(doc.getDocumentElement().getNodeName());

            NodeList nodeList = doc.getElementsByTagName("supercars");
            System.out.println("----------------------------");

            for(int n=0; n<nodeList.getLength(); n++){
                Node tempNode = nodeList.item(n);

                if(tempNode.getNodeType() == Node.ELEMENT_NODE){
                    Element tempElement = (Element) tempNode;
                    String companyName = tempElement.getAttribute("company");
                    System.out.println("\n"+companyName+"----------------");
                    NodeList carNamesList = tempElement.getElementsByTagName("carname");

                    for(int x=0;x<carNamesList.getLength();x++){
                        Node carNameNode = carNamesList.item(x);

                        if(carNameNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element carNameElement = (Element) carNameNode;
                            String carType = carNameElement.getAttribute("type");
                            String carName = carNameElement.getTextContent();

                            System.out.println("type : " + carType);
                            System.out.println("name : " + carName);
                        }
                    }

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
