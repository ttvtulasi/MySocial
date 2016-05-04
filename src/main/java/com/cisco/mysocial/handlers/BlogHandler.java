package com.cisco.mysocial.handlers;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.cisco.mysocial.model.Blog;

import com.cisco.mysocial.utils.DbUtil;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class BlogHandler {

	public void getAllBlogs(RoutingContext routingContext) {
		System.out.println("Get All Blogs called");

		HttpServerResponse response = routingContext.response();
		String id = routingContext.request().getParam("id");
		response.putHeader("content-type", "application/json");
		Datastore dataStore = DbUtil.getMongoDB();
		System.out.println("dataStore" + dataStore.createQuery(Blog.class));

		// List<Blog> blogs = dataStore.createQuery(Blog.class).asList();
		// if (blogs.size() != 0) {
		// UserDTO dto = new UserDTO().fillFromModel(users.get(0));
		// ObjectMapper mapper = new ObjectMapper();
		// JsonNode node = mapper.valueToTree(dto);
		// response.putHeader("content-type", "application/json");
		// response.end(node.toString());
		// } else {
		// response.setStatusCode(404).end("not found");
		// }

//		List<Blog> blogs = dataStore.createQuery(Blog.class).asList();
//		if (blogs.size() > 0) {
//			routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8")
//					.end(Json.encodePrettily(blogs));
//		} else {
//			routingContext.response().setStatusCode(201).putHeader("content-type", "application/json; charset=utf-8")
//					.end(Json.encodePrettily("No blogs found!!! Go ahead & create one."));
//		}
		response.putHeader("content-type", "application/json");
		response.setStatusCode(200).end(
				"[{\"id\":\"55716696eec5ca2b6ddf562f\",\"content\":\"Blog 2 text here Blog text here...\",\"tags\":\"abc,  lmn\",\"title\":\"blog2\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[{\"content\":\"Some comment\",\"blogId\":null,\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"559e4331c203b4638a00ba1d\",\"date\":1436435291623}],\"date\":1433495190426},{\"id\":\"55b75a08c203b410b0cc9c2c\",\"content\":\"THis is some blog about ferrari\",\"tags\":\"tag1,  ferrari\",\"title\":\"Some blog\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[{\"content\":\"Nice blog\",\"blogId\":null,\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"date\":1438079510294}],\"date\":1438079496982},{\"id\":\"55c44a3ceec5ca1702674335\",\"content\":\"THis is my newest blog content\",\"tags\":\"B1,  B2\",\"title\":\"My New Blog\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[{\"content\":\"Comment on my newest blog\",\"blogId\":null,\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"date\":1438927499769}],\"date\":1438927420291},{\"id\":\"55c44b04eec5ca1702674339\",\"content\":\"This is some other new blog\",\"tags\":\"C1,  C2\",\"title\":\"Some other blog\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[],\"date\":1438927620514},{\"id\":\"55716685eec5ca2b6ddf562c\",\"content\":\"Blog1 test Blog text here...\",\"tags\":\"abc,  xyz\",\"title\":\"blog1\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[{\"content\":\"thats great!\",\"blogId\":null,\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"date\":1460714985813}],\"date\":1433495173600},{\"id\":\"5710be266d97491091476944\",\"content\":\"Ferraris are good in red\",\"tags\":\"Ferrari\",\"title\":\"Demoblog\",\"userFirst\":\"Maruthi\",\"userLast\":\"RJ\",\"userId\":\"55716669eec5ca2b6ddf5629\",\"comments\":[],\"date\":1460715046876}]");
		// System.out.println("tulasi");
	}

	public void postComment(RoutingContext routingContext) {
		System.out.println("Posting Comment");
	}

}
