package org.genejones.wordcount.client.rpc;

import java.util.ArrayList;
import java.util.Map;

import org.genejones.wordcount.shared.BookTextRetrievalType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("wordCountServices")
public interface WordCountService extends RemoteService {
	
	void generateBookStats(String bookTitle, String bookText, BookTextRetrievalType generateMethod);
	
	Map<String, Integer> topTenWords();
	
	Map<String, Integer> topTenWords(String bookTitle);
	
	ArrayList<String> getBooks();
	
	
}