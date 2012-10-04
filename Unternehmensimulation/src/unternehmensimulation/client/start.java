package unternehmensimulation.client;

import unternehmensimulation.shared.Unternehmen;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Slider;

public class start extends Composite implements EntryPoint, HasText {

	private static startUiBinder uiBinder = GWT.create(startUiBinder.class);

	interface startUiBinder extends UiBinder<Widget, start> {
	}

	public start() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	Unternehmen eigenesUN;
	

	@UiField TextBox tNameUN;
	@UiField Slider sPersonal;
	@UiField Slider sKapital;
	@UiField Slider sQualitaet;	
	@UiField Slider sKapazitaet;	
	@UiField Button bWeiter;
	@UiField Button bKonkurrenz;
	@UiField Button bDaten;

	public start(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		bWeiter.setText(firstName);
	}
	@UiHandler("bKonkurrenz")
	void onClickKonkurrenz(ClickEvent e) {
		
	}
	@UiHandler("bDaten")
	void onClickDaten(ClickEvent e) {
		
	}

	@UiHandler("bWeiter")
	void onClickWeiter(ClickEvent e) {
		String name = tNameUN.getText();
		int personal = sPersonal.getValue();
		Window.alert("Variablen" + name + personal);
		eigenesUN = new Unternehmen(tNameUN.getText(), sPersonal.getValue(), sKapital.getValue(), sQualitaet.getValue(), sKapazitaet.getValue());

	}

	public void setText(String text) {
		bWeiter.setText(text);
	}

	public String getText() {
		return bWeiter.getText();
	}

	@Override
	public void onModuleLoad() {
		sPersonal.setMinValue(100);
		sPersonal.setMaxValue(1000);
		sPersonal.setIncrement(100);
		
		sQualitaet.setMinValue(100);
		sQualitaet.setMaxValue(1000);
		sQualitaet.setIncrement(100);		
		
		sKapazitaet.setMinValue(100);
		sKapazitaet.setMaxValue(1000);
		sKapazitaet.setIncrement(100);
		
		sKapital.setMinValue(100);
		sKapital.setMaxValue(1000);
		sKapital.setIncrement(100);
		
		bWeiter.setText("weiter");
		bKonkurrenz.setText("Konkurrenz definieren");
		bDaten.setText("Unternehmensdaten laden");
		RootPanel.get().add(this);
		
	}

}
