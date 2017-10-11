package com.gen.tool;

import java.sql.*;
import java.nio.charset.StandardCharsets.*;
import oracle.jdbc.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
			String INSERT_STMT = "insert into act(actType, actID, memID, actCreateDate, actName, actStatus, actPriID, actStartDate, actEndDate, actImg, actContent, actLong, actLat, actLocName, actAdr, actUID, actShowUnit, actMasterUnit, actWebSales, actSourceWebName, actOnSale, actPrice)"
														+ " values (2, act_seq.nextval, 0, systimestamp, ?, 1, 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				int cat=1;
				while (cat<20) {
					try {
						System.out.println("cat="+cat);
						String json = readUrl("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category="+cat);
//						System.out.println(json);
		    
					    Gson gson = new Gson();
					    JsonParser parser = new JsonParser();
//					    JsonArray Jarray = (JsonArray) parser.parse(new FileReader("e:\\json.txt"));
					    JsonArray Jarray = parser.parse(json).getAsJsonArray();
					     
					    ArrayList<actGVO> actgs = new ArrayList<actGVO>();
//					     start for each loop
					    for(JsonElement obj : Jarray ){
					    	System.out.println("start a new obj");
					    	actGVO actg = (actGVO) gson.fromJson(obj , actGVO.class);
					    	System.out.println(actg.getTitle()+" start======================================");
					    	
					    	int showCount=7;
					    	for (int i=0; i <showCount; i++) {
					    		String subtitle;
					    			if(showCount>1) {
					    					subtitle="－第"+(i+1)+"場（共"+showCount+"場）";
					    			} else {
					    				subtitle="";
					    			}
					    		String actName=actg.getTitle()+subtitle;
					    		Timestamp actStartDate=com.gen.tool.tools.strToTimestampGson(actg.getShowInfo().get(i).getTime());
					    		Timestamp actEndDate=com.gen.tool.tools.strToTimestampGson(actg.getShowInfo().get(i).getEndTime());
					    		//System.out.println("actEndDate="+actEndDate);
					    		
								Blob blobbb=con.createBlob();
								String path="https://cloud.culture.tw/frontsite/inquiry/eventInquiryAction.do?method=showEventDetail&uid="+actg.getUID();
								System.out.println("path="+path);
								Document doc = Jsoup.connect(path).get();
								byte[] bytesss=null;
								if	(!actg.getImageUrl().trim().equals("")){
									bytesss=com.gen.tool.tools.getUrlPictureByteArray(actg.getImageUrl());
								}else {
									 Element content = doc.select("div.Info_img>img[src]").first();
									 int start=content.toString().indexOf("/");
									 int end= content.toString().indexOf("\" ");
									 	if(end>start) {
											 String res="https://cloud.culture.tw"+content.toString().substring(start, end);
											 	System.out.println("res"+res);
											bytesss=com.gen.tool.tools.getUrlPictureByteArray(res);
									 	} else {
										String path2 = "E:\\\\DB\\\\POIBLOB\\"+actg.getCategory()+".jpg";
										bytesss=com.gen.tool.tools.getPictureByteArray(path2);
									 	}
								}
								
								blobbb.setBytes(1, bytesss);
								
//								String actContent=actg.getDescriptionFilterHtml();
								String actContent=doc.select("div.detail_cont").first().toString();
								

								Double actLong=(Double) actg.getShowInfo().get(i).getLongitude();
								Double actLat=(Double) actg.getShowInfo().get(i).getLatitude();
								String actAdr=actg.getShowInfo().get(i).getLocation();
								if(actAdr==null || actAdr.trim().equals("")) {
									continue;
									}
								
								
								String mu=actg.getMasterUnit().toString();
								mu=mu.substring(1, mu.length()-1);
			System.out.println("mu="+mu);
								if(actLong==999.9 || actLong.toString().equals("")) {
									String[] str=geoTrans.adrToGeo(actAdr).split(",");
									actLat=Double.parseDouble(str[0]);
									actLong=Double.parseDouble(str[1]);
								}
								
					    		
									pstmt.setString(1, actName);	//setActName
									pstmt.setTimestamp(2, actStartDate);	//StartDate
												if(actEndDate!=null) {
													pstmt.setTimestamp(3, actEndDate);
												} else {
													pstmt.setTimestamp(3, actStartDate);	//endDate
												}
									pstmt.setBlob(4, blobbb);							
									pstmt.setString(5, actContent);	//Content
									pstmt.setDouble(6, actLong);
									pstmt.setDouble(7, actLat);
									pstmt.setString(8, actg.getShowInfo().get(i).getLocationName());
									pstmt.setString(9, actAdr);	//adr
									pstmt.setString(10, actg.getUID());
									pstmt.setString(11, actg.getShowUnit());
									pstmt.setString(12, mu);
									pstmt.setString(13, actg.getWebSales());
									pstmt.setString(14, actg.getSourceWebName());
									pstmt.setString(15, actg.getShowInfo().get(i).getOnSales());
									pstmt.setString(16, actg.getShowInfo().get(i).getPrice());
									pstmt.executeUpdate();
									
//									ResultSet rs = pstmt.getGeneratedKeys();
//									int actIDg = 0;
//									rs.next();
//									actIDg = rs.getInt(1);
		//
//									pstmt2.setInt(1,actIDg);
//									pstmt2.setInt(2,Integer.parseInt(actg.getCategory()));
//									pstmt2.executeUpdate();
									con.commit(); 
									System.out.println(actg.getTitle()+subtitle+" data commited===================================");
//									System.out.println(actIDg+" commited.");
			
					    	}
					    }
//						     End for each loop
			    	cat=cat+1;	
//					end of try			    
			 }catch (NumberFormatException e){
			    	System.out.println("NumberFormatException occur, continue");
					continue;
			    	
			}catch (SQLException e){
		    	e.printStackTrace();
				System.out.println("SQLException occur, continue");
					continue;
			}catch (HttpStatusException e){
		    	e.printStackTrace();
				System.out.println("HttpStatusException occur, continue");
					continue;
			} finally {
					
				}
		    		cat=cat+1;  
	}
					//end of while				
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
