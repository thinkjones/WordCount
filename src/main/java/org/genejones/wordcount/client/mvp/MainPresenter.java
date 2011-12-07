package org.genejones.wordcount.client.mvp;

import org.genejones.wordcount.client.WordCountEventBus;
import org.genejones.wordcount.client.mvp.view.MainView;
import org.genejones.wordcount.shared.IWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = MainView.class)
public class MainPresenter extends
		BasePresenter<MainPresenter.IView, WordCountEventBus> {

	public interface IView extends IWidget {
		void showTopBar(IWidget widget);

		void changeMainWidget(IWidget widget);

		HasClickHandlers getShowInput();

		HasClickHandlers getShowStats();
		
		void setHeader(String text);

	}

	/**
	 * Events
	 */

	public void onStart() {
		// This is the entry point once the application is loaded.
		getEventBus().setupApp();
	}

	public void onShowAtTop(IWidget widget) {
		view.showTopBar(widget);
	}

	public void onShowInMain(IWidget widget, String title) {
		view.changeMainWidget(widget);
		view.setHeader(title);
	}

	@Override
	public void bind() {
		super.bind();
		getView().getShowInput().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getEventBus().setupApp();

			}

		});
		
		getView().getShowStats().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getEventBus().showStats("", false);
			}

		});
	}
}
