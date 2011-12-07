package org.genejones.wordcount.server.helper;

import org.genejones.wordcount.shared.NodeLetter;
import org.genejones.wordcount.shared.NodeWord;

public class Trie {
	private NodeLetter rootNode = null;

	public Trie() {
		rootNode = new NodeLetter(' ');
	}

	public boolean isWord(String wordToCheck) {
		NodeLetter startNode = rootNode;
		return this.isWordFromNode(wordToCheck, startNode);
	}

	public boolean isWordFromNode(String wordToCheck, NodeLetter startNode) {
		NodeWord nodeWord = getWord(wordToCheck, startNode);
		return (nodeWord != null);
	}
	
	public NodeWord getWord(String wordToCheck){
		return getWord(wordToCheck, rootNode);
	}
			
	public NodeWord getWord(String wordToCheck, NodeLetter startNode) {
		NodeLetter currentNode = rootNode;
		char letters[] = wordToCheck.toUpperCase().toCharArray();
		for (int i = 0; i < wordToCheck.length(); i++) {
			char letter = letters[i];
			if (currentNode.isChild(letter)==false) {
				return null;
			}

			currentNode = currentNode.getChild(letter);

			if (i == (wordToCheck.length() - 1))
				if (currentNode.isWord() == true)
					return new NodeWord(wordToCheck, currentNode.getOccurances());
		}
		return null;
	}

	public void addWord(String wordToAdd) {
		NodeLetter currentNode = rootNode;
		char letters[] = wordToAdd.toUpperCase().toCharArray();
		for (int i = 0; i < wordToAdd.length(); i++) {
			char letter = letters[i];
			NodeLetter letterNode = currentNode.getChild(letter);
			currentNode = letterNode;
		}
		currentNode.setWord(true);
	}


	
	public NodeLetter getRootNode(){
		return this.rootNode;
	}
}