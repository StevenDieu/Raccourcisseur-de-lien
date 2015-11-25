package edu.fges.shorturl.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns="*")
public class FilterRedirect implements Filter {
	
	FilterConfig filterConfig = null;
	
	@Override
	public void destroy() {
		this.filterConfig = null;
			
	}

	@Override
	public void doFilter(ServletRequest resquest, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html");
		
		try {
			if (resquest instanceof HttpServletRequest) {
				HttpServletRequest httpResquest = (HttpServletRequest) resquest;

			}
			chain.doFilter(resquest, response);
			
		}
		finally{
			
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		
	}

}
