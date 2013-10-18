package com.sfeir.photoAlarm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class ViderCache
 */
public class ViderCache extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViderCache() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletOutputStream outputStream = response.getOutputStream();
	try {
		List<String> result = deleteAll();
		outputStream.print("<h1>Le cache a été vidé !</h1>");
		outputStream.print("<ul>");
		for (String string : result) {
			outputStream.print("<li>" + string + "</li>");			
		}
		outputStream.print("</ul>");
		
	} catch (Exception e) {		
		outputStream.print("<h1 style=\"color : red;\">Erreur : Merci de consulter le code !</h1>");
		e.printStackTrace();
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	 public List<String> deleteAll(){
	    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    	Query query = new Query("Photo");
	    	List<String> result = new ArrayList<String>();
	    	PreparedQuery prepare = datastore.prepare(query);
	    	for (Entity entity : prepare.asIterable()) {
				Object property = entity.getProperty("blobKey");
				BlobKey blobKey = new BlobKey(property.toString());
				result.add(property.toString());
				blobstoreService.delete(blobKey);
			}
	    	return result;
	    }

}
