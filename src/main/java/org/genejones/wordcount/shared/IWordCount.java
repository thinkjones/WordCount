package org.genejones.wordcount.shared;

import java.util.Iterator;
import java.util.Map;

/** A service that counts words in books. */
public interface IWordCount {
    /**
     * Count all words in a given book in preparation for later querying.
     *
     * @param bookTitle The title of the book. Must be a non-empty String.
     * @param lines     The book's text. Must not be null.
     */
    public void countWords(String bookTitle, Iterator<String> lines);

    /**
     * Provides the top ten most common words found cumulatively across all
     * books that have been processed by the service.
     *
     * @return A mapping of at most ten words to their respective counts. Never
     *         null, but possibly empty.
     */
    public Map<String, Integer> topTenWords();

    /**
     * Provides the top ten most common words found in a book that was already
     * processed by the service.
     *
     * @param bookTitle The title of the book. Must be a non-empty String.
     * @return A mapping of at most ten words to their respective counts. Never
     *         null, but possibly empty.
     */
    public Map<String, Integer> topTenWords(String bookTitle);
}

