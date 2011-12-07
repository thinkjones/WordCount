package org.genejones.wordcount.client;

import org.genejones.wordcount.client.mvp.GatherInputPresenter;
import org.genejones.wordcount.client.mvp.MainPresenter;
import org.genejones.wordcount.client.mvp.ShowStatsPresenter;
import org.genejones.wordcount.client.mvp.TopBarPresenter;
import org.genejones.wordcount.client.mvp.view.MainView;
import org.genejones.wordcount.shared.IWidget;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

@Events(startView = MainView.class)
public interface WordCountEventBus extends EventBus {

	@Start
	@Event(handlers = { MainPresenter.class })
	void start();

	/**
	 * Set Main Components
	 */
	@Event(handlers = { MainPresenter.class }) void showInMain(IWidget view, String title);
	@Event(handlers = { MainPresenter.class }) void showAtTop(IWidget view);
	
	// Child classes
	@Event(handlers = {TopBarPresenter.class, GatherInputPresenter.class}) void setupApp();
	
	@Event(handlers = {ShowStatsPresenter.class}) void showStats(String bookTitle, boolean refreshBookList);
	
	
}
