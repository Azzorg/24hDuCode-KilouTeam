package serveur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLWriter {

	/**
	 * Create the new JS call of the Mappy API. With the marker on the map for locate place
	 * @param allBat: place to locate on the map
	 * @return the new JS code
	 */
	public String CreateJSGeocode(ArrayList<ArrayList<Place>> allBat) {
		String newJS = "L.Mappy.setImgPath(\"images/\");"
				+ "var exampleMap1 = new L.Mappy.Map(\"example-map-1\", {clientId: 'dri_24hducode',"
				+ "center: [43.6044, 1.44295],zoom: 7});";

		/*
		 * 
		 * 
		 * CALCUL DES ROUTEUH
		 * 
		 */
		
		
		for (ArrayList<Place> pl : allBat) {
			String ic_type = pl.get(0).getType();
			newJS += "var " + ic_type + "Icon = L.icon({" + "iconUrl: 'images/ic_lieu/ic_" + ic_type + ".png',"
					+ "shadowUrl: 'images/ic_lieu/ombre.png'," +

					"iconSize:     [50, 50]," + "shadowSize:   [50, 50]," + "iconAnchor:   [10, 22],"
					+ "shadowAnchor: [0, 0]," + "popupAnchor:  [-3, -76]" + "});";

			for (Place p : pl) {
				newJS += "L.Mappy.Services.geocode(\"" + p.getAddr() + "\"," + "function(results){"
						+ "var coords = results[0].Point.coordinates.split(\",\").reverse();"
						+ "exampleMap1.addLayer(L.marker(coords, {icon: " + ic_type + "Icon}));},"
						+ "function(errorType) { 			});";
			}
		}

		return newJS;
	}

	/**
	 * Rewrite the HTML file with the new marker.
	 * @param newJS: the new JS for replacing and update the map
	 * @throws IOException
	 */
	public void RewriteFile(String newJS) throws IOException {
		File input = new File("resources/site/index2.html");
		Document doc = Jsoup.parse(input, "UTF-8", "http://LicorneForever.com/");
		Elements scripts = doc.getElementsByTag("script");
		for (Element element : scripts) {
			for (DataNode node : element.dataNodes()) {
				// System.out.println(node.getWholeData());
				node.setWholeData(newJS);
			}
		}

		String newHtml = doc.outerHtml();
		// System.out.println(newHtml);
		FileWriter fooWriter = new FileWriter(input, false);
		fooWriter.write(newHtml);
		fooWriter.close();
	}

	public static void main(String args[]) throws IOException {
		HTMLWriter h = new HTMLWriter();
		PlaceSearcher ps = new PlaceSearcher();

		/*
		 * InputStream boulangerie = ps.searchPlace("rennes", "boulangerie");
		 * InputStream boucher = ps.searchPlace("rennes", "boucher");
		 * InputStream pharmacie = ps.searchPlace("rennes", "pharmacie");
		 * 
		 * ps.writeToFile(boulangerie, "boulangerie.json");
		 * ps.writeToFile(boucher, "boucher.json"); ps.writeToFile(pharmacie,
		 * "pharmacie.json");
		 */

		ArrayList<Place> ListBoulangerie = ps.parseResult("boulangerie.json", "boulangerie");
		ArrayList<Place> ListBoucher = ps.parseResult("boucher.json", "boucherie");
		ArrayList<Place> ListPharmacie = ps.parseResult("pharmacie.json", "pharmacie");

		ArrayList<ArrayList<Place>> List_AllBat = new ArrayList<ArrayList<Place>>();

		List_AllBat.add(ListBoulangerie);
		List_AllBat.add(ListBoucher);
		List_AllBat.add(ListPharmacie);

		String js = h.CreateJSGeocode(List_AllBat);

		h.RewriteFile(js);

		ListBoulangerie.get(0).dropCard();
	}
}
