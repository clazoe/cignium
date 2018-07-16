package com.cignium.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class WebSearchUtils {
	
	private static WebSearchUtils instance=null;
	private Properties p;
	private WebSearchUtils() {
	 
	p= new Properties();
		try {
			InputStream propertiesIs = this.getClass().getClassLoader().getResourceAsStream("META-INF/webSearch.properties");
            p.load(propertiesIs);
		    //p.load(new FileInputStream(new File("basedatos.properties")));
		} catch (IOException e) {
		     e.printStackTrace();
		}
	}
	 
	public static WebSearchUtils getInstance() {
	 
	 if (instance==null) {	 
		instance=new WebSearchUtils();
	 }
	  return instance;
	}
	 
	public String getCustomProperty(String clave) {
	  return p.getProperty(clave);
	}
	
	
	
	public static HashMap<String,BigInteger> getMaxHashMapSearch(HashMap<String,BigInteger> hmap) {
	 	
		HashMap<String,BigInteger> maxMap = new HashMap<String,BigInteger>();	
		
	  BigInteger maxValueInMap=(Collections.max(hmap.values()));
	  BigInteger value = null;
	  String key = null;
	  
      for (Entry<String, BigInteger> entry : hmap.entrySet()) {  
          if (entry.getValue().equals(maxValueInMap)) {
        	  key = entry.getKey();
        	  value = entry.getValue();  
        	  
        	  maxMap.put(key, value);
          }
      }
	
         return maxMap;
	}
	

}
