package com.act.trashcan;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class test {
	 public static void main(String[] args) throws IOException {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
			    public X509Certificate[] getAcceptedIssuers(){return null;}
			    public void checkClientTrusted(X509Certificate[] certs, String authType){}
			    public void checkServerTrusted(X509Certificate[] certs, String authType){}
			}};

			// Install the all-trusting trust manager
			try {
			    SSLContext sc = SSLContext.getInstance("TLS");
			    sc.init(null, trustAllCerts, new SecureRandom());
			    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			    ;
			}
		 String path="https://cloud.culture.tw/frontsite/inquiry/eventInquiryAction.do?method=showEventDetail&uid=59c2bf4a6e5e731960abaa61";
	 
		 Document doc = Jsoup.connect(path).get();
		 Element content = doc.select("div.Info_img>img[src]").first(); 
		 String res="https://cloud.culture.tw"+content.toString().substring(content.toString().indexOf("/"), content.toString().indexOf("\" "));
		 System.out.println(res);
 
	 }
}
