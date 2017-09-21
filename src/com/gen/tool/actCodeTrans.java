package com.gen.tool;

public class actCodeTrans {
		
		public static String actStatIDtoString(int actStatID){
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
