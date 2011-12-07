package org.genejones.wordcount.server.helper;

import static org.junit.Assert.*;

import org.genejones.wordcount.shared.NodeLetter;
import org.junit.Test;

public class TestNodeLetter {

	@Test
	public void testLetterIndexCalculation() throws Exception {

		int index = NodeLetter.getIndexValue('a');
		assertEquals(0,index);

		index = NodeLetter.getIndexValue('A');
		assertEquals(0,index);

		index = NodeLetter.getIndexValue('b');
		assertEquals(1,index);

		index = NodeLetter.getIndexValue('B');
		assertEquals(1,index);

		index = NodeLetter.getIndexValue('z');
		assertEquals(25,index);

		index = NodeLetter.getIndexValue('Z');
		assertEquals(25,index);
		
	}

	@Test
	public void testCreateNodeLetter() throws Exception {
		
		NodeLetter node = new NodeLetter('G');
		assertFalse(node.isWord());
		assertEquals(node.getLetter(), 'G');

		node = new NodeLetter('g');
		assertFalse(node.isWord());
		assertEquals(node.getLetter(), 'G');

		node.setWord(true);
		assertTrue(node.isWord());
		
	}

	@Test
	public void testCreateNodeLetterAndLevel() throws Exception {

		NodeLetter node = new NodeLetter('G',0);
		assertFalse(node.isWord());
		assertEquals(node.getLevel(), 0);

		NodeLetter node2 = new NodeLetter('G',1);
		assertEquals(node2.getLevel(), 1);

	}
	
	@Test
	public void testCreateWord() throws Exception {

		char letters[] = {'g','e','n','e'};
		NodeLetter rootNode = new NodeLetter(' ');
		NodeLetter currentNode = rootNode;
		
		for(char letter : letters){
			currentNode = currentNode.getChild(letter);
		}
		currentNode.setWord(true);
		
		assertEquals(currentNode.getLevel(), 4);		
		assertTrue(currentNode.isWord());
		
		//loop through temp Trie and assert word.
		currentNode = rootNode;
		for(char letter : letters){
			assertTrue(currentNode.isChild(letter));		
			currentNode = currentNode.getChild(letter);
		}

		
	}

}
