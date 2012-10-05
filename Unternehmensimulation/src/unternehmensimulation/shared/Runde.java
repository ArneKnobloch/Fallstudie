package unternehmensimulation.shared;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

public class Runde extends Composite implements  HasText {

	private static RundeUiBinder uiBinder = GWT.create(RundeUiBinder.class);

	interface RundeUiBinder extends UiBinder<Widget, Runde> {
	}

	public Runde() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	Label lVersuch = new Label("Hallo");
	TextBox tBox = new TextBox();
	ContentPanel panel = new ContentPanel();

	public Runde(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}
	
	public Widget getRunde(){
		setText("Hallo");
		panel.add(button);
		panel.add(lVersuch);
		return panel;
		
	}

//	@Override
//	public void onModuleLoad() {
//		// TODO Auto-generated method stub
//	}

}
