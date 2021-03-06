package Retriever;

import org.elasticsearch.search.SearchHits;

import javax.json.JsonObject;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RetrieverWrapper {
    public static JsonObject retrieveSearchResults(List<String> query, int sizeOfRetrievedList) throws Exception {
        // query elastic
        SearchHits hits = NewRetriever.queryElastic(query, sizeOfRetrievedList);

        // parse hits into json
        JsonObject searchResultsAsJson = NewParser.newParser(hits);

        return searchResultsAsJson;
    }

    public static AbstractMap<String, AbstractMap<String, Double>> retrieveSearchResultsLists(List<String> query, int sizeOfRetrievedList) throws Exception {
        ParameterCreator queryParams = new ParameterCreator();

        // search elastic for the specific query
        queryParams.setRestParamsForStandardQuery(query, sizeOfRetrievedList);
        JsonObject retrievedRes = Retriever.searchResultRetriever(queryParams);

        // parse the elastic ranked list into scoredDocs, linkedDocs, and totalScore
        List parsedResult = RetrieverParser.docAndLinksScoreParser(retrievedRes);
        AbstractMap<String, Double> scoredDocs = (HashMap<String, Double>) parsedResult.get(0);
        AbstractMap<String, Double> linkedDocs = (HashMap<String, Double>) parsedResult.get(1);
        AbstractMap<String, Double> totalScore = new HashMap<>();
        totalScore.put("totalScore", (Double) parsedResult.get(2));

        // add results to the the to return-map
        AbstractMap<String, AbstractMap<String, Double>> parsedSearchResults = new HashMap<>();
        parsedSearchResults.put("scoredDocs", scoredDocs);
        parsedSearchResults.put("linkedDocs", linkedDocs);
        parsedSearchResults.put("totalScore", totalScore);

        return parsedSearchResults;
    }

    public static List<AbstractMap<String, AbstractMap<String, Double>>> retrieveClustersLists(List<List<String>> queries, int sizeOfRetrievedList) throws Exception {

        List<AbstractMap<String, AbstractMap<String, Double>>> storedSearchResultLists = new ArrayList<>();
        ParameterCreator queryParams = new ParameterCreator();

        for (List<String> query : queries) {
            // search elastic for the specific query
            queryParams.setRestParamsForClusters(query, sizeOfRetrievedList);
            JsonObject retrievedRes = Retriever.searchResultRetriever(queryParams);

            // parse the elastic ranked list into scoredDocs, linkedDocs, and totalScore
            List parsedResult = RetrieverParser.docAndLinksScoreParser(retrievedRes);
            AbstractMap<String, Double> scoredDocs = (HashMap<String, Double>) parsedResult.get(0);
            AbstractMap<String, Double> linkedDocs = (HashMap<String, Double>) parsedResult.get(1);
            AbstractMap<String, Double> totalScore = new HashMap<>();
            totalScore.put("totalScore", (Double) parsedResult.get(2));

            AbstractMap<String, AbstractMap<String, Double>> parsedSearchResults = new HashMap<>();
            parsedSearchResults.put("scoredDocs", scoredDocs);
            parsedSearchResults.put("linkedDocs", linkedDocs);
            parsedSearchResults.put("totalScore", totalScore);

            // store
            storedSearchResultLists.add(parsedSearchResults);
        }
        return storedSearchResultLists;
    }
}

