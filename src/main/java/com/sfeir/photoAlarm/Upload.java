package com.sfeir.photoAlarm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Upload extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	
        Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
        BlobKey blobKey = blobs.get("photo");

        if (blobKey == null) {
            res.sendRedirect("/");
        } /*else {
            res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
        }*/
        
       
        saveBlobKey(blobKey.getKeyString());
        
        
        UploadOptions uploadOptions = UploadOptions.Builder.withGoogleStorageBucketName("bucket-photoalarm-sfeir");
        String uploadUrl = blobstoreService.createUploadUrl("/on_upload_success", uploadOptions);
        
        
    }
    
    public void saveBlobKey(String blobKey){
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    	Entity employee = new Entity("Photo");
    	employee.setProperty("blobKey", blobKey);
    	employee.setProperty("datePhoto",Calendar.getInstance().getTimeInMillis() );

    	datastore.put(employee);
    }
}
