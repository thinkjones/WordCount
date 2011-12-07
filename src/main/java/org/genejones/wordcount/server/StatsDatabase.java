package org.genejones.wordcount.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.genejones.wordcount.server.helper.CalcTopTenWords;
import org.genejones.wordcount.server.helper.Trie;
import org.genejones.wordcount.shared.IWordCount;

public class StatsDatabase implements IWordCount{

	private Trie wordStore;

	
	private HashMap<String, Map<String, Integer>> bookStats;
	private Map<String, Integer> globalStats;
	
	public StatsDatabase(){
		this.wordStore = new Trie();
		this.bookStats = new HashMap<String, Map<String, Integer>>();
		this.globalStats = new HashMap<String, Integer>();
	}
	
	public void countWords(String bookTitle, Iterator<String> lines) {
		
		CalcTopTenWords calcTopTenWordsBook = new CalcTopTenWords(); 
		CalcTopTenWords calcTopTenWordsGlobal = new CalcTopTenWords(); 

		//Add words to global and book specific trie
		Trie bookWordStore = new Trie();
		
		while(lines.hasNext()){
			String thisLine = lines.next();
			String[] words = thisLine.split(" ");
			for(String word : words){
				bookWordStore.addWord(word);  //local trie to create book stats
				wordStore.addWord(word);     //global trie to calc global stats
			}
		}
		
		// Calc Book Stats
		Map<String, Integer> thisBookStats = calcTopTenWordsBook.topTenWords(bookWordStore);
		bookStats.put(bookTitle,  thisBookStats);
		
		//Calc global stats
		globalStats = calcTopTenWordsGlobal.topTenWords(wordStore);

	}

	public Map<String, Integer> topTenWords() {
		return globalStats;
	}

	public Map<String, Integer> topTenWords(String bookTitle) {
		return bookStats.get(bookTitle);
	}
	
	public ArrayList<String> getBooks(){
		ArrayList<String> books = new ArrayList<String>();
		for(String key : bookStats.keySet()){
			books.add(key);
		}
		return books;
	}
}
