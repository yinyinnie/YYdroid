package com.hujiang.yinyinnie.textxmlparse;

import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by nieyinyin on 15/11/12.
 */
public class XMLTestUtils {

    public static void PullParseXML(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");
        int event = xmlPullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            String tagName = xmlPullParser.getName();
            String text = xmlPullParser.getText();
            if (tagName != null) {
                Log.d("xys", "name : " + tagName + "----text:" + text);
            }
            event = xmlPullParser.next();
        }
    }

    public static Map<String, String> DOMParseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        Map<String, String> xmlMap = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            parseNode(xmlMap, nodes.item(i));
        }

        return xmlMap;
    }

    private static void parseNode(Map xmlMap, Node node){
        if(node.hasChildNodes()) {
            NodeList nodeList = node.getChildNodes();
            for(int i = 0; i< nodeList.getLength(); i++){
                Node item = nodeList.item(i);
                parseNode(xmlMap,item);
            }
        }else{
            xmlMap.put(node.getNodeName(), node.getNodeValue());
        }
    }

}
