package org.genejones.wordcount.server.helper;

import static org.junit.Assert.*;

import java.util.Map;

import org.genejones.wordcount.server.iterators.TextIterator;
import org.junit.Before;
import org.junit.Test;

public class TestTrieComplex {

	private String numbers[] = {"one" ,"two", "three","four", "five", "six", "seven", "eight", "nine" , "ten","eleven"};

	private String fourWords = "Here is some code";
	
	private Trie testTrie = null;
	
	@Before
	public void createTrie() {
		this.testTrie = new Trie();
	}
	
	private String generateStatsString(){
		String ret = "";
		for(int i = 0 ; i < numbers.length; i++){
			String line = "";
			for(int j = 0 ; j < i + 1; j++){
				line += numbers[i] + " ";				
			}
			line += "\n";
			ret += line;
		}
		return ret;
	}
	
	private TextIterator getIter(){
		String textualInput = this.generateStatsString();
		TextIterator iter = new TextIterator(textualInput);
		return iter;
	}
	
	private Trie getTrie(){
		//Add words to global and book specific trie
		Trie bookWordStore = new Trie();
		TextIterator lines = getIter();
		
		while(lines.hasNext()){
			String thisLine = lines.next();
			String[] words = thisLine.split(" ");
			for(String word : words){
				bookWordStore.addWord(word);  //local trie to create book stats
			}
		}
		return bookWordStore;
	}
	
	private Trie getTrieFourWords(){
		//Add words to global and book specific trie
		Trie bookWordStore = new Trie();
		TextIterator lines = getIterFourWords();
		
		while(lines.hasNext()){
			String thisLine = lines.next();
			String[] words = thisLine.split(" ");
			for(String word : words){
				bookWordStore.addWord(word);  //local trie to create book stats
			}
		}
		return bookWordStore;
	}
	
	private TextIterator getIterFourWords(){
		TextIterator iter = new TextIterator(fourWords);
		return iter;
	}

	
	@Test
	public void testTextIterator(){
		
		TextIterator iter = getIter();
		
		//Check there are ten entries
		for(int i = 0 ; i < numbers.length; i++){
			assertTrue("Line " + i + " doesn't exist", iter.hasNext());
			String thisLine = iter.next();
		}
		assertFalse("Has another unexpeted line.", iter.hasNext());
	}
	
	
	@Test
	public void testAddComplexTrie(){
		
		//Add words to global and book specific trie
		Trie bookWordStore = getTrie();
		
		//Verify All words exist.
		for(int i = 0 ; i < numbers.length; i++){
			String word = numbers[i];
			assertTrue("Word " + word  + " doesn't exist", bookWordStore.isWord(word));
		}
	}
	
	@Test
	public void testCalculatedStats(){
		
		//Get Trie
		Trie bookWordStore = getTrie();
		
		//Get Calc Top Ten Words
		CalcTopTenWords calcTopTenWords = new CalcTopTenWords();
		Map<String, Integer> thisBookStats = calcTopTenWords.topTenWords(bookWordStore);
		
		//Loop through stats and check they are all here in the correct amounts;
		for(String word : thisBookStats.keySet()){
			if(word.equals("ONE")){
				assertTrue("ONE shouldn't be in the top ten because we have 11 entries and each number is repeated as a word the same number of times.", false);
			}else{
				Integer repeatsNumberOfTimes = getIndex(word) + 1;
				assertEquals("Word " + word + " should be repeated " + repeatsNumberOfTimes.toString() + " times.", thisBookStats.get(word), repeatsNumberOfTimes);
			}
		}
	}
	
	@Test
	public void testFourWords(){
		
		//Get Trie
		Trie bookWordStore = getTrieFourWords();
		
		//Get Calc Top Ten Words
		CalcTopTenWords calcTopTenWords = new CalcTopTenWords();
		Map<String, Integer> thisBookStats = calcTopTenWords.topTenWords(bookWordStore);
		
		//All words should exist once.
		String[] words = fourWords.split(" ");
		for(String word : words){
			if(thisBookStats.containsKey(word.toUpperCase())==false){
				assertTrue("Word does not exist " + word, false);
			}
		}
	}
	
	
	
	private int getIndex(String word){
		int index = 0;
		for(String thisWord : numbers){
			if(thisWord.toUpperCase().equals(word)==true)
				break;
			else
				index++;
		}
		return index;
	}
	
	
	
}
