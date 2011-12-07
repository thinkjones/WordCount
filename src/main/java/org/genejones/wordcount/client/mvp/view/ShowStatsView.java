package org.genejones.wordcount.client.mvp.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.genejones.wordcount.client.mvp.ShowStatsPresenter;
import org.genejones.wordcount.client.widget.VerticalFlowPanel;
import org.genejones.wordcount.shared.ApplicationProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ShowStatsView extends Composite implements
		ShowStatsPresenter.IView {
	private static ShowStatsViewUiBinder uiBinder = GWT
			.create(ShowStatsViewUiBinder.class);

	interface ShowStatsViewUiBinder extends UiBinder<Widget, ShowStatsView> {
	}

	public ShowStatsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	ListBox selectBook;
	@UiField
	Anchor showGlobal;
	@UiField
	Anchor showBook;

	@UiField
	VerticalFlowPanel pnlStats;
	@UiField
	Label lblStatsTitle;

	@Override
	public Widget getMyWidget() {
		return this.asWidget();
	}

	@Override
	public String getBookSelected() {
		return selectBook.getItemText(selectBook.getSelectedIndex());
	}

	@Override
	public void setBookSelected(String bookTitle) {

	}

	@Override
	public void showStats(String title, Map<String, Integer> stats) {
		lblStatsTitle.setText(title);

		pnlStats.clear();

		//Map<Integer, String> newStats = stats; //reverseStats(stats);
		ArrayList<String> sorted = sortHashMap(stats);

		if (stats.size() < 1)
			pnlStats.add(new Label("No Current Stats"));

		for (String line : sorted) {
			pnlStats.add(new Label(line));
		}
	}

	private ArrayList<String> sortHashMap(Map<String, Integer> input){
	  boolean finished = false;
	  ArrayList<String> list = new ArrayList<String>();
	  while(finished==false){
		  String lowestKey = getLowestKey(input);
		  String listItem = lowestKey + "\t:\t" + input.get(lowestKey);
		  list.add(listItem);
		  input.remove(lowestKey);
		  
		  if(input.isEmpty()==true)
			  finished = true;
	  }
	  return list;
	}
	
	private String getLowestKey(Map<String, Integer> input){
		Integer lowest = -1;
		String lowestKey = "";
		for(String key : input.keySet()){
			if(lowest == -1){
				lowestKey = key;
				lowest = input.get(key);
			}else{
				if(input.get(key) < lowest){
					lowestKey = key;
					lowest = input.get(key);
				}
			}
		}
		return lowestKey;
	}


	@Override
	public void loadBooks(ArrayList<String> books) {
		int index = 0;

		selectBook.clear();

		selectBook.insertItem(ApplicationProperties.PLEASE_SELECT, index);
		index++;

		for (String book : books) {
			selectBook.insertItem(book, index);
			index++;
		}

	}

	@Override
	public HasClickHandlers getGlobalStats() {
		return showGlobal;
	}

	@Override
	public HasClickHandlers getBookStats() {
		return showBook;
	}

}
