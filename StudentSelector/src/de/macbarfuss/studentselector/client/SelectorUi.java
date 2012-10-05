package de.macbarfuss.studentselector.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.studentselector.shared.Student;
/**
 * Diese Klasse ist EntryPoint UND verwendet den UiBinder.
 * 
 * Überlicherweise dürfte es in einem Projekt nur einen EntryPoint geben, aber mehrere Composites.
 */
public class SelectorUi extends Composite implements EntryPoint {

    /**
     * Interface, damit GWT einen passenden UiBinder erzeugen kann. 
     */
    interface SelectorUiBinder extends UiBinder<Widget, SelectorUi> { }

    /**
     * So wird der UiBinder für dieses Composite erzeugt.
     */
    private static SelectorUiBinder uiBinder = GWT.create(SelectorUiBinder.class);

    /**
     * Variable um den Service zu speichern. In diesem Fall arbeiten wir (wie meistens) asynchron.
     */
    private StudentSelectorServiceAsync service;

    /**
     * Im Konstruktor weißen wir den UiBinder an seine Arbeit zu tun.
     */
    public SelectorUi() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * Jetzt folgt die Liste mit allen Feldern auf dem User-Interface.
     */
    @UiField Button addBtn;
    @UiField Button nextBtn;
    @UiField Button removeBtn;
    @UiField Label chosenName;
    @UiField ListBox students;
    @UiField TextBox newName;

    /**
     * Diese Methode wird ausgeführt wenn der EntryPoint geladen wird.
     * 
     * Ist also quasi unsere main()-Methode.
     */
    @Override
    public void onModuleLoad() {
        // wir werfen das hier definierte UI auf das Browserfenster
        RootPanel.get().add(this);
        
        // holen uns eine Instanz des Services
        service = GWT.create(StudentSelectorService.class);
        
        // und führen eine Methode aus.
        // Diese Daten werden auf dem UI von Anfang an dargestellt, daher hole ich mir die Daten
        // beim Laden des Moduls. Das wirdkliche Schreiben auf das UI passoert im Callback.
        service.getStudents(new GetStudentsCallback());
    }

    // Diese Methode wird ausgeführt, wenn der User auf den Hinzufügen-Button klickt. 
    @UiHandler("addBtn")
    void addNewStudent(ClickEvent e) {
        // wir legen ein neues Objekt an,
        Student s = new Student();
        
        // stzen seine Attribute auf Basis der Felder auf dem UI
        s.setName(newName.getText());
        
        // und führen den Add-Service aus.
        // Die Implementierung des Callbacks steht unten.
        service.addStudent(s, new AddStudentCallback());
        
        // man könnte darüber streiten ob das asynchron sein muss, es wäre auch möglich das
        // Hinzufügen synchron ausführen und sofort den Wert zur Liste hinzufügen. Dann haben wir
        // aber kein Feedback ob der Wert wirklich in der Datenbank steht.
    }

    // das wird beim aktivieren der Zufalls-Abfrage ausgeführt.
    @UiHandler("nextBtn")
    void onClick(ClickEvent e) {
        // wir bitten den Service um einen neuen Zufälligen Wert.
        // Der Callback wird hier direkt im Parameter definiert.
        service.getNextStudent(new AsyncCallback<Student>() {
            
            @Override
            public void onSuccess(Student result) {
                // falls wir einen Studenten zurück bekommen können wir ihn anzeigen.
                chosenName.setText(result.getName());
            }
            
            @Override
            public void onFailure(Throwable caught) {
                // einfaches Beispiel um die Fehlersuchen zu ermöglichen.
                // Wir geben einfach die Fehlernachricht aus.
                chosenName.setText(caught.getMessage());
            }
        });
    }

    // das UiBindung ist hier analog wie oben ... nix neues also.
    @UiHandler("removeBtn")
    void removeSelectedStudent(ClickEvent e) {
        Student s = new Student();
        
        // hier wird für den zu löschenden Studenten der Name aus der LIste gepickt.
        // Wir greifen dazu auf die Indexzahl der Selektion zurück. 
        s.setName(students.getItemText(students.getSelectedIndex()));

        service.removeStudent(s, new RemoveStudentCallback());
    }

    // das ist ein einfacher Callback. Er implementiert das Interface-Template ergänzt um den
    // Richtigen Parametertyp. Ein Blick auf den nächsten Callback zeigt ein anderes Beispiel.  
    public class AddStudentCallback implements AsyncCallback<java.lang.Void> {

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Void result) {
            // jetzt wissen wir, das alles geklappt hat, auf dem Server.
            // Wir fügen den neuen Wert der Liste hinzu
            students.addItem(newName.getText());
            
            // und leeren das Eingabefeld.
            newName.setText("");
        }
    }

// wer bis hier alles verstanden hat findet nix neues in dieser Datei.
    public class GetStudentsCallback implements AsyncCallback<List<Student>> {

        @Override
        public void onFailure(Throwable caught) { }

        @Override
        public void onSuccess(List<Student> result) {
            for (Student s : result) {
                students.addItem(s.getName());
            }
        }
    }

    public class RemoveStudentCallback implements AsyncCallback<java.lang.Void> {

        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(Void result) {
            students.removeItem(students.getSelectedIndex());
        }
        
    }
}
