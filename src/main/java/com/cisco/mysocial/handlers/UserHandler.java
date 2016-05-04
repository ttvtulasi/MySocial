package com.cisco.mysocial.handlers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.cisco.mysocial.dto.UserDTO;
import com.cisco.mysocial.model.Company;
import com.cisco.mysocial.model.User;
import com.cisco.mysocial.utils.DbUtil;
import com.cisco.mysocial.verticles.MySocialVerticle;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class UserHandler extends AbstractVerticle {
	Logger logger = Logger.getLogger(UserHandler.class.getName());

	public void getUserSignedStatus(RoutingContext routingContext) {
		System.out.println(" getUserSignedStatus Get All Blogs called");

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json");
		MySocialVerticle.vertxInstance.executeBlocking(future -> {
			// Call some blocking API that takes a significant amount of time to
			// return

			future.complete(null);
		} , res -> {

			response.end(
					"[{\"first\":\"Maruthi\",\"id\":\"55716669eec5ca2b6ddf5629\",\"password\":\"abc123\",\"companyId\":null,\"email\":\"maruthi @abc.com\",\"deptId\":null,\"isCompany\":null,\"companyName\":null,\"subdomain\":null,\"deptName\":null,\"siteId\":\"55716669eec5ca2b6ddf5627\",\"last\":\"RJ\",\"userName\":\"maruthirj\"}]");
			System.out.println("The result is: ");
		});

	}

	public void authenticateUser(RoutingContext routingContext) {
		System.out.println("Authentication User Started");
		HttpServerResponse response = routingContext.response();
		String jsonStr = routingContext.getBodyAsString();
		response.putHeader("content-type", "application/json");
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readValue(jsonStr, JsonNode.class);
			JsonNode userName = jsonNode.get("userName");
			String userNameString = userName.asText();

			JsonNode passwordNode = jsonNode.get("password");
			String passwordString = passwordNode.asText();

			// if (userNameString.equalsIgnoreCase("ttv") &&
			// passwordString.equalsIgnoreCase("cisco")) {
			// System.out.println("Logged In Success Full");
			// response.setStatusCode(200).end();
			// } else {
			// response.setStatusCode(401).end("not found");
			// }
			// Datastore ds = DbUtil.getMongoDB();
			// //List<User> users = ds.createQuery(User.class).filter("userName
			// ==", userNameString).asList();
			// Query<UserDTO> q = ds.find(UserDTO.class, "userName",
			// userNameString);
			//
			// UserDTO fromDB = q.get();
			// if (fromDB.getUserName().equals(userNameString) &&
			// fromDB.getPassword().equals(passwordString)){
			// routingContext
			// .response()
			// .setStatusCode(201)
			// .putHeader("content-type",
			// "application/json; charset=utf-8").end();
			// System.out.println("User Authenticated");
			// }

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean validateUser(RoutingContext routingContext) {
		logger.log(Level.INFO, "Inside validateUser API!! " + routingContext.getBodyAsString());
		try {

			ObjectMapper mapper = new ObjectMapper();
			UserDTO dto = mapper.readValue(routingContext.getBodyAsString(), UserDTO.class);
			logger.log(Level.INFO, "The given userName is :" + dto.getUserName());
			Datastore ds = DbUtil.getMongoDB();
			Query<User> q = ds.find(User.class, "userName", dto.getUserName());
			if ((q != null) && (q.get() != null) && q.get().getUserName() != null && q.get().getPassword() != null
					&& (q.get().getUserName().equals(dto.getUserName()))
					&& (q.get().getPassword().equals(dto.getPassword()))) {
				logger.log(Level.INFO, "User Successfully Authenticated !!! " + routingContext.getBodyAsString());
				routingContext.response().setStatusCode(201)
						.putHeader("content-type", "application/json; charset=utf-8").end();
				return true;
			} else {
				logger.log(Level.INFO, "User Not Authenticated !!! " + routingContext.getBodyAsString());
				return false;

			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, " Exception while validating the user!!! " + e.getMessage());
		}
		return false;
	}

	public void registerUser(RoutingContext routingContext) {
		System.out.println("registerUser ");
		HttpServerResponse response = routingContext.response();

		MySocialVerticle.vertxInstance.executeBlocking(future -> {
			String jsonStr = routingContext.getBodyAsString();
			JsonNode jsonNode = null;
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				jsonNode = objectMapper.readValue(jsonStr, JsonNode.class);
			} catch (JsonParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JsonNode isCompanyNode = jsonNode.get("isCompany");

			if (isCompanyNode == null) {
				UserDTO dto = null;
				ObjectMapper mapper = new ObjectMapper();
				try {
					dto = mapper.readValue(jsonStr, UserDTO.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
				User u = dto.toModel();
				Datastore dataStore = DbUtil.getMongoDB();
				dataStore.save(u);
				future.complete("");
			} else {
				UserDTO dto = null;
				ObjectMapper mapper = new ObjectMapper();
				try {
					dto = mapper.readValue(jsonStr, UserDTO.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Company c = dto.toCompanyModel();
				Datastore dataStore = DbUtil.getMongoDB();
				dataStore.save(c);
				future.complete("");
			}
		} , res -> {
			System.out.println("Data Persisted");
			response.setStatusCode(204).end("Data saved");

		});

	}
}
