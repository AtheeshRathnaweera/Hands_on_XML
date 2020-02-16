package com.atheesh.learnxml.SQLtoXML;

import com.atheesh.learnxml.DB.DBConnection;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.*;

public class ConverterSQL {

    private static Document document;
    private static Element rootNode;
    private static Element currentMainNode;

    public static void main(String args[]) {

        Connection myConn = DBConnection.getConnection();
        document = XMLControllers.createANewDocument();
        rootNode = XMLControllers.createANewElement(document,"sqlData");
        document.appendChild(rootNode);

        usersTableDataConverter(myConn);
        pricesTableConverter(myConn);

        XMLControllers.writeTheContentToXML(document,System.getProperty("user.dir") + "/src/generated/sqlData.xml");

    }

    //table convert
    private static void usersTableDataConverter(Connection myConn) {

        currentMainNode = XMLControllers.createANewElement(document,"users");//create the main node
        rootNode.appendChild(currentMainNode);//append users node to sql data root node

        String sql = "SELECT * FROM users";

        try {
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String town = result.getString(4);
                String city = result.getString(5);
                String postalCode = result.getString(6);
                Date dob = result.getDate(7);
                String email = result.getString(8);
                String phone = result.getString(9);

                System.out.println(Integer.toString(id));

                addUserToMainNode(id, firstName,lastName,town,city,postalCode,dob,email,phone);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void pricesTableConverter(Connection myConn){

        currentMainNode = XMLControllers.createANewElement(document,"prices");//create the main node
        rootNode.appendChild(currentMainNode);//append prices node to sql data root node

        String sql = "SELECT * FROM prices";

        try {
            Statement statement = myConn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String price = result.getString(3);

                System.out.println(Integer.toString(id));

                addItemToMainNode(id,name,price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //table convert


    //user creation
    private static Element createAUserInnerElement(String elementName,String textValue){
        //this method can be used to add any kind of node to created tempNode --> textNode,comment node,...
        Element tempNode = XMLControllers.createANewElement(document,elementName);
        tempNode.appendChild(XMLControllers.createANewTextNode(document,textValue));//insert text inside tha tags
        return tempNode;
    }

    private static void addUserToMainNode(int id, String firstName, String lastName, String town, String city, String postalCode, Date dob, String email, String phone){

        Element user = XMLControllers.createANewElement(document,"user");
        Attr idAttr = XMLControllers.createANewAttribute(document,"id",Integer.toString(id));
        user.setAttributeNode(idAttr);

        user.appendChild(createAUserInnerElement("firstName",firstName));
        user.appendChild(createAUserInnerElement("lastName",lastName));
        user.appendChild(createAUserInnerElement("town",town));
        user.appendChild(createAUserInnerElement("city",city));
        user.appendChild(createAUserInnerElement("postalCode",postalCode));
        user.appendChild(createAUserInnerElement("dob",dob.toString()));
        user.appendChild(createAUserInnerElement("email",email));
        user.appendChild(createAUserInnerElement("phone",phone));

        currentMainNode.appendChild(user);

    }
    //user creation

    //prices creation
    private static Element createAnItemInnerElement(String elementName,String textValue){
        //this method can be used to add any kind of node to created tempNode --> textNode,comment node,...
        Element tempNode = XMLControllers.createANewElement(document,elementName);
        tempNode.appendChild(XMLControllers.createANewTextNode(document,textValue));//insert text inside tha tags
        return tempNode;
    }

    private static void addItemToMainNode(int id, String name, String price){

        Element item = XMLControllers.createANewElement(document,"item");
        Attr idAttr = XMLControllers.createANewAttribute(document,"id",Integer.toString(id));
        item.setAttributeNode(idAttr);

        item.appendChild(createAnItemInnerElement("name",name));
        item.appendChild(createAnItemInnerElement("price",price));

        currentMainNode.appendChild(item);

    }
    //prices creation





}
