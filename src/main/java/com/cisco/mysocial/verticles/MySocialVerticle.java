package com.cisco.mysocial.verticles;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.Datastore;

import com.cisco.mysocial.router.MySocialRouter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * @author Tulasinath</a>
 */
public class MySocialVerticle extends AbstractVerticle {

	public static Vertx vertxInstance;
	public static void main(String[] args) {
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		Vertx vertx = Vertx.factory.vertx(options);
		vertx.deployVerticle(new MySocialVerticle());

	}

	@Override
	public void start() {
		vertxInstance = vertx;
		MySocialRouter routerObj = new MySocialRouter();
		routerObj.setRouter(Router.router(vertx));
		routerObj.initialiseRoutes();
		vertx.createHttpServer().requestHandler(routerObj.getRouter()::accept).listen(7070);
		System.out.println("Server up : ***MySocial Ready***");
	}

}
