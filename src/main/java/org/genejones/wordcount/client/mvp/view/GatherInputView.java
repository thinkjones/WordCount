package org.genejones.wordcount.client.mvp.view;

import org.genejones.wordcount.client.mvp.GatherInputPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GatherInputView extends Composite implements GatherInputPresenter.IView{

	private static GatherInputViewUiBinder uiBinder = GWT
			.create(GatherInputViewUiBinder.class);

	interface GatherInputViewUiBinder extends UiBinder<Widget, GatherInputView> {
	}

	public GatherInputView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField Button button;
	@UiField TextBox bookTitle;
	//@UiField ListBox generateMethod;
	@UiField TextArea bookText;
	@UiField Label errorMsg;

	public Widget getMyWidget() {
		return this.asWidget();
	}

	public HasClickHandlers getSubmit() {
		return button;
	}

	public HasText getBookTitle() {
		return bookTitle;
	}

	public HasText getBookText() {
		return bookText;
	}

	public String getGenerateMethod() {
		return "fromText"; //generateMethod.getItemText(generateMethod.getSelectedIndex());
	}

	@Override
	public void showError(String text) {
		errorMsg.setVisible(text.length() > 0);
		errorMsg.setText(text);
	}
}
