package com.qa.servlet;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;

import util.JSONUtil;

@WebServlet(urlPatterns=("/Create"))
public class Create extends HttpServlet{
	
	AccountDBRepository repo = new AccountDBRepository();
	JSONUtil json= new JSONUtil();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String firstName=request.getParameter("firstName");
		String surname=request.getParameter("surname");
		Long accountNumber=Long.parseLong(request.getParameter("accountNumber"));
		
		Account account = new Account(firstName,surname,accountNumber);
		String accountAsJSON=json.getJSONForObject(account);
		
		
		//
		CloseableHttpClient client=HttpClients.createDefault();
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/Application-Management-App/api/account/json/");

		// Request parameters and other properties.
		//List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		//params.add(new BasicNameValuePair("param-1", accountAsJSON));
		//httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		
		StringEntity entity=new StringEntity(accountAsJSON);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    
	    CloseableHttpResponse res = client.execute(httpPost);
	    //assertThat((((HttpResponse) response).getStatusLine().getStatusCode()), equal(200));
	    client.close();
	    
		
		//Execute and get the response.
		//HttpResponse res = httpclient.execute(httppost);
		//HttpEntity entity = res.getEntity();

		//if (entity != null) {
		//    InputStream instream = entity.getContent();
		//    try {
		//    	PrintWriter out=response.getWriter();
		//		out.println(instream);
		//    } finally {
		//        instream.close();
		//    }
	//	}
		
	}
}
