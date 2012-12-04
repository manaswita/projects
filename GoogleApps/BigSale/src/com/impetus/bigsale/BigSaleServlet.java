package com.impetus.bigsale;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

@SuppressWarnings("serial")
public class BigSaleServlet extends HttpServlet {

	String postBody = "";

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		SpreadsheetService service = new SpreadsheetService("com.impetus.bigsale");
		
		try {
		    String urlString = "https://spreadsheets.google.com/feeds/list/0Amj1s1T59vR0dFZTcUpOV3FER01aUEhuM1hyaWJxZkE/od6/public/values";
		      // turn the string into a URL
		      URL url = new URL(urlString);

		      ListFeed feed = service.getFeed(url, ListFeed.class);
		      
		      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		        
		        Query query = new Query("Offers");

		        ArrayList<Key> keys = new ArrayList<Key>();

		        for ( Entity entity: datastore.prepare(query).asIterable(FetchOptions.Builder.withDefaults())) {
		         keys.add(entity.getKey());
		        }


		        keys.trimToSize();
		        datastore.delete(keys);

		        Entity offer2 = new Entity("Offers");
		        DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
		        
		        
		        for (ListEntry entry : feed.getEntries()) {
		        	 CustomElementCollection elements = entry.getCustomElements();
		        	 
	            	offer2 = new Entity("Offers");
	            	String category = elements.getValue("Category");
		            offer2.setProperty("Category", category);
		            offer2.setProperty("Retailer", elements.getValue("Retailer"));
		            offer2.setProperty("Offer", elements.getValue("Offer"));
		            offer2.setProperty("City", elements.getValue("City"));
		            offer2.setProperty("StartDate", elements.getValue("StartDate"));
		            offer2.setProperty("EndDate", elements.getValue("EndDate"));
		    
		            datastore2.put(offer2);
		        }
		    } catch (IOException e) {
		      e.printStackTrace();
		    } catch (ServiceException e) {
		      e.printStackTrace();
		    }
        
        
		
        process(req, resp);	
	}
	/*public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		String url="http://splash-art.blogspot.in";
		
        System.out.println("Fetching %s..."+ url);

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        Query query = new Query("Offers");

        ArrayList<Key> keys = new ArrayList<Key>();

        for ( Entity entity: datastore.prepare(query).asIterable(FetchOptions.Builder.withDefaults())) {
         keys.add(entity.getKey());
        }


        keys.trimToSize();
        datastore.delete(keys);

        Entity offer2 = new Entity("Offers");
        DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
        
        
        for (Element src : media) {
        	
            if (src.tagName().equals("img")){
	            	//System.out.println(src.tagName()+"-------"+src.attr("src"));
	            else
	            	System.out.println(src.tagName()+"*******"+ src.attr("abs:src"));
            	offer2 = new Entity("Offers");
	            
	            offer2.setProperty("alt", src.attr("alt")+req.getParameter("value1"));
	            offer2.setProperty("image", src.attr("src"));
	    
	
	            datastore2.put(offer2);
            }
        }
		
        process(req, resp);	
	}*/
	
	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		resp.setContentType("text/plain");

		List<JSONObject> list = new ArrayList<JSONObject>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query fetchQuery = new Query("Offers");

		List<Entity> pages = new ArrayList<Entity>();

		pages =  datastore.prepare(fetchQuery).asList(FetchOptions.Builder.withDefaults());
		for (Entity beer : pages) {
			// Add the pojo as a JSONObject
			list.add(new JSONObject(beer));
		}
		// Create a JSONArray based from the list of JSONObejcts
		JSONArray jsonArray = new JSONArray(list);
		// Then output the JSON string to the servlet response
		System.out.println(jsonArray.toString());
		resp.getWriter().println(jsonArray.toString());
	}

}