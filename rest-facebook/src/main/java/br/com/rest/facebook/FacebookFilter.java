package br.com.rest.facebook;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter(urlPatterns="*#*")
public class FacebookFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		System.out.println("request: "+request.getRequestURL());
		System.out.println("request: "+request.getContextPath());
		System.out.println(request.getPathInfo());
		System.out.println(request.getAttributeNames());
		System.out.println(request.getParameterNames());
		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Iniciando o FacebookFilter");
	}
}
