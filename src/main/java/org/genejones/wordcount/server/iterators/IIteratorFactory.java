package org.genejones.wordcount.server.iterators;

import java.util.Iterator;

import org.genejones.wordcount.shared.BookTextRetrievalType;

public interface IIteratorFactory {

	public Iterator<String> create(BookTextRetrievalType type, String input);
	
}
