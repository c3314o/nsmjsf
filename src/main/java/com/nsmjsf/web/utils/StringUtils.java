package com.nsmjsf.web.utils;

import java.util.regex.Pattern;

public class StringUtils {
	
	public static boolean isAlike(final String str, final String expr)
	{
	  String regex = quotemeta(expr);
	  regex = regex.replace("_", ".").replace("%", ".*?");
	  Pattern p = Pattern.compile(regex,
	      Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	  return p.matcher(str).matches();
	}
	
	
	public static String quotemeta(String s)
	{
	  if (s == null)
	  {
	    throw new IllegalArgumentException("String cannot be null");
	  }

	  int len = s.length();
	  if (len == 0)
	  {
	    return "";
	  }

	  StringBuilder sb = new StringBuilder(len * 2);
	  for (int i = 0; i < len; i++)
	  {
	    char c = s.charAt(i);
	    if ("[](){}.*+?$^|#\\".indexOf(c) != -1)
	    {
	      sb.append("\\");
	    }
	    sb.append(c);
	  }
	  return sb.toString();
	}
	
	public static float parseFloatSigned(String number){
		if(number.contains("+")||number.contains("-")){
			String sign=number.substring(0, 0);
			String numeric=number.substring(1,number.length());
			float fnum=Float.parseFloat(numeric);
			if(sign.equalsIgnoreCase("-")){
				return fnum*(-1);
				
			}else{
				return fnum;
				
			}
		}
		return Float.parseFloat(number);
	}
	
	public static int parseInt(String number){
		if(number.contains(".")){
			
			String numeric=number.substring(0,number.indexOf("."));
			int fnum=Integer.parseInt(numeric);
			return fnum;
			
		}
		return Integer.parseInt(number);
	}


}
