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

@WebServlet(urlPatterns="/padrao")
public class FacebookServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String XML_HEADING = "<?xml version='1.0'?>\n ";
	private static final String NO_RESPONSE = "Error: No response. ";
	
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacebookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no ServletPadrao");
		
		String requestString = "";
		String responseString = sendRequestGet(requestString, "http://search.twitter.com/search.json?q=%40apple");
		try {
			//Document xmlResponse =	DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(responseString)));
			response.getWriter().print("Hello World via Servlet:<br/>"+responseString);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	private String sendRequestGet(String request, String uri) throws IOException, HttpException {
		GetMethod method = new GetMethod(uri);
		method.setRequestHeader("Content-type", "application/xml");
		try {
			new HttpClient().executeMethod(method);
			return new String(method.getResponseBody(),"UTF-8");
		} finally {
		method.releaseConnection();
		}
	}
	
	
	private String sendRequestPost(String request, String uri) throws IOException, HttpException {
		PostMethod method = new PostMethod(uri);
		method.setRequestHeader("Content-type", "application/xml");
		method.setRequestBody(XML_HEADING + request);
		try {
			new HttpClient().executeMethod(method);
			return new String(method.getResponseBody(),"UTF-8");
		} finally {
		method.releaseConnection();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doPost no ServletPadrao");
	}
}
