package com.sfeir.photoAlarm;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class ListDataBase
 */
public class ListDataBase extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListDataBase() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		printAll(outputStream);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void printAll(ServletOutputStream outputStream) throws IOException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("Photo");
		PreparedQuery prepare = datastore.prepare(query);
		outputStream.println("<table>");
		outputStream.println("<tr>");

		outputStream.println("<th>");
		outputStream.println("Key");
		outputStream.println("</th>");

		outputStream.println("<th>");
		outputStream.println("Date");
		outputStream.println("</th>");

		outputStream.println("</tr>");
		for (Entity entity : prepare.asIterable()) {
			String property = (String) entity.getProperty("blobKey");
			Long datePhoto = (Long) entity.getProperty("datePhoto");
			outputStream.println("<tr>");

			outputStream.println("<td>");
			outputStream.println(property);
			outputStream.println("</td>");

			outputStream.println("<td>");
			outputStream.println(new Timestamp(datePhoto).toString());
			outputStream.println("</td>");

			outputStream.println("</tr>");
		}
		outputStream.println("</table>");
	}

}
