package serveur;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class PlaceSearcher {

	public PlaceSearcher() {

	}

	/**
	 * Allow to search a list of place in the city.
	 * 
	 * @param city:
	 *            where to search
	 * @param type:
	 *            what do you want
	 * @return: the inputstream of the json file
	 * @throws IOException
	 */
	public InputStream searchPlace(String city, String type) throws IOException {
		URL url = new URL("https://api.apipagesjaunes.fr/pros/find?what=" + type + "&where=" + city
				+ "&app_id=d140a6f6&app_key=26452728b034374bccb462e880bfb0e5");

		HttpsURLConnection cnx = (HttpsURLConnection) url.openConnection();
		cnx.setConnectTimeout(5000);
		cnx.setRequestMethod("GET");

		return cnx.getInputStream();
	}

	/**
	 * Write a file on the disk to reuse result without calling a second type
	 * the API
	 * 
	 * @param in:
	 *            the json file you want to write on the disk
	 * @throws IOException
	 */
	public void writeToFile(InputStream in, String where) throws IOException {
		BufferedReader dr = new BufferedReader(new InputStreamReader(in));

		// Ecriture du fichier
		FileOutputStream fos = new FileOutputStream(where);
		PrintStream fileWrite = new PrintStream(fos);

		String to_write = dr.readLine();
		while (to_write != null) {
			fileWrite.println(to_write);
			to_write = dr.readLine();
		}

		fos.close();
	}

	/**
	 * Parse a JSON file and stock the result in a place Array
	 * 
	 * @param file:
	 *            file Path
	 * @throws FileNotFoundException
	 */
	public ArrayList<Place> parseResult(String file, String type) throws FileNotFoundException {
		FileReader fr = new FileReader(file);
		JSONObject obj = (JSONObject) JSONValue.parse(fr);
		return parsingPlace(obj, type);
	}

	/**
	 * Parse a JSON file and stock the result in a place Array
	 * 
	 * @param file:
	 *            Input Stream of the file
	 */
	public ArrayList<Place> parseResult(InputStream file, String type) {
		InputStreamReader fr = new InputStreamReader(file);
		JSONObject obj = (JSONObject) JSONValue.parse(fr);
		return parsingPlace(obj, type);
	}

	/**
	 * C'est jolie une licorne
	 * 
	 * @param obj:
	 *            un objet json
	 */
	private ArrayList<Place> parsingPlace(JSONObject obj, String type) {
		JSONObject context = (JSONObject) JSONValue.parse(obj.get("context").toString());
		JSONObject results = (JSONObject) JSONValue.parse(context.get("results").toString());

		int nb_Lieu = Integer.parseInt(results.get("last_listing").toString());

		JSONObject result_list = (JSONObject) JSONValue.parse(obj.get("search_results").toString());
		JSONArray result_listening_list = (JSONArray) result_list.get("listings");
		
		ArrayList<Place> PlaceList = new ArrayList<Place>();
		for (int i = 0; i < nb_Lieu; i++) {
			JSONObject listening = (JSONObject) result_listening_list.get(i);

			JSONArray inscriptions_list = (JSONArray) listening.get("inscriptions");
			JSONObject inscriptions = (JSONObject) inscriptions_list.get(0);

			if (listening.get("merchant_name") != null && inscriptions.get("adress_street") != null) {
				String n = listening.get("merchant_name").toString();
				String addr = inscriptions.get("adress_street").toString();

				Place placeToAdd = new Place(n, addr, type);
				placeToAdd.InitCard();
				PlaceList.add(placeToAdd);
			}

		}

		return PlaceList;
	}

}
