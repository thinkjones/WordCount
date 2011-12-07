package org.genejones.wordcount.server.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.genejones.wordcount.shared.NodeLetter;
import org.genejones.wordcount.shared.NodeWord;

public class CalcTopTenWords {

	private MinHeap minHeap = null;
	
	public CalcTopTenWords(){
		minHeap = new MinHeap();
	}
	
	public Map<String, Integer> topTenWords(Trie trie){
		recurseThroughNodes(trie.getRootNode(), "");
		return convertToMap();
	}
	
	private Map<String, Integer> convertToMap(){
		ArrayList<NodeWord> words = this.minHeap.getSortedArray();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for(NodeWord word : words){
			map.put(word.getWord(), word.getCount());
		}
		
		return map;
		
	}
	
	private void recurseThroughNodes(NodeLetter node, String prefix) {
		
		if (node.isWord() == true) {
			int totalInstances = node.getOccurances();
			String thisWord = prefix + Character.toString(node.getLetter()).toString();
			NodeWord nw = new NodeWord(thisWord, totalInstances);
			minHeap.insertNode(nw);
		}

		for (NodeLetter thisNode : node.getChildren()) {
			if (thisNode != null) {
				String newPrefix ="";
				if(node.getLetter()!='_'&&node.getLetter()!=' '){
					newPrefix = prefix
						+ Character.toString(node.getLetter());
				}
				recurseThroughNodes(thisNode, newPrefix);
			}
		}
	}
	
}
