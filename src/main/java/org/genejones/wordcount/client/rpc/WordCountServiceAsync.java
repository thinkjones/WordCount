package org.genejones.wordcount.client.rpc;

import java.util.ArrayList;
import java.util.Map;

import org.genejones.wordcount.shared.BookTextRetrievalType;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface WordCountServiceAsync {

	void generateBookStats(String bookTitle, String bookText,
			BookTextRetrievalType generateMethod, AsyncCallback<Void> callback);

	void topTenWords(AsyncCallback<Map<String, Integer>> callback);

	void topTenWords(String bookTitle,
			AsyncCallback<Map<String, Integer>> callback);

	void getBooks(AsyncCallback<ArrayList<String>> callback);

}
