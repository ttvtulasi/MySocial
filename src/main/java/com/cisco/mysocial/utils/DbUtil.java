package com.cisco.mysocial.utils;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DbUtil {
	private static ThreadLocal<Datastore> mongoTL = new ThreadLocal<Datastore>();

	/**
	 * Method to retrieve a mongo database client from the thread local storage
	 *
	 * @return
	 */
	public static Datastore getMongoDB() {
		if (mongoTL.get() == null) {
			MongoClientURI connectionString = new MongoClientURI("mongodb://172.17.42.1:27017");
			MongoClient mongoClient = new MongoClient(connectionString);
			
			Morphia morphia = new Morphia();
			morphia.mapPackage("com.mysocial.model");
			
			Datastore datastore = morphia.createDatastore(mongoClient, "mySocialDB");
			datastore.ensureIndexes();
			mongoTL.set(datastore);
			return datastore;
		}
		return mongoTL.get();
	}
}
