package com.cignium.controller;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.cignium.util.WebSearchUtils;
import com.cignium.websearch.service.WebSearchService;


public class CustomBingSearch {

    static String subscriptionKey = WebSearchUtils.getInstance().getCustomProperty("bingSubscriptionKey");
    static String host = WebSearchUtils.getInstance().getCustomProperty("bingHost");
    static String path = WebSearchUtils.getInstance().getCustomProperty("bingPath");

    private String search (String searchQuery) throws Exception {
        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();
        stream.close();
        return response;
    }

 
    public BigInteger getTotalQuery (String searchString ) {
        if (subscriptionKey.length() != 32) {
            System.out.println("Invalid Bing Search API subscription key!");
            System.exit(1);
        }
        try {   
            String response = search(searchString); 
            
            WebSearchService wss = new WebSearchService();
            BigInteger numResults = wss.getBingWebSearchResponse(response).getNumberOfResults();

			return numResults;
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            System.exit(1);
        }
        return null;
    }    
}

