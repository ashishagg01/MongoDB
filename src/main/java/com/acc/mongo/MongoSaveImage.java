package com.acc.mongo;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * @author ashish.g.agarwal
 * 
 */
public class MongoSaveImage {
	public static void main(String[] args) {

		try {

			MongoClient mongo = new MongoClient();
			DB db = mongo.getDB("ashishdb");
			// DBCollection collection = db.getCollection("dummyColl");

			String newFileName = "new_image";

			File imageFile = new File(
					"C:\\Users\\ashish.g.agarwal\\Desktop\\Capture.PNG");
			
			System.out.println("create a photo namespace");
			// create a "photo" namespace
			GridFS gfsPhoto = new GridFS(db, "PHOTO_NAMESPACE");

			System.out.println("get image file from local drive");
			// get image file from local drive
			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);

			System.out.println("set a new filename for identify purpose");
			// set a new filename for identify purpose
			gfsFile.setFilename(newFileName);

			System.out.println("save the image file into mongoDB");
			// save the image file into mongoDB
			gfsFile.save();

			// print the result
			DBCursor cursor = gfsPhoto.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			// get image file by it's filename
			GridFSDBFile imageForOutput = gfsPhoto.findOne(newFileName);

			System.out.println("save the same image file on localdrive");
			// save it into a new image file
			imageForOutput
					.writeTo("C:\\Users\\ashish.g.agarwal\\Desktop\\CaptureNew.png");

			// remove the image file from mongoDB
			//gfsPhoto.remove(gfsPhoto.findOne(newFileName));

			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
