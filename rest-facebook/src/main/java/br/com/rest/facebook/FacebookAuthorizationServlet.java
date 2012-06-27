package br.com.rest.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.RedirectException;

@WebServlet(urlPatterns="/authorization")
public class FacebookAuthorizationServlet  extends HttpServlet{
	
	private static final long serialVersionUID = -1022348709105431372L;
	private static final String TOKEN_URL = "https://graph.facebook.com/oauth/access_token?";
	private static final String APP_ID="client_id=181926225207193&";
	private static final String YOUR_REDIRECT_URI="redirect_uri=http://localhost:8080/teste/token/&";
	private static final String YOUR_APP_SECRET= "client_secret=a8d0dc676bce758bf2bec94ca96434f2&";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no FacebookAuthorizationServlet");
		System.out.println("request.getQueryString(): "+request.getQueryString());
		String chamada = TOKEN_URL+APP_ID+YOUR_REDIRECT_URI+YOUR_APP_SECRET+request.getQueryString();
		System.out.println("Chamada: "+chamada);
		response.sendRedirect(chamada);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no FacebookAuthorizationServlet");
	}

}
