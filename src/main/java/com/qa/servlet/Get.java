package com.qa.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

@WebServlet(urlPatterns=("/Get"))
public class Get extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Long accountNumber=Long.parseLong(request.getParameter("accountNumber1"));
		
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("http://localhost:8080/Application-Management-App/api/account/json/"+accountNumber);
		HttpResponse res=httpclient.execute(httpGet);
		
		BufferedReader br=new BufferedReader
				(new InputStreamReader( res.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line="";
		while ((line=br.readLine())!=null) {
			result.append(line);
		}
		PrintWriter out= response.getWriter();
		if (result.length()<5) {
			out.println("Account does not exist");
		}else {
		out.println(result);
		}
	}
}
