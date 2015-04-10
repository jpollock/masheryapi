package com.mashery.pm.api.sdk.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.security.cert.X509Certificate;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashery.pm.api.sdk.domain.Member;
import com.mashery.pm.api.sdk.gson.DeserializerString;



public class MasheryAPIBase {
	protected String propertiesFile = "/etc/mashery.api.conf";
	protected String protocol = "http";
	protected String api_key;
	protected String secret;
	protected String apiHost="api.mashery.com";
	protected String apiEndpoint = "/v2/json-rpc";
	protected String site;
	protected String area;
	protected Gson gson;
	protected boolean is_dashboard = false;
	protected int limit = 1000;
	
	public MasheryAPIBase() throws Exception {
		gson = new Gson();
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File(propertiesFile));
		prop.load(fis);
		this.api_key = prop.getProperty("api_key");
		this.secret = prop.getProperty("secret");
		this.site = prop.getProperty("site");
		

	}
	@SuppressWarnings("unchecked")
	public String callMasheryAPI(String method, String object, String select, String where, int page, int items) throws Exception
	{
		
		if (method == null)
			throw new NullPointerException();
		
		JSONObject obj=new JSONObject();
		obj.put("method",method);
		@SuppressWarnings("rawtypes")
		LinkedList list = new LinkedList();
		
		
		String queryString = "SELECT " + select + " from "+ object +  " " + where + " PAGE " + page + " ITEMS " + items;
		if (where == null || where.isEmpty())
			queryString = "SELECT " + select + " from "+ object +" PAGE " + page + " ITEMS " + items;

		list.add(queryString);
		obj.put("params",list);
		obj.put("id",1);
		return callMasheryAPI(obj.toJSONString());
		
	}
	

	@SuppressWarnings("unchecked")
	public String callMasheryAPI(String jsonString) throws Exception
	{
		//System.out.println(jsonString);
		StringBuffer buf = new StringBuffer();

		DefaultHttpClient client = new DefaultHttpClient();
		Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, null, null);
		SSLSocketFactory sf = new SSLSocketFactory(
				sslcontext,
		        SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme https = new Scheme("https", 443, sf);
		client.getConnectionManager().getSchemeRegistry().register(http);
		client.getConnectionManager().getSchemeRegistry().register(https);
	
		client = (DefaultHttpClient) wrapClient(client);
		long epoch = System.currentTimeMillis()/1000;
		String timestamp = Long.toString(epoch);
		String sig = MD5(api_key + secret + timestamp);  
		
		BasicHttpContext context = new BasicHttpContext();

		String urlStr = protocol + "://"+ apiHost + apiEndpoint;
		String urlParams ="apikey=" + api_key + "&sig=" + sig + (area != null ? "&mashery_area=" + area : "");
		
		urlStr = urlStr +  (site != null ? "/" + site : "")+"?" + urlParams;	
		HttpPost httpPost = new HttpPost(urlStr);
		StringEntity postbody = new StringEntity(jsonString);
		httpPost.setEntity(postbody);
		
        HttpResponse response = client.execute(httpPost, context);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
        	 InputStream instream = entity.getContent();
        	 if (response.getStatusLine().getStatusCode() != 200) {
        		 System.out.println(response.getStatusLine().getStatusCode() + "\n" + response.getStatusLine().getReasonPhrase());
        		 throw new Exception();
        	 }
             try {
            	 
                 BufferedReader reader = new BufferedReader(
                         new InputStreamReader(instream));
                String inputLine;
             	while ((inputLine = reader.readLine()) != null)
        	    {
        			buf.append(inputLine);
        	    }

             } catch (RuntimeException ex) {

                 // In case of an unexpected exception you may want to abort
                 // the HTTP request in order to shut down the underlying
                 // connection and release it back to the connection manager.
                 httpPost.abort();
                 throw ex;

             } finally {

                 // Closing the input stream will trigger connection release
                 instream.close();
                 

             }
        }		

        return buf.toString();
		
	}
	public String getReportDataAsString( String reportResource, String format, String startDateStr, String endDateStr, int[] filtercols) throws Exception
	{
		
		long epoch = System.currentTimeMillis()/1000;
		String timestamp = Long.toString(epoch);
		String sig = MD5(api_key + secret + timestamp);  
		String urlStr = "http://"+ apiHost;
		String params ="apikey=" + api_key + "&sig=" + sig + "&format="+format +  "&start_date=" + encode(startDateStr) +"&end_date="+ encode(endDateStr);

		StringBuffer returnData = new StringBuffer();
		
		try { 
			URL url = new URL(urlStr + "/v2/rest/"+site + "/reports/" + reportResource +"?" + params);
			URLConnection connection = url.openConnection();
			connection.setReadTimeout(0); //10 Sec
		
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine = null;
		
			while ((inputLine = in.readLine()) != null)
		    {
				if (inputLine.endsWith(","))
					inputLine = inputLine.substring(0,inputLine.length()-1);
				
				if (format.equals("csv"))
				{
					returnData.append(inputLine).append("\r");	
				} else {
					returnData.append(inputLine);
				}
				
				  
		    }
			
			in.close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
			
		}
		return returnData.toString();
	}
	
	/*	
	public String callMasheryAPI(String jsonString) throws Exception
	{
		
		long epoch = System.currentTimeMillis()/1000;
		String timestamp = Long.toString(epoch);
		String sig = MD5(api_key + secret + timestamp);  
		
		String urlStr = protocol + "://"+ apiHost + apiEndpoint;
		String urlParams ="apikey=" + api_key + "&sig=" + sig;
		
		urlStr = urlStr + "/" + site +"?" + urlParams;	

		URL url = new URL(urlStr);
		
		URLConnection connection = url.openConnection();
		if (protocol.equals("https"))
		{
			HttpsURLConnection connection2 = (HttpsURLConnection) url.openConnection(); 

			connection2.setHostnameVerifier(new HostnameVerifier() {        
			    public boolean verify(String hostname, SSLSession session)  {  
				return true;
			    }
			});
			
			connection = connection2;
		}
		
		connection.setDoOutput(true); // Triggers POST.
		connection.setDoInput(true);
		connection.setReadTimeout(0);
		
		connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		connection.setRequestProperty("Content-Type", "application/json");
		OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

		wr.write(jsonString);
		wr.flush();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer buf = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
	    {
			buf.append(inputLine);
			  
	    }
		
		return buf.toString();
	}
	*/
		
	@SuppressWarnings("rawtypes")
	public int getResultTotalPages(String result) throws Exception
	{
		 Object obj=JSONValue.parse(result);
		 Map map=(Map)obj;
	     Iterator iter = map.entrySet().iterator();
		 while(iter.hasNext()){
		      Map.Entry entry = (Map.Entry)iter.next();
		      String key = (String) entry.getKey();
		    
		      if (key.equals("result"))
		      {
		    	  JSONObject jobj=(JSONObject)entry.getValue();
		    	  Iterator iter2 =  jobj.entrySet().iterator();
		    	  
		    	  while (iter2.hasNext())
		    	  {
		    		  Map.Entry entry2 = (Map.Entry) iter2.next();
		    		  entry2.getValue();
		    		  if (entry2.getKey().equals("total_pages"))
		    		  {
		    			  Long val = (Long) entry2.getValue();
		    			  return val.intValue();
		    		  }
		    	  }
		    	 
		      }
		      
		 }
		 return 0;
	}
	
	@SuppressWarnings("rawtypes")
	public int getResultTotalItems(String result) throws Exception
	{
		 Object obj=JSONValue.parse(result);
		 Map map=(Map)obj;
		 Iterator iter = map.entrySet().iterator();
		 while(iter.hasNext()){
		      Map.Entry entry = (Map.Entry)iter.next();
		      String key = (String) entry.getKey();
		    
		      if (key.equals("result"))
		      {
		    	  JSONObject jobj=(JSONObject)entry.getValue();
		    	  Iterator iter2 =  jobj.entrySet().iterator();
		    	  
		    	  while (iter2.hasNext())
		    	  {
		    		  Map.Entry entry2 = (Map.Entry) iter2.next();
		    		  entry2.getValue();
		    		  if (entry2.getKey().equals("total_items"))
		    		  {
		    			  Long val = (Long) entry2.getValue();
		    			  return val.intValue();
		    		  }
		    	  }
		    	 
		      }
		      
		 }
		return 0;
	}
	
	private static void printHeaders(Header[] headers) {
		   for (int i=0; i<headers.length; i++) {
				System.out.println("\t" + headers[i].getName() + ":" + headers[i].getValue());
		   }
	}
	private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 
 
    public static String MD5(String text) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    
    public static String encode(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }


	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	
	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}
	public void setApiEndpoint(String apiEndpoint) {
		this.apiEndpoint = apiEndpoint;
	}
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isIs_dashboard() {
		return is_dashboard;
	}

	public void setIs_dashboard(boolean is_dashboard) {
		this.is_dashboard = is_dashboard;
	} 
	public void setLimit(int limit) {
		this.limit=limit;
	}

	public int getLimit() {
		return limit;
	}
	
	public HttpClient wrapClient(HttpClient base) {
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }

				public void checkClientTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        return null;
	    }
	}
}
