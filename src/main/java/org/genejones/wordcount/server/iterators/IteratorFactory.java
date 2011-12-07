package org.genejones.wordcount.server.iterators;

import java.util.Iterator;

import org.genejones.wordcount.shared.BookTextRetrievalType;

public class IteratorFactory implements IIteratorFactory{

	public Iterator<String> create(BookTextRetrievalType type, String input) {
		if(type==BookTextRetrievalType.FromTextSent)
			return new TextIterator(input);
		if(type==BookTextRetrievalType.FromUrl)
			return new UrlFileIterator(input);
		if(type==BookTextRetrievalType.FromFileUploaded)
			return new TextFileIterator(input);
		return null;
	}
}
