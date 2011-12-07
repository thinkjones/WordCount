package org.genejones.wordcount.server.helper;

import static org.junit.Assert.*;

import org.genejones.wordcount.shared.NodeLetter;
import org.genejones.wordcount.shared.NodeWord;
import org.junit.Before;
import org.junit.Test;

public class TestMinHeap {
	private String words[] = {"Gene" ,"General", "EEEEEE"};
	private int count[] = {0,1,2};

	private MinHeap minHeap = null;
	
	@Before
	public void createMinHeap() {
		this.minHeap = new MinHeap();
	}
	
	private void addAscendingWords(){
		for(int i=10; i < 31; i++){
			String word = "word" + new Integer(i).toString();
			minHeap.insertNode(new NodeWord(word, i));
		}
	}
	
	private void addDecendingWords(){
		for(int i=30; i > 9; i--){
			String word = "word" + new Integer(i).toString();
			minHeap.insertNode(new NodeWord(word, i));
		}
	}
	
	@Test
	public void testAddAscendingWords(){
		addAscendingWords();
		
		NodeWord node = minHeap.getRootNode();
		println("Ascending");
		printChildren();
		
		assertEquals("Root node should have a value of 21, actual value " + node.getCount(), 21, node.getCount());
	}
	
	@Test
	public void testAddDescendingWords(){
		addDecendingWords();
		
		NodeWord node = minHeap.getRootNode();
		
		println("Descending");
		printChildren();
		assertEquals("Root node should have a value of 21, actual value " + node.getCount(), 21, node.getCount());
	}
	
	private void println(String message){
		System.out.println(message);
	}
	
	private void printChildren() {
		NodeWord[] words = this.minHeap.getDataStruture();
		Integer index = 0;
		for(NodeWord word : words){
			println(index.toString() + "\t" + word.getWord() + "\t" + word.getCount());
			index++;
		}
	}
	
}
