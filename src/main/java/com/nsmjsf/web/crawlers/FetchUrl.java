package com.nsmjsf.web.crawlers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.nsmjsf.web.datamodels.Proxy;



public class FetchUrl {
	private String sUrl;
	private String categoryName;
	private boolean isFetched = false;
	private static UrlValidator validator = new UrlValidator();
	private static final Log log = LogFactory.getLog(FetchUrl.class);
	private static int linksFetched = 0;
	private int fetchAttempt = 0;
	

	public FetchUrl(String url) {
		this.sUrl = url;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getsUrl() {
		return sUrl;
	}

	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	public boolean isFetched() {
		return isFetched;
	}

	public void setFetched(boolean isFetched) {
		this.isFetched = isFetched;
	}

	public Document fetchDoc() {
		Document doc = null;

		if (!validator.isValid(sUrl)) {
			log.error("Invalid Url:" + sUrl);
			this.isFetched = true;
			return null;
		}

		do {

			try {
				this.fetchAttempt++;
				log.info("Fetching Url[attempt:" + fetchAttempt + "]:"
						+ this.sUrl);
				doc = Jsoup.connect(this.sUrl).userAgent("Mozilla")
						.timeout(1000 * 60).get();
				this.linksFetched++;
				this.isFetched = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.isFetched = false;
			}
			if (fetchAttempt > 5) {
				break;
			}

		} while ((!this.isFetched));
		return doc;

	}

	public synchronized Document fetchDoc(HashMap<String, String> params) {
		Document doc = null;

		if (!validator.isValid(sUrl)) {
			log.error("Invalid Url:" + sUrl);
			this.isFetched = true;
			return null;
		}

		do {

			try {
				this.fetchAttempt++;
				log.info("Fetching Url[attempt:" + fetchAttempt + "]:"
						+ this.sUrl);
				doc = Jsoup.connect(this.sUrl).userAgent("Mozilla")
						.timeout(1000 * 60).data(params).post();
				// log.info(doc.toString());
				this.linksFetched++;
				this.isFetched = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.isFetched = false;
			}
			if (this.fetchAttempt > 5) {
				break;
			}

		} while ((!this.isFetched));
		return doc;

	}

	public  Document proxyFetch(HashMap<String, String> params) throws IOException
			 {
		
		HttpURLConnection connection=this.getConnection(params);
		if (connection!=null) {

			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}

			Document doc = Jsoup.parse(String.valueOf(tmp));
			return doc;
		}

		return null;
		// Jsoup.connect("").
	}
   private HttpURLConnection getConnection(HashMap<String,String> params) throws MalformedURLException, IOException{
      HttpURLConnection uc = null;
      URL url = new URL(this.sUrl);
      Proxy dbProxy = null;
      String urlParameters  = this.getParameterQuery(params);
      log.info("Query params"+urlParameters);
      int status=0;
      
	do{
		try{
			 dbProxy = ProxyManager.getInstance().getProxy();
			 log.info("Trying proxy "+dbProxy.getProxyIp()+"to port "+dbProxy.getProxyPort());
			java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress(dbProxy.getProxyIp(),
							dbProxy.getProxyPort())); // or whatever your proxy is
		    uc = (HttpURLConnection) url.openConnection(proxy);
		    uc.setReadTimeout(10000);
		    uc.setConnectTimeout(15000);
		    uc.setRequestMethod("POST");
		    uc.setDoInput(true);
		    uc.setDoOutput(true);
		    uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");

		   
		    byte[] postData       = urlParameters.getBytes( Charset.forName( "UTF-8" ));
		    int    postDataLength = postData.length;
		    uc.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		    uc.setRequestProperty( "charset", "utf-8");
		    uc.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		    uc.setUseCaches( false );
		    try( DataOutputStream wr = new DataOutputStream( uc.getOutputStream())) {
		       wr.write( postData );
		    }
		    
		    
		 
			uc.connect();

		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		status=uc.getResponseCode();
		if(status==200){
			ProxyManager.getInstance().onProxySuccess(dbProxy);
			
		}else{
			ProxyManager.getInstance().onProxyFailure(dbProxy);
			
		}
				
      }while( status!= 200);
	
	
	 return uc;
   }
   private String getParameterQuery(HashMap<String, String> params){
	   String query="";
	   Iterator<Entry<String, String>> it = params.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<String,String> pair = it.next();
	        query=query+pair.getKey() + "=" + pair.getValue();
	        if(it.hasNext()){
	        	query=query+"&";
	        }
	        
	    }
	    return query;
   }
  
}
