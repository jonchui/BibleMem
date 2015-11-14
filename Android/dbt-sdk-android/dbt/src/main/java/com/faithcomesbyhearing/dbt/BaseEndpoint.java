package com.faithcomesbyhearing.dbt;

import android.os.AsyncTask;

import com.faithcomesbyhearing.dbt.model.BookName;
import com.faithcomesbyhearing.dbt.model.TextSearch;
import com.faithcomesbyhearing.dbt.model.TextSearchResult;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

class BaseEndpoint {

    public class NetworkTask extends AsyncTask<Void, Void, List> {

        private String endpoint;
        private List responseType;
        private Type gsonType;
        private HashMap<String, String> params;
        private Callback callback;
        private RestClient client;

        public NetworkTask(String endpoint, List responseType, Type gsonType,
                           HashMap<String, String> params, Callback callback) {
            this.endpoint = endpoint;
            this.responseType = responseType;
            this.gsonType = gsonType;
            this.params = params;
            this.callback = callback;
            client = new RestClient(Dbt.getApiKey());
        }

        @Override
        protected List doInBackground(Void... voids) {
            try {
                InputStreamReader reader = client.execute(endpoint, params);
                if (endpoint.equals("/text/search")) {
                    String jsonText = convertInputStreamReaderToString(reader);
                    JSONArray jsonArray = new JSONArray(jsonText);
                    String numberOfResults = jsonArray.getJSONArray(0)
                            .getJSONObject(0).getString("total_results");
                    JSONArray resultsArray = jsonArray.getJSONArray(1);
                    TextSearch search = new TextSearch();
                    List<TextSearchResult> results = new ArrayList<TextSearchResult>(resultsArray.length());
                    for (int i = 0; i < resultsArray.length(); i++) {
                        JSONObject object = resultsArray.getJSONObject(i);
                        TextSearchResult result = new TextSearchResult();
                        result.setDamId(object.getString("dam_id"));
                        result.setBookId(object.getString("book_id"));
                        result.setBookName(object.getString("book_name"));
                        result.setBookOrder(object.getString("book_order"));
                        result.setChapterId(object.getString("chapter_id"));
                        result.setVerseId(object.getString("verse_id"));
                        result.setVerseText(object.getString("verse_text"));
                        results.add(result);
                    }
                    search.setResultsReturned(Long.parseLong(numberOfResults));
                    search.setResults(results);
                    return results;
                } else if (endpoint.equals("/library/bookname")) {
                    String jsonText = convertInputStreamReaderToString(reader);
                    JSONObject bookNamesJson = new JSONArray(jsonText).getJSONObject(0);
                    Iterator<?> keys = bookNamesJson.keys();
                    HashMap<String, String> bookNames = new HashMap<String, String>();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        bookNames.put(key, bookNamesJson.getString(key));
                    }
                    BookName bookName = new BookName();
                    bookName.setBookNames(bookNames);
                    List<BookName> bookList = new ArrayList<BookName>();
                    bookList.add(bookName);
                    callback.success(bookList);
                } else if (endpoint.equals("/library/numbers")) {
                    String jsonText = convertInputStreamReaderToString(reader);
                    JSONObject numbersJson = new JSONArray(jsonText).getJSONObject(0);
                    Iterator<?> keys = numbersJson.keys();
                    HashMap<String, String> numbers = new HashMap<String, String>();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        numbers.put(key, numbersJson.getString(key));
                    }

                    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
                    list.add(numbers);
                    callback.success(list);
                } else {
                    return responseType.getClass()
                            .cast(new Gson().fromJson(reader, gsonType));
                }
            } catch (Exception e) {
                callback.failure(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            if (list != null) {
                callback.success(list);
            }
        }

        private String convertInputStreamReaderToString(InputStreamReader isr) throws IOException {
            StringBuilder jsonTextBuilder = new StringBuilder();
            BufferedReader buffer = new BufferedReader(isr);
            String line;
            do {
                line = buffer.readLine();
                jsonTextBuilder.append(line + "\n");
            } while (line != null);
            return jsonTextBuilder.toString();
        }
    }
}
