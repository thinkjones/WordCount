package org.genejones.wordcount.client.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class HeaderOne extends Composite implements HasText {

	private static HeaderOneUiBinder uiBinder = GWT.create(HeaderOneUiBinder.class);

	interface HeaderOneUiBinder extends UiBinder<Widget, HeaderOne> {
	}

	@UiField
	HeadingElement header;

	public HeaderOne() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public String getText() {
		return header.getInnerHTML();
	}

	@Override
	public void setText(String arg0) {
		header.setInnerHTML(arg0);
	}

}
