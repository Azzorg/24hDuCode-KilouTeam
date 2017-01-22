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
import java.util.ArrayList;

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
    private double distToParcours = 1000;
    double x1,x2,x3,x4;
    double y1,y2,y3,y4;
    ArrayList<Double> x = new ArrayList<Double>();
    ArrayList<Double> y = new ArrayList<Double>();
    
    public void ChangePosition(String htmlurl, double x, double y) throws IOException{

        File input = new File(htmlurl);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements scripts = doc.getElementsByClass("MainScript");
        System.out.println("***-------------------***");    
        for (Element element :scripts){                
            for (DataNode node : element.dataNodes()) {
                //System.out.println(node.getWholeData());
                node.setWholeData(
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
        //System.out.println(newHtml);
        FileWriter fooWriter = new FileWriter(input, false);
        fooWriter.write(newHtml);
        fooWriter.close();
        
    }
    
    public void DrawLine(String htmlurl) throws IOException{
    	
    	/* Generation des chemins possibles pour l'utilisateur */
    	x1 = 47.9;
    	y1 = 1.9;
    	generateAllPoints(x1, y1);
    	x.removeAll(x);
    	y.removeAll(y);
    	x.add(x2);
    	x.add(x3);
    	x.add(x4);
    	y.add(y2);
    	y.add(y3);
    	y.add(y4);
    	
    	for(int i=0; i<x.size(); i++){
    		System.out.println(x.get(i) +" " + y.get(i));
    	}
    	
    	
    	String newScript = "";
    	
    	File input = new File(htmlurl);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements scripts = doc.getElementsByClass("MainScript");
        System.out.println("***-------------------***");   
        
        newScript += 
		"L.Mappy.setClientId(\"dri_24hducode\");\n"+
		"var exampleMap1 = new L.Mappy.Map(\"example-map-1\", {\n"+
			"clientId: 'dri_24hducode',\n"+
			"center: [48, 2],\n"+
			"zoom: 10\n"+
		"});\n"+
		"var greenIcon = L.icon({\n"+
			"iconUrl: 'images/marker_geolocation_x2.png',\n"+
			"iconSize:     [50, 50],\n"+
			"shadowSize:   [50, 50],\n"+
			"iconAnchor:   [10, 22],\n"+
			"shadowAnchor: [0, 0],\n"+
			"popupAnchor:  [-3, -76]\n"+
		"});\n"+
  "function measure(lat1, lon1, lat2, lon2){  \n"+
    "var R = 6378.137; \n"+
    "var dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;\n"+
    "var dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;\n"+
    "var a = Math.sin(dLat/2) * Math.sin(dLat/2)\n" +
    "Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *\n"+
    "Math.sin(dLon/2) * Math.sin(dLon/2);\n"+
    "var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));\n"+
    "var d = R * c;\n"+
    "return d * 1000; \n"+
  "}\n"+
  "var options = {"+
			"vehicle: L.Mappy.Vehicles.comcar,\n"+
			"cost: \"length\", \n"+
			"gascost: 1.0,	\n"+
			"nopass: 0, \n"+
			"notoll: 0,\n"+
			"infotraffic: 0\n"+
		"};\n";
    
       for(int i=0; i<x.size(); i++){
    	   
    	   newScript += "L.Mappy.Services.route([L.latLng("+x1+","+y1+"), L.latLng("+x.get(i)+", "+y.get(i)+")],\n"+
	    "options,\n"+
	    "function(result) {\n"+
	        "L.Mappy.route(result.routes).addTo(exampleMap1);\n"+
	        "var summary = result.routes.route[0].summary;\n"+
	        "var roadbook = result.routes.route[0].actions.action;\n"+
	    "},\n"+
	    "function(errorType) {\n"+
	    "});\n"+
	    "var marker = L.marker(["+x.get(i)+", "+y.get(i)+"], {icon : greenIcon}).addTo(exampleMap1).bindPopup(\""+i+"\");";
	    
       }
        
        for (Element element :scripts){                
            for (DataNode node : element.dataNodes()) {
                //System.out.println(node.getWholeData());
                node.setWholeData("");
                node.setWholeData(newScript);

            }
            System.out.println("***-------------------***");
        } 
        System.out.println("***************************************");
        String newHtml = doc.outerHtml();
        //System.out.println(newHtml);
        FileWriter fooWriter = new FileWriter(input, false);
        fooWriter.write(newHtml);
        fooWriter.close();
    }
    
    public double measure(double lat1, double lon1, double lat2, double lon2){  // generally used geo measurement function
        double R = 6378.137; // Radius of earth in KM
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d * 1000; // meters
      }
    
 
    public ArrayList<Double> generateCoords(double x, double y, double  xp, double yp){
    	
    	ArrayList<Double> coords = new ArrayList<Double>();

    	double dist = 0;
    	
    	do{ 
    		  
    	      yp = y + ((Math.random()-0.5) * (distToParcours/20000));
    	      xp = x + ((Math.random()-0.5) * (distToParcours/330000));
    	      //System.out.println(" X : " + xp + " Y : " + yp);
    	      //System.out.println("dist : " + dist);
    	      dist = measure(x, y, xp, yp);
    	      System.out.println("dist : " + dist);
    	    }while(dist<=(distToParcours-(distToParcours/10)) || dist>=(distToParcours+(distToParcours/10)));

    	coords.add(xp);
    	coords.add(yp);
	
	    return coords;
    }
    
    public void generateAllPoints(double x, double y){
	
    	ArrayList<Double> coordsTemp = new ArrayList<Double>();
    	coordsTemp = generateCoords(x, y, x2, y2);
    	x2=coordsTemp.get(0);
    	y2=coordsTemp.get(1);
    	System.out.println("x2 : " + x2 + " y2 : " +y2);
    	coordsTemp = generateCoords(x, y ,x3, y3);
    	x3=coordsTemp.get(0);
    	y3=coordsTemp.get(1);
    	coordsTemp = generateCoords(x, y, x4, y4);
    	x4=coordsTemp.get(0);
    	y4=coordsTemp.get(1);
    	System.out.println("Generate all points");
    }
    
}
 