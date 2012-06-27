package br.com.rest.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

@WebServlet(urlPatterns="/token")
public class FacebookTokenServlet   extends HttpServlet{
	
	private static final long serialVersionUID = 2366078473062814997L;
	private static final String TOKEN_URL = "https://graph.facebook.com/oauth/access_token?";
	private static final String APP_ID="client_id=181926225207193&";
	private static final String YOUR_REDIRECT_URI="redirect_uri=http://localhost:8080/teste/token&";
	private static final String YOUR_APP_SECRET= "client_secret=a8d0dc676bce758bf2bec94ca96434f2&";
	private static final String FRIENDS = "https://graph.facebook.com/me/friends?";
	static int contador = 0;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no FacebookAuthorizationServlet");
		System.out.println("request.getQueryString(): "+request.getQueryString());
		String chamada = TOKEN_URL+APP_ID+YOUR_REDIRECT_URI+YOUR_APP_SECRET+request.getQueryString();
		System.out.println(contador+++"- Chamada: "+chamada);
		String acessToken = sendRequestPost(null, chamada);
		System.out.println("acessToken: "+acessToken);
		String friends = sendRequestGet(null, FRIENDS+acessToken);;
		response.getWriter().print("Hello World via Servlet:\n"+acessToken+"\n FRIENDS: "+friends);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no FacebookAuthorizationServlet");
	}
	
	private String sendRequestPost(String request, String uri) throws IOException, HttpException {
		System.out.println("Enviando um Post: "+uri);
		PostMethod method = new PostMethod(uri);
		try {
			new HttpClient().executeMethod(method);
			return new String(method.getResponseBody(),"UTF-8");
		} finally {
		method.releaseConnection();
		}
	}
	
	private String sendRequestGet(String request, String uri) throws IOException, HttpException {
		System.out.println("Enviando um Get: "+uri);
		GetMethod method = new GetMethod(uri);
		try {
			new HttpClient().executeMethod(method);
			return new String(method.getResponseBody(),"UTF-8");
		} finally {
		method.releaseConnection();
		}
	}
	


}
