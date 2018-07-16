package com.cignium.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cignium.util.WebSearchUtils;
import com.cignium.websearch.service.WebSearchService;

public class CustomGoogleSearch {
	    final static String apiKey=WebSearchUtils.getInstance().getCustomProperty("googleApiKey");
	    final static String customSearchEngineKey = WebSearchUtils.getInstance().getCustomProperty("googleCustomSearchEngineKey");
	    final static String searchURL = WebSearchUtils.getInstance().getCustomProperty("googleSearchURL");

		private String search(String pUrl) {
			try {
				URL url = new URL(pUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String line;
				StringBuffer buffer = new StringBuffer();
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
				return buffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		private static String buildSearchString(String searchString, int start, int numOfResults) {
			String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey + "&q=";

			// replace spaces in the search query with +
			String newSearchString = searchString.replace(" ", "%20");

			toSearch += newSearchString;

			// specify response format as json
			toSearch += "&alt=json";

			// specify starting result number
			toSearch += "&start=" + start;

			// specify the number of results you need from the starting position
			toSearch += "&num=" + numOfResults;
			
			return toSearch;
		}


		public BigInteger getTotalQuery(String searchString) throws Exception {
			String url = buildSearchString(searchString, 1, 10);
			String result = search(url);
			WebSearchService wss = new WebSearchService();
			BigInteger numResults = wss.getGoogleWebSearchResponse(result).getNumberOfResults();
			return numResults;
			
		}
}
