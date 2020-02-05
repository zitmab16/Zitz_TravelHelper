package BL;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLAccess {

    private static XMLAccess instance = null;
    private final String path = System.getProperty("user.home") + "\\Documents\\weatherdata.xml";
    private static Document doc;

     public XMLAccess() throws Exception{
        instance = this;
        this.createXML(new File(path));
    }
    
    
    private void createXML(File f) throws Exception {
        if (f.createNewFile()) {
            Element e = new Element("entries");
            doc = new Document(e);
            XMLOutputter xmlOut = new XMLOutputter();
            xmlOut.setFormat(Format.getPrettyFormat());
            xmlOut.output(doc, new FileWriter(f));
        } else {
            SAXBuilder builder = new SAXBuilder();
            doc = builder.build(f);
        }
    }
    public static XMLAccess getInstance() throws Exception {
        if (instance == null) {
            new XMLAccess();
        }

        return instance;
    }

    public void addEntry(Destination d) throws IOException {
        Element ele = new Element("city");
        Element childele = new Element("destination");
        childele.setText(d.getDestname());
        ele.addContent(childele);
        childele = new Element("zipcode");
        childele.setText(d.getZipCode());
        ele.addContent(childele);

        doc.getRootElement().addContent(ele);

        saveXML();
    }

    private void saveXML() throws IOException {
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(doc, new FileWriter(new File(path)));
    }

    public ArrayList<Destination> loadFromXML() {
        Element root = doc.getRootElement();
        String destination = "";
        String zipcode = "";
        ArrayList<Destination > destinations = new ArrayList<>();
        for (Element ele : root.getChildren("city")) {
            destination = ele.getChild("destination").getText();
            zipcode = ele.getChild("zipcode").getText();
            destinations.add(new Destination(zipcode, destination));
        }
        return destinations;
    }
}
