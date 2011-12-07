package org.genejones.wordcount.server.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.genejones.wordcount.shared.NodeWord;

public class MinHeap {

	// Use an array to represent a min-heap.
	public NodeWord[] dataStruture = null;

	private int lastNodePopulated = -1;

	public MinHeap() {
		this.dataStruture = new NodeWord[10];
	}

	public void insertNode(NodeWord node) {

		// Special case top ten nodes not yet populated
		// therefore add node at last position and bubbleup
		if (lastNodePopulated < 9) {
			lastNodePopulated++;
			dataStruture[lastNodePopulated] = node;
			bubbleUp(lastNodePopulated);
			return;
		} else {
			// we now have a Min-Heap with ten elements so we can always compare
			// to the minimum entry
			// the root node and determine whether we need to add. If it is
			// greater than the root node we
			// replace the root node and bubble down.
			if (node.getCount() > this.getRootNode().getCount()) {
				dataStruture[0] = node;
				bubbleDown();
			}
		}

	}

	private void bubbleUp(int index) {
		int currentIndex = index;
		boolean finished = false;
		while (finished != true) {
			NodeWord node = dataStruture[currentIndex];
			NodeWord parentNode = getParent(currentIndex);
			if (parentNode != null && node.getCount() < parentNode.getCount()) {
				// Swap positions as parent node is less than the
				int parentIndex = this.getParentIndex(currentIndex);
				dataStruture[currentIndex] = parentNode;
				dataStruture[parentIndex] = node;
				currentIndex = parentIndex;
			} else {
				finished = true;
			}
		}
	}

	private void bubbleDown() {
		int currentIndex = 0;
		boolean finished = false;
		while (finished != true) {
			NodeWord node = dataStruture[currentIndex];
			NodeWord swapNode = null;
			int swapIndex = getSmallerChildIndex(currentIndex, node.getCount());

			if (swapIndex > -1  && swapIndex < 10) {
				swapNode = dataStruture[swapIndex];

				// Swap positions as parent node is less than the
				if(node.getCount() > swapNode.getCount()){
					dataStruture[currentIndex] = swapNode;
					dataStruture[swapIndex] = node;
					currentIndex = swapIndex;
				}else{
					finished=true;
				}
			} else {
				finished = true;
			}
		}
	}

	private int getSmallerChildIndex(int currentIndex, int currentCount) {
		NodeWord leftChild = getLeftChild(currentIndex);
		NodeWord rightChild = getRightChild(currentIndex);
		int swapIndex = -1;
		int leftChildCount = -1;
		int rightChildCount = -1;

		// Get Left Child
		if (leftChild != null)
			leftChildCount = leftChild.getCount();

		// Get Right Child
		if (rightChild != null)
			rightChildCount = rightChild.getCount();

		if(leftChild != null && rightChild != null){
			if (leftChildCount < rightChildCount) {
				swapIndex = this.getLeftChildIndex(currentIndex);
			} else {
				swapIndex = this.getRightChildIndex(currentIndex);
			}
		}

		if(leftChild != null && rightChild == null){
			swapIndex = this.getLeftChildIndex(currentIndex);
		}
		
		if(rightChild != null && leftChild == null){
			swapIndex = this.getRightChildIndex(currentIndex);
		}		

		return swapIndex;
	}

	public NodeWord getRootNode() {
		return dataStruture[0];
	}

	private NodeWord getLastNode() {
		return dataStruture[lastNodePopulated];
	}

	private NodeWord getLeftChild(int index) {
		int childIndex = getLeftChildIndex(index);
		if (childIndex < 10)
			return dataStruture[childIndex];
		else
			return null;
	}

	private int getLeftChildIndex(int index) {
		int position = index + 1;
		int childPos = position * 2;
		return childPos - 1;
	}

	private NodeWord getRightChild(int index) {
		int childIndex = getRightChildIndex(index);
		if (childIndex < 10)
			return dataStruture[childIndex];
		else
			return null;
	}

	private int getRightChildIndex(int index) {
		int position = index + 1;
		int childPos = (position * 2) + 1;
		return childPos - 1;
	}

	private int getParentIndex(int index) {
		if (index > 0) {
			return index / 2;
		} else {
			return -1; // at root
		}
	}

	private NodeWord getParent(int index) {
		int parentIndex = getParentIndex(index);
		if (parentIndex > -1) {
			return dataStruture[parentIndex];
		} else {
			return null; // at root
		}
	}

	public ArrayList<NodeWord> getSortedArray() {
		ArrayList<NodeWord> array = new ArrayList<NodeWord>(
				Arrays.asList(dataStruture));
		array = removeNullEntries(array);
		Collections.sort(array);
		return array;
	}
	
	private ArrayList<NodeWord> removeNullEntries(ArrayList<NodeWord> list){
		ArrayList<NodeWord> newList = new ArrayList<NodeWord>();
		
		for(NodeWord word : list){
			if(word!=null){
				newList.add(word);
			}
		}
		return newList;
	}
	
	public NodeWord[] getDataStruture(){
		return this.dataStruture;
	}
}
