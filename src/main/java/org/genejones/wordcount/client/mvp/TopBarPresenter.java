package org.genejones.wordcount.client.mvp;

import org.genejones.wordcount.client.WordCountEventBus;
import org.genejones.wordcount.client.mvp.view.TopBarView;
import org.genejones.wordcount.shared.IWidget;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

@Presenter(view=TopBarView.class)
public class TopBarPresenter extends BasePresenter<TopBarPresenter.IView, WordCountEventBus>{
	
	public interface IView extends IWidget{
	}
	
	/**
	 * Events
	 */
	public void onSetupApp(){
		getEventBus().showAtTop(view);
	}
}
