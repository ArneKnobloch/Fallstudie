package unternehmensimulation.client;

import java.awt.Container;

import unternehmensimulation.shared.Runde;
import unternehmensimulation.shared.Unternehmen;

import com.gargoylesoftware.htmlunit.javascript.host.EventHandler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.layout.client.Layout;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutCommand;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuBar;
import com.sencha.gxt.widget.core.client.menu.MenuBarItem;
import com.sencha.gxt.widget.core.client.menu.MenuItem;


public class start extends Composite implements EntryPoint, HasText {

	private static startUiBinder uiBinder = GWT.create(startUiBinder.class);

	interface startUiBinder extends UiBinder<Widget, start> {
	}

	public start() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	Unternehmen eigenesUN;
	Runde runde[];
	

	@UiField TextBox tNameUN;
	@UiField TextBox currentPersonal;
	@UiField Slider sPersonal;
	@UiField Slider sKapital;
	@UiField Slider sQualitaet;	
	@UiField Slider sKapazitaet;	
	@UiField Button bWeiter;

	public start(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		bWeiter.setText(firstName);
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
		
		
		//MENUBAR
		
		MenuBar bar = new MenuBar();

	    Menu menu = new Menu();
	    menu.add(new MenuItem("Schnelles Spiel"));

	    MenuBarItem item = new MenuBarItem("Neues Spiel starten", menu);
	    bar.add(item);

	    menu = new Menu();
	    menu.add(new MenuItem("Spiel 1"));
	    menu.add(new MenuItem("Spiel 2"));

	    item = new MenuBarItem("Spiel laden", menu);
	    bar.add(item);
	    
	    menu = new Menu();

	    item = new MenuBarItem("Optionen", menu);
	    menu.add(new MenuItem("Spieleinstellungen"));
	    bar.add(item);

	    RootPanel.get().add(bar);
	    
	    //TABPANEL
	    
	    final TabPanel panel = new TabPanel();
	    panel.setTabScroll(true);
	    panel.setWidth("100%");
	    panel.setResizeTabs(true);
	    
	    
	    runde = new Runde[8];
	    for (int i = 1; i < 8; i++) {
	    	if(i!=1){
	    		runde[i] = new Runde();
	    		panel.add(runde[i].getRunde(), new TabItemConfig("Runde " + i) );
	    	}
	    	else{
	        sPersonal.setMinValue(100);
			sPersonal.setMaxValue(1000);
			sPersonal.setIncrement(100);
			sPersonal.addValueChangeHandler(new ValueChangeHandler<Integer>() {
				public void onValueChange(ValueChangeEvent<Integer> event) {
					currentPersonal.setText("" + event.getValue());

				}
			});
			
		
			
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
			currentPersonal.setText("100");
			
			panel.add(this, new TabItemConfig("Unternehmen definieren"));
	    	}
	    }

	    //config.setClosable(true);
	    //panel.add(new Label("2"), config);

	    panel.setActiveWidget(panel.getWidget(0));
	 
	    
	    panel.getWidget(1);
   		panel.addSelectionHandler(new SelectionHandler<Widget>() {
            public void onSelection(SelectionEvent<Widget> event) {
                    Widget item = event.getSelectedItem();
                    TabItemConfig config = panel.getConfig(item);
                    String name = config.getText();
//                    
            }
		
   		});
	    RootPanel.get().add(panel);
		
	}

	
}

