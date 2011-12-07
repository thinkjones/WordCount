package org.genejones.wordcount.shared;

import java.io.Serializable;

public class NodeWord implements Comparable<NodeWord>, Serializable{

	private String word;
	private int count;
	
	//For Serialization
	private NodeWord(){}
	
	public NodeWord(String word, int count){
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int compareTo(NodeWord arg0) {
		if(this.count > arg0.getCount())
			return 1;
		else if(this.count < arg0.getCount())
			return -1;
		else
			return 0;
	}
	
}
