package org.genejones.wordcount.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.genejones.wordcount.client.rpc.WordCountService;
import org.genejones.wordcount.server.iterators.IteratorFactory;
import org.genejones.wordcount.shared.BookTextRetrievalType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class WordCountServiceImpl extends RemoteServiceServlet implements
	WordCountService {

	private StatsDatabase statsDatabase = new StatsDatabase();
	private IteratorFactory iterFactory = new IteratorFactory();
	
	public void generateBookStats(String bookTitle, String bookText,
			BookTextRetrievalType generateMethod) {
		
		//Do we need to generate text for this text?
		if(statsDatabase.topTenWords(bookTitle) == null){
			Iterator<String> lines = iterFactory.create(generateMethod, bookText);
			statsDatabase.countWords(bookTitle, lines);
		}
		
	}

	public Map<String, Integer> topTenWords() {
		return statsDatabase.topTenWords();
	}

	public Map<String, Integer> topTenWords(String bookTitle) {
		return statsDatabase.topTenWords(bookTitle);
	}

	@Override
	public ArrayList<String> getBooks() {
		return statsDatabase.getBooks();
	}

}
