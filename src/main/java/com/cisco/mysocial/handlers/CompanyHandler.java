package com.cisco.mysocial.handlers;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class CompanyHandler {
	
	public void getCompanies(RoutingContext routingContext) {
		System.out.println("Get All getCompanies called");
		HttpServerResponse response = routingContext.response();
    	response.setStatusCode(200).end("[{\"id\":\"55716669eec5ca2b6ddf5626\",\"companyName\":\"Acme Inc\",\"subdomain\":\"acme\"},{\"id\":\"559e4331c203b4638a00ba1a\"\n,\"companyName\":\"Acme Inc\",\"subdomain\":\"acme\"}]");
		System.out.println("tulasi");
	}
	

	public void getDepartments(RoutingContext routingContext) {
		System.out.println("Get All getDepartments for sites");
		HttpServerResponse response = routingContext.response();
		response.setStatusCode(200).end("[{\"id\":\"55716669eec5ca2b6ddf5628\",\"deptName\":\"Sales\",\"siteId\":\"55716669eec5ca2b6ddf5627\"}]");
	}
	
	public void getSiteForCompany(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
    	response.setStatusCode(200).end("[{\"id\":\"55716669eec5ca2b6ddf5627\",\"siteName\":\"Acme Inc\",\"companyId\":\"55716669eec5ca2b6ddf5626\",\"subdomain\"\n:\"acme\"}]");
		
	}
}
