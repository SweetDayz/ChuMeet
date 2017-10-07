package com.gen.tool;

import java.sql.*;
import oracle.jdbc.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import com.act.act.model.Act_VO;
import com.act.actPOI.model.ActPOIVO;
import com.google.gson.*;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class actJSON {		


	
	
		public static void main(String args[]) {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String userid = "BA103G2";
			String passwd = "a123";
			String INSERT_STMT = "insert into act(actType, actID, memID, actCreateDate, actName, actStatus, actPriID, actStartDate, actEndDate, actImg, actContent, actLong, actLat, actLocName, actAdr, actRef, actUID) "
															+ "values (2, act_seq.nextval, 0, systimestamp, ?, 1, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String POISQL="insert into actPOI values (?,?)";
			
			
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
			

			
			Connection con = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			
		    try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt2 = con.prepareStatement(POISQL);
				
				String json = readUrl("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&keyword=2013");
				
		    
			    Gson gson = new Gson();
			    JsonParser parser = new JsonParser();
			    JsonArray Jarray = parser.parse(json).getAsJsonArray();
			     
			    ArrayList<actGVO> actgs = new ArrayList<actGVO>();
			     
			    for(JsonElement obj : Jarray ){
			    	actGVO actg = (actGVO) gson.fromJson(obj , actGVO.class);
			    	System.out.println("cat: "+actg.getCategory());
			    	
			    	int showCount=actg.getShowInfo().size();
			    	for (int i=0; i <showCount; i++) {
							pstmt.setString(1, actg.getTitle());	//setActName
							System.out.println("actg.getShowInfo().get(i).getTime()="+actg.getShowInfo().get(i).getTime());
							pstmt.setTimestamp(2, com.gen.tool.tools.strToTimestampGson(actg.getShowInfo().get(i).getTime()));	//StartDate
							pstmt.setTimestamp(3, com.gen.tool.tools.strToTimestampGson(actg.getShowInfo().get(i).getEndTime()));	//endDate
									Blob blobbb=con.createBlob();
									byte[] bytesss=null;
									if	(!actg.getImageUrl().trim().equals("")){
										bytesss=com.gen.tool.tools.getUrlPictureByteArray(actg.getImageUrl());
									}else {
										String path = "E:\\\\DB\\\\POIBLOB\\"+actg.getCategory()+".jpg";
										bytesss=com.gen.tool.tools.getPictureByteArray(path);
									}
									blobbb.setBytes(1, bytesss);
							pstmt.setBlob(4, blobbb);							
							pstmt.setString(5, actg.getDescriptionFilterHtml());	//Content
							pstmt.setDouble(6, (Double) actg.getShowInfo().get(i).getLongitude());
							pstmt.setDouble(7, (Double) actg.getShowInfo().get(i).getLatitude());
							pstmt.setString(8, actg.getShowInfo().get(i).getLocationName());
							pstmt.setString(9, actg.getShowInfo().get(i).getLocation());	//adr
							pstmt.setString(10, actg.getWebSales());
							pstmt.setString(11, actg.getUID());
							pstmt.executeUpdate();
							System.out.println("Hi there");
							
//							ResultSet rs = pstmt.getGeneratedKeys();
//							int actIDg = 0;
//							rs.next();
//							actIDg = rs.getInt(1);
//
//							pstmt2.setInt(1,actIDg);
//							pstmt2.setInt(2,Integer.parseInt(actg.getCategory()));
//							pstmt2.executeUpdate();
							con.commit(); 
//							System.out.println(actIDg+" commited.");
			    	}		

			    	//actgs.add(actgVO);  
			    }
			    
	
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

		
		 static String readUrl(String urlString) throws Exception {
		    BufferedReader reader = null;
		    try {
		        URL url = new URL(urlString);
		        reader = new BufferedReader(new InputStreamReader(url.openStream()));
		        StringBuffer buffer = new StringBuffer();
		        int read;
		        char[] chars = new char[1024];
		        while ((read = reader.read(chars)) != -1)
		            buffer.append(chars, 0, read); 

		        return buffer.toString();
		    } finally {
		        if (reader != null)
		            reader.close();
		    }
		}
		 
		 
		 
		 
		
		
	}

