/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author slokhas
 */
public class PositionModifier {
    
    BufferedWriter bw = null;
    FileWriter fw = null;
    
    public void ChangePosition(String htmlurl, double x, double y) throws IOException{

        File input = new File(htmlurl);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements scripts = doc.getElementsByTag("script");
        System.out.println("***-------------------***");    
        for (Element element :scripts){                
            for (DataNode node : element.dataNodes()) {
                System.out.println(node.getWholeData());
                node.setWholeData("L.Mappy.setImgPath(\"../dossier/des/logos/\");\n" +
"		var exampleMap1 = new L.Mappy.Map(\"example-map-1\", {\n" +
"			clientId: 'dri_24hducode',\n" +
"			center: ["+x+", "+y+"],\n" +
"			zoom: 7\n" +
"		});\n" +
"		alert(\"paptate\");");
            }
            System.out.println("***-------------------***");            
        } 
        System.out.println("***************************************");
        String newHtml = doc.outerHtml();
        System.out.println(newHtml);
        FileWriter fooWriter = new FileWriter(input, false);
        fooWriter.write(newHtml);
        fooWriter.close();
        
    }
    
    public void DrawLine(String htmlurl, double x1, double y1, double d, double e) throws IOException{
    	
    	File input = new File(htmlurl);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements scripts = doc.getElementsByTag("script");
        System.out.println("***-------------------***");    
        for (Element element :scripts){                
            for (DataNode node : element.dataNodes()) {
                System.out.println(node.getWholeData());
                node.setWholeData("L.Mappy.setImgPath(\"../dossier/des/logos/\");\n" +
"		var exampleMap1 = new L.Mappy.Map(\"example-map-1\", {\n" +
"			clientId: 'dri_24hducode',\n" +
"			center: ["+x1+", "+y1+"],\n" +
"			zoom: 7\n" +
"		});\n" +
"		alert(\"paptate\");\n" + 
"		L.Mappy.Services.route([L.latLng("+x1+", "+y1+"), L.latLng("+d+", "+e+")], \n"+
"	    options,\n"+
"	    // Callback de succès\n"+
"	    function(result) {\n"+
"	        L.Mappy.route(result.routes).addTo(map);\n"+
"	        var summary = result.routes.route[0].summary;\n"+
"	        var roadbook = result.routes.route[0].actions.action;\n"+
"	    },\n"+
"	    // Callback d'erreur\n"+
"	    function(errorType) {\n"+
"	        // Error during route calculation\n" +
"	    });");

            }
            System.out.println("***-------------------***");            
        } 
        System.out.println("***************************************");
        String newHtml = doc.outerHtml();
        System.out.println(newHtml);
        FileWriter fooWriter = new FileWriter(input, false);
        fooWriter.write(newHtml);
        fooWriter.close();
    }
    
}
