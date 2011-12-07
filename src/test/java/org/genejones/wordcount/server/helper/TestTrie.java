package org.genejones.wordcount.server.helper;

import static org.junit.Assert.*;

import org.genejones.wordcount.server.helper.Trie;
import org.genejones.wordcount.shared.NodeWord;
import org.junit.Before;
import org.junit.Test;

public class TestTrie {
	
	private String words[] = {"Gene" ,"General", "EEEEEE",""};

	private Trie testTrie = null;
	
	@Before
	public void createTrie() {
		this.testTrie = new Trie();
		addWords();
	}
	
	private void addWords(){
		for(String word : words){
			testTrie.addWord(word);
		}
	}
	
	@Test
	public void testSimpleWordsAddToTrie() throws Exception {
		
		//Loop through words and add a word each time
		for(String word : words){
			testTrie.addWord(word);
			
			if(word.length() > 0)
				assertTrue("[" + word + "] was not located", testTrie.isWord(word));
		}
		
		//Check null word was not found.
		assertFalse(testTrie.isWord(""));
		
	}

	@Test
	public void testOccurances() throws Exception {
		
		//test all one words
		for(String word : words){
			NodeWord nodeWord = testTrie.getWord(word);

			if(word.length() > 0)
				assertEquals("[" + word + "] occurred [" + nodeWord.getCount() + "] times should only occur once.", nodeWord.getCount(), 1);
		}
		
		//Add Gene Again
		String word  = "Gene";
		testTrie.addWord(word);
		NodeWord nodeWord = testTrie.getWord(word);
		assertEquals("[" + word + "] occurred [" + nodeWord.getCount() + "] times should have occurred twice.", nodeWord.getCount(), 2);
		

		
		
	}

	

}
