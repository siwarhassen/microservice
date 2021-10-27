package com.esprit.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
    //Activation ou désactivation de filtre
	@Override
	public boolean shouldFilter() {
		return true;
	}
   //Le taritement à exécuter lors de lancement de l'application
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		System.out.println(
				"Using Pre filter    Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());

		return null;
	}

}