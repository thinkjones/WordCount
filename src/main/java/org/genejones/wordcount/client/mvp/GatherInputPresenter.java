package org.genejones.wordcount.client.mvp;

import org.genejones.wordcount.client.WordCountEventBus;
import org.genejones.wordcount.client.mvp.view.GatherInputView;
import org.genejones.wordcount.client.rpc.WordCountService;
import org.genejones.wordcount.client.rpc.WordCountServiceAsync;
import org.genejones.wordcount.shared.BookTextRetrievalType;
import org.genejones.wordcount.shared.IWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view = GatherInputView.class)
public class GatherInputPresenter extends
		BasePresenter<GatherInputPresenter.IView, WordCountEventBus> {

	private WordCountServiceAsync wordCountSvc = GWT
			.create(WordCountService.class);

	public interface IView extends IWidget {
		HasText getBookTitle();

		HasText getBookText();

		String getGenerateMethod();

		HasClickHandlers getSubmit();
		
		void showError(String text);
	}

	private String lastBookTitle = "";
	
	/**
	 * Events
	 */
	public void onSetupApp() {
		getEventBus().showInMain(view, "Enter Book Information");
	}

	@Override
	public void bind() {
		super.bind();

		getView().getSubmit().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				String bookTitle = getView().getBookTitle().getText();
				String bookText = sanitizeInput(getView().getBookText().getText());
				BookTextRetrievalType generateMethod = BookTextRetrievalType.FromTextSent; // BookTextRetrievalType.getByValue(getView().getGenerateMethod());
				
				GatherInputPresenter.this.getView().showError("");
				
				//Validation
				if(GatherInputPresenter.this.justAsciiLetters(bookText)==true){
					GatherInputPresenter.this.generateBookStats(bookTitle,
							bookText, generateMethod);
				}else{
					GatherInputPresenter.this.getView().showError("Book text can only contain ascii characters a-z.");
				}
			}

		});
	}

	private void generateBookStats(String bookTitle, String bookText,
			BookTextRetrievalType generateMethod) {
		
		this.lastBookTitle = bookTitle;
				
		// Initialize the service proxy.
		if (wordCountSvc == null) {
			wordCountSvc = GWT.create(WordCountService.class);
		}

		// Set up the callback object.
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			public void onFailure(Throwable arg0) {
				Window.alert("Error procssing data " + arg0.getMessage());
			}

			public void onSuccess(Void arg0) {
				GatherInputPresenter.this.navigateToBookStats();
			}

		};

		// Make the call to the stock price service.
		wordCountSvc.generateBookStats(bookTitle, bookText, generateMethod,
				callback);
	}
	
	private Boolean justAsciiLetters(String input) {
		String pattern = "\\d*";
	    RegExp regExp = RegExp.compile(pattern, "im");
	    MatchResult matcher = regExp.exec(input);
	    return !(matcher == null);
	}
	
	private String sanitizeInput(String input){
		return input.replaceAll("[^a-zA-Z \n]", "");
	}
	
	private void navigateToBookStats(){
		if(this.lastBookTitle.length() > 0){
			getEventBus().showStats(lastBookTitle, true);
		}
	}


}
