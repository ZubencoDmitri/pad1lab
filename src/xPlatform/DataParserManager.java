package xPlatform;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataParserManager {
    private Document doc;
    private String stringData;
    public DataParserManager(String stringData) {
        //stringData="<message text=\"hello\"><receiver>q</receiver><receiver>w</receiver><receiver>e</receiver><receiver>r</receiver></message>";
        this.stringData=stringData;
    }
    public List<String>getReceivers() {
        List<String>rec=new ArrayList<>();
        for(int i=0;i<doc.getChildNodes().item(0).getChildNodes().getLength();i++)
            rec.add(i,doc.getChildNodes().item(0).getChildNodes().item(i).getTextContent());
//        System.out.println(rec);
        return rec;
    }
    public String getMessage() {
//        System.out.println(doc.getChildNodes().item(0).getAttributes().getNamedItem("text").getTextContent());
        return doc.getChildNodes().item(0).getAttributes().getNamedItem("text").getTextContent();//get attr
    }
    public boolean CheckIfXml(){
        try
        {
            DocumentBuilderFactory documentBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder doccumentBuilder = documentBuildFactory.newDocumentBuilder();
            doc = doccumentBuilder.parse(new ByteArrayInputStream(stringData.getBytes()));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
