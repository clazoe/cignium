package com.cignium.main;

import java.math.BigInteger;
import java.util.HashMap;

import com.cignium.controller.CustomBingSearch;
import com.cignium.controller.CustomGoogleSearch;
import com.cignium.util.WebSearchUtils;

public class WebSearchExecutor {
	
	
	public static void main (String[] args) throws Exception {
		
		CustomBingSearch bingSearch = new CustomBingSearch();
		CustomGoogleSearch googleSearch = new CustomGoogleSearch();
		
		HashMap<String, BigInteger> hmapGoogle = new HashMap<String, BigInteger>();
		HashMap<String, BigInteger> hmapBing = new HashMap<String, BigInteger>();
		
			
		for(String argument : args){
			BigInteger totalGoogle = googleSearch.getTotalQuery(argument);
			BigInteger totalBing = bingSearch.getTotalQuery(argument);
            System.out.println(argument + ":" + " Google: " + totalGoogle + " Bing: " + totalBing);
			hmapGoogle.put(argument, totalGoogle);
			hmapBing.put(argument, totalBing);
        }      
		
        
		
		HashMap<String, BigInteger> hmapMaxGoogle = WebSearchUtils.getMaxHashMapSearch(hmapGoogle);
		HashMap<String, BigInteger> hmapMaxBing = WebSearchUtils.getMaxHashMapSearch(hmapBing);
		
		System.out.println("Google winner: " + hmapMaxGoogle.keySet().iterator().next());
		System.out.println("Bing winner: " + hmapMaxBing.keySet().iterator().next() );
		
		HashMap<String, BigInteger> unionMax = WebSearchUtils.getMaxHashMapSearch(hmapBing);
		
		unionMax.putAll(hmapMaxGoogle);
		unionMax.putAll(hmapMaxBing);
		
		System.out.println("Total winner: " + WebSearchUtils.getMaxHashMapSearch(unionMax).keySet().iterator().next());
		
	}
	
	
	

	
	
	

	
	

}
