package org.genejones.wordcount.client.mvp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.genejones.wordcount.client.mvp.MainPresenter;
import org.genejones.wordcount.client.widget.HeaderOne;
import org.genejones.wordcount.client.widget.VerticalFlowPanel;
import org.genejones.wordcount.shared.IWidget;

public class MainView extends Composite implements MainPresenter.IView {

	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}
	
	@UiField SimplePanel topPanel;
	@UiField VerticalFlowPanel mainPanel;
	
	@UiField Anchor lnkInput;
	@UiField Anchor lnkStats;
	
	@UiField HeaderOne header;

	public MainView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget getMyWidget() {
		return this.asWidget();
	}

	public void showTopBar(IWidget widget) {
		topPanel.setWidget(widget.getMyWidget());
	}

	public void changeMainWidget(IWidget widget) {
		mainPanel.clear();
		if (widget != null)
			mainPanel.add(widget.getMyWidget());
		
	}

	@Override
	public HasClickHandlers getShowInput() {
		return lnkInput;
	}

	@Override
	public HasClickHandlers getShowStats() {
		return lnkStats;
	}

	@Override
	public void setHeader(String text) {
		header.setText(text);		
	}


}
