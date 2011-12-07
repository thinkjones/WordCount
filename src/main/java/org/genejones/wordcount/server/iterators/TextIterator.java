package org.genejones.wordcount.server.iterators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

//Used this URL as inspiration for this code
//http://www.java2s.com/Code/Java/Language-Basics/Javaforinforinlinebylineiterationthroughatextfile.htm

public class TextIterator implements Iterator<String> {

	// The stream we're reading from
	BufferedReader in;

	// Return value of next call to next()
	String nextline;

	public TextIterator(String input) {
		try {
			in = new BufferedReader(new StringReader(input));
			nextline = in.readLine();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public boolean hasNext() {
		return nextline != null;
	}

	public String next() {
		try {
			String result = nextline;

			// If we haven't reached EOF yet
			if (nextline != null) {
				nextline = in.readLine(); // Read another line
				if (nextline == null)
					in.close(); // And close on EOF
			}

			// Return the line we read last time through.
			return result;
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public void remove() {
		throw new UnsupportedOperationException(); 
	}

}
