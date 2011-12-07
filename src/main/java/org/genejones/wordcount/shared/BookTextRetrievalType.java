package org.genejones.wordcount.shared;

import java.util.EnumSet;

public enum BookTextRetrievalType {
	FromUrl("From URL"), FromTextSent("From Text"), FromFileUploaded(
			"From File Upload");

	private String value;

	BookTextRetrievalType(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}

	public static BookTextRetrievalType getByValue(String value) {
		BookTextRetrievalType returnValue = null;
		for (final BookTextRetrievalType element : EnumSet
				.allOf(BookTextRetrievalType.class)) {
			if (element.toString().equals(value)) {
				returnValue = element;
			}
		}
		return returnValue;
	}
}
