package com.gensuite.search.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gensuite.search.util.SearchConstants;



@Controller
public class SearchController {

	@Autowired
	private Client client;

	 @Value("${index}")
	 private String index;
	
	@RequestMapping(value = "/searchWelcome.action", method = RequestMethod.GET)
	public String searchWelcome(HttpServletRequest request,
			HttpServletResponse response) {
		return "search";

	}

	@RequestMapping(value = "/autocomplete.action", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	String autocomplete(HttpServletRequest request,	@RequestBody String searchString) throws IOException {
		QueryBuilder query = null;
		query = QueryBuilders.multiMatchQuery(searchString, "_all").type(MatchQueryBuilder.Type.PHRASE_PREFIX);
		// Executing the query and highlight the matching field
		SearchResponse response = client.prepareSearch(index).setQuery(query)
				.addHighlightedField("*", 0, 0).execute().actionGet();

		final JSONArray jsonArray = new JSONArray();
		List<String> uniqueList = new ArrayList<String>();
		List<String> chunk = new ArrayList<String>();
		for (SearchHit searchHit : response.getHits()) {
			if (response.getHits().getHits().length <= 0) {
				return null;
			}

			Map<String, HighlightField> highlightFields = searchHit
					.getHighlightFields();
			Integer searchTypeFlag = null;
			
			
			for (String fieldName : highlightFields.keySet()) {
				HighlightField highlightField = highlightFields.get(fieldName);
				Text[] str = highlightField.getFragments();
				chunk.add(String.valueOf(str[0]));
				JSONObject jsonObject = new JSONObject();
				// Setting the value for fuzzy search check
				if (SearchConstants.CUSTOMER_NAME.equals(fieldName)
						|| SearchConstants.CATALOG_ENG_DESC.equals(fieldName)
						|| SearchConstants.CATALOG_COMM_DESC.equals(fieldName)
						|| SearchConstants.MODEL.equals(fieldName)
						|| SearchConstants.ORGANIZATION_NAME.equals(fieldName)
						|| SearchConstants.COMMERCIAL_DESC.equals(fieldName)
						|| SearchConstants.ENGINEERING_DESC.equals(fieldName)
						|| SearchConstants.CATEGORY_DESC.equals(fieldName)
						|| SearchConstants.PRODUCT_DESC.equals(fieldName)) {
					searchTypeFlag = SearchConstants.SEARCH_FUZZY_TYPE;
				} else if (SearchConstants.CATALOG_NAME.equals(fieldName)
						|| SearchConstants.PART_NUMBER.equals(fieldName)
						|| SearchConstants.LEGACY_PART_NUMBER.equals(fieldName)
						|| SearchConstants.VENDOR_PART_NUMBER.equals(fieldName)
						|| SearchConstants.PRODUCT_CATALOG_NAME.equals(fieldName)
						|| SearchConstants.CATALOG_NAME.equals(fieldName)){
					searchTypeFlag = SearchConstants.SEARCH_NON_FUZZY_TYPE;
				} else {
					searchTypeFlag = SearchConstants.SEARCH_FUZZY_TYPE;;
				}
				try {
					if (!uniqueList.contains(String.valueOf(str[0]))) {
						jsonObject.put("value", searchTypeFlag);
						jsonObject.put("lable", String.valueOf(str[0]));
						uniqueList.add(String.valueOf(str[0]));
					}else{
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				jsonArray.put(jsonObject);
			}

		}

		// Replacing all <em> tags, to avoid it appearance in the dropdown
		String jsonAlter = jsonArray.toString().replaceAll("<em>", "")
				.replaceAll("<\\\\/em>/", "").replaceAll("<\\\\/em>", "");
		return jsonAlter;

	}

	@RequestMapping(value = "/searchRequest.action", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String searchRequest(HttpServletRequest request) throws IOException {
		int size = 10;
		int from = 0;
		
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		from = Integer.parseInt(request.getParameter("from"));
		String fuzzy = (String) request.getParameter("fuzzy");

		QueryBuilder query = null;
		Fuzziness fuzz = Fuzziness.ONE;
		if (SearchConstants.NON_FUZZY.equals(fuzzy)) {
			// the value is 2 if the search type is non fuzzy search( searches on _all)
			query = QueryBuilders.multiMatchQuery(search, "_all");
		} else if (SearchConstants.FUZZY.equals(fuzzy) || "".equals(fuzzy)) {
			// the value is 1 if the search type is fuzzy search
			query = QueryBuilders.multiMatchQuery(search, "_all").fuzziness(fuzz);
		}

		SearchRequestBuilder clientType;

		if ("all".equals(type)) {
			clientType = client.prepareSearch(index);
		} else {
			clientType = client.prepareSearch(index).setTypes(type);
		}

		// Executing the query and highlight the matching field
		SearchResponse searchResponse = clientType.setQuery(query)
				.setSize(size).setFrom(from).addHighlightedField("*", 0, 0)
				.execute().actionGet();

		SearchHit[] searchHits = searchResponse.getHits().getHits();
		StringBuilder builder = new StringBuilder();
		long Totalhits =searchResponse.getHits().getTotalHits();
		int length = searchHits.length;
		
		builder.append("[");
		builder.append("{\"totalhits\":"+Totalhits+"},");
		for (int i = 0; i < length; i++) {
			if (i == length - 1) {
				builder.append(searchHits[i].getSourceAsString());
			} else {
				// appending the Highlighted portion into the json string
				builder.append(searchHits[i].getSourceAsString());
				if (searchHits[i].getHighlightFields() != null) {
					Map<String, HighlightField> highlightFields = searchHits[i]
							.getHighlightFields();
					builder.append(",{");
					for (String fieldName : highlightFields.keySet()) {
						HighlightField highlightField = highlightFields
								.get(fieldName);
						Text[] str = highlightField.getFragments();
						builder.append("\"" + fieldName + "\":\"" + str[0]
								+ "\",");
					}
					builder.deleteCharAt(builder.length() - 1);
					builder.append("}");
				}
				builder.append(",");
			}
		}
		builder.append("]");
		String result = builder.toString();
		System.out.println(result);
		return result;
	}

}
