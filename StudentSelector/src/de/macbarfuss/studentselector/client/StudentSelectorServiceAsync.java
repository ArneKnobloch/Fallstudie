package de.macbarfuss.studentselector.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.macbarfuss.studentselector.shared.Student;

/**
 * So sieht die Erweiterung um die Callbacks aus.
 * 
 * Alle Methoden werden geändert, sodass als weiterer Parameter das CallbackInterface dazukommt. Der
 * gültige Rückgabewert steht in den Klammern <>. Die Methoden sind jetzt alle void.
 */
public interface StudentSelectorServiceAsync {

    void addStudent(Student student, AsyncCallback<Void> callback);

    void getStudents(AsyncCallback<List<Student>> callback);

    void getNextStudent(AsyncCallback<Student> callback);

    void removeStudent(Student student, AsyncCallback<Void> callback);

}
