package org.genejones.wordcount.shared;

public class NodeLetter {
	
	private char letter;
	private boolean isWord = false;
	private NodeLetter[] children = null;
	private int level = 0;
	private int occurances = 0;

	public NodeLetter(char letter){
		letter = Character.toUpperCase(letter);
		this.letter = letter;
		this.children = new NodeLetter[26];	
	}
	public NodeLetter(char letter, int level){
		letter = Character.toUpperCase(letter);
		this.letter = letter;
		this.children = new NodeLetter[26];
		this.level = level;
	}
		
	public NodeLetter getChild(char letter){
		
		int index = NodeLetter.getIndexValue(letter);
		
		if(this.children==null || this.children[index]==null){
			NodeLetter letterNode = new NodeLetter(letter,this.level + 1);
			this.children[index] = letterNode;
		}
		return this.children[index];
	}
	
	public boolean isChild(char letter){
		//returns node if this child as this leter
		int index = NodeLetter.getIndexValue(letter);
		
		if(this.children==null||this.children[index]==null){
			return false;
		}else{
			return true;
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
		occurances++;
	}
	
	public NodeLetter[] getChildren(){
		return this.children;
	}
	
	public char getLetter(){
		return this.letter;
	}
	public int getOccurances() {
		return occurances;
	}
	
	public static int getIndexValue(char letter){
		letter = Character.toUpperCase(letter);
		return letter - 'A';
	}
}
