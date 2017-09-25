package com.gen.tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

import com.act.model.Act_VO;

public class actCodeTrans {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		public static String actStattoString(int actStatID){
			String Str=null;
				switch(actStatID){
				case 1:
					Str="籌辦中";
					break;
				case 2:
					Str="進行中";
					break;
				case 3:
					Str="已完成";
					break;
				case 4:
					Str="停權";
					break;
				case 5:
					Str="暫停中";
					break;
				case 6:
					Str="已取消";
					break;
	            default: 
	                System.out.println("????"); 
				}
			return Str;
		}

		
			public static String actLoctoString(int postNo){
				String city=null;
				return city;
			}
			
	
			
}
