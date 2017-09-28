package com.gen.tool;

	import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.*;

	public class hiActIMG {

		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	        public static void main(String argv[]) {
	    		Connection con = null;
	    		PreparedStatement pstmt = null;
	    		ResultSet rs = null;
		        
	              try {
	      			con = ds.getConnection();
	                File pic = new File("picFrom", picName); //相對路徑- picFrom
	                                                         //絕對路徑- 譬如:
	                                                         //File pic = new File("x:\\aa\\bb\\picFrom", picName);
	                long flen = pic.length();
	                String fileName = pic.getName();
	                int dotPos = fileName.indexOf('.');
	                String fno = fileName.substring(0, dotPos);
	                String format = fileName.substring(dotPos + 1);
	                InputStream fin = new FileInputStream(pic);  

	                System.out.println("\n\nUpdate the database... ");
	                pstmt = con.prepareStatement(
	                 "insert into act (actIMG)  values(?)");
	                pstmt.setString(1, fno);
	                pstmt.setString(2, format);
	                pstmt.setBinaryStream(3, fin, (int)flen); //void pstmt.setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException
	                int rowsUpdated = pstmt.executeUpdate();
				
	                System.out.print("Changed " + rowsUpdated);

	                if (1 == rowsUpdated)
	                    System.out.println(" row.");
	                else
	                    System.out.println(" rows.");

	                fin.close();
	                pstmt.close();

	              } catch (Exception e) {
	                    e.printStackTrace();
	              } finally {
	                    try {
	                      con.close();
	                    } catch (SQLException e) {
	                    }
	             }
	      }
	}