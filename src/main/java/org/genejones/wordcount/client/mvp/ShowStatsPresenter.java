package org.genejones.wordcount.client.mvp;

import java.util.ArrayList;
import java.util.Map;

import org.genejones.wordcount.client.WordCountEventBus;
import org.genejones.wordcount.client.mvp.view.ShowStatsView;
import org.genejones.wordcount.client.rpc.WordCountService;
import org.genejones.wordcount.client.rpc.WordCountServiceAsync;
import org.genejones.wordcount.shared.ApplicationProperties;
import org.genejones.wordcount.shared.IWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view=ShowStatsView.class)
public class ShowStatsPresenter extends BasePresenter<ShowStatsPresenter.IView, WordCountEventBus>{
	
	private WordCountServiceAsync wordCountSvc = GWT.create(WordCountService.class);
	
	public interface IView extends IWidget{ 
		String getBookSelected();
		void setBookSelected(String bookTitle);
		HasClickHandlers getGlobalStats();
		HasClickHandlers getBookStats();
		void showStats(String title, Map<String, Integer> stats);
		void loadBooks(ArrayList<String> books);
	}
	
	/**
	 * Events
	 */
	public void onShowStats(String bookTitle, boolean refreshBookList){
		getEventBus().showInMain(view, "Book Statistics");
		
		if(refreshBookList== true)
			loadBooks();
		
		if(bookTitle.length() > 0 )
			loadBookStats(bookTitle);
	}
	
	@Override
	public void bind() {
		super.bind();
		loadBooks();
		
		getView().getGlobalStats().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) { 
				loadGlobalStats();
			}
			
		});
		
		getView().getBookStats().addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				String bookTitle = ShowStatsPresenter.this.getView().getBookSelected();
				if(!bookTitle.equals(ApplicationProperties.PLEASE_SELECT))
					loadBookStats(bookTitle);
			}
			
		});
	}
	
	private void loadBooks(){
		
	    // Initialize the service proxy.
	    if (wordCountSvc == null) {
	    	wordCountSvc = GWT.create(WordCountService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<ArrayList<String>>  callback = new AsyncCallback<ArrayList<String>> () {

			public void onFailure(Throwable arg0) {
				Window.alert("Error analyzing data");
			}

			public void onSuccess(ArrayList<String> arg0) {
				getView().loadBooks(arg0);
			}

	    };

	    // Make the call to the stock price service.
	    wordCountSvc.getBooks(callback);
		
	}
	
	private void loadBookStats(String bookTitle){
		
	    // Initialize the service proxy.
	    if (wordCountSvc == null) {
	    	wordCountSvc = GWT.create(WordCountService.class);
	    }
	    final String finalBookTitle = bookTitle;
	    
	    // Set up the callback object.
	    AsyncCallback<Map<String, Integer>>  callback = new AsyncCallback<Map<String, Integer>>  () {
	    	
			public void onFailure(Throwable arg0) {
				Window.alert("Error analyzing data");
			}

			public void onSuccess(Map<String, Integer> arg0) {
				getView().showStats("Statistics for : " + finalBookTitle,arg0);
			}

	    };

	    // Make the call to the stock price service.
	    wordCountSvc.topTenWords(bookTitle, callback);
		
	}
	
	private void loadGlobalStats(){
		
	    // Initialize the service proxy.
	    if (wordCountSvc == null) {
	    	wordCountSvc = GWT.create(WordCountService.class);
	    }
	    
	    // Set up the callback object.
	    AsyncCallback<Map<String, Integer>>  callback = new AsyncCallback<Map<String, Integer>>  () {

			public void onFailure(Throwable arg0) {
				Window.alert("Error analyzing data");
			}

			public void onSuccess(Map<String, Integer> arg0) {
				getView().showStats("Global Statistics",arg0);
			}

	    };

	    // Make the call to the stock price service.
	    wordCountSvc.topTenWords(callback);
		
	}
	
}
