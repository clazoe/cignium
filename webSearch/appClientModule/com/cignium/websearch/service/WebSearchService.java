package com.cignium.websearch.service;

import java.io.StringReader;
import java.math.BigInteger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.cignium.util.WebSearchConstants;
import com.cignium.websearch.model.BingSearch;
import com.cignium.websearch.model.GoogleSearch;

public class WebSearchService {

	public GoogleSearch getGoogleWebSearchResponse(String json) {
		    GoogleSearch googleSearch = new GoogleSearch();
		    JsonReader reader = Json.createReader(new StringReader(json)); 
	        JsonObject content = reader.readObject();
	        reader.close();    
	        JsonObject searchContent = content.getJsonObject(WebSearchConstants.GOOGLE_HEADER_SEARCH); 
	        googleSearch.setNumberOfResults( new BigInteger(searchContent.getString(WebSearchConstants.GOOGLE_TOTAL_RESULTS).trim()) );
	        return googleSearch;
	}
	
	
	
	public BingSearch getBingWebSearchResponse(String json) {
	    BingSearch bingSearch = new BingSearch();
	    JsonReader reader = Json.createReader(new StringReader(json)); 
        JsonObject content = reader.readObject();
        reader.close();    
        JsonObject searchContent = content.getJsonObject(WebSearchConstants.BING_HEADER_SEARCH);         
        bingSearch.setNumberOfResults(BigInteger.valueOf(searchContent.getInt(WebSearchConstants.BING_TOTAL_RESULTS)) );
        return bingSearch;
}
	
}




