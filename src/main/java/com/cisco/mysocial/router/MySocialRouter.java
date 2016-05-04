package com.cisco.mysocial.router;

import com.cisco.mysocial.handlers.BlogHandler;
import com.cisco.mysocial.handlers.CompanyHandler;
import com.cisco.mysocial.handlers.UserHandler;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class MySocialRouter {
	
	private UserHandler userHandlerObj = new UserHandler();
	private BlogHandler blogHandlerObj = new BlogHandler();
	private CompanyHandler companyHandlerObj = new CompanyHandler();
	
	private Router router;// add vertex here

	public Router getRouter() {
		return router;
	}

	public void setRouter(Router router) {
		this.router = router;
	}
	
	public void initialiseRoutes(){
		router.route().handler(BodyHandler.create());
		router.get("/Services/rest/blogs").handler(blogHandlerObj::getAllBlogs);
		router.get("/Services/rest/user").handler(userHandlerObj::getUserSignedStatus);
		//router.get("/Services/rest/blogs?tag=").handler(null);
		
		router.post("/Services/rest/blogs/:blogId/comments").handler(blogHandlerObj::postComment);
		router.get("/Services/rest/company").handler(companyHandlerObj::getCompanies);
		router.get("/Services/rest/company/:id/sites").handler(companyHandlerObj::getSiteForCompany);
		router.post("/Services/rest/user/auth").handler(userHandlerObj::validateUser);
		router.post("/Services/rest/user/register").handler(userHandlerObj::registerUser);
		router.get("/Services/rest/company/:companyId/sites/:id/departments").handler(companyHandlerObj::getDepartments);
		router.route().handler(StaticHandler.create()::handle);
	}

}
