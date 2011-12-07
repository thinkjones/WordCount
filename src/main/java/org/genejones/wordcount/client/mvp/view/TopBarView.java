package org.genejones.wordcount.client.mvp.view;

import org.genejones.wordcount.client.mvp.MainPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import org.genejones.wordcount.client.mvp.TopBarPresenter;

public class TopBarView  extends Composite implements TopBarPresenter.IView{

	private static TopBarUiBinder uiBinder = GWT.create(TopBarUiBinder.class);

	interface TopBarUiBinder extends UiBinder<Widget, TopBarView> {
	}

	public TopBarView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget getMyWidget() {
		return this.asWidget();
	}




}
