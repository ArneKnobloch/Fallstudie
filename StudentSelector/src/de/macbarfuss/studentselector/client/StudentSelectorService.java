package de.macbarfuss.studentselector.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.macbarfuss.studentselector.shared.Student;

/**
 * Ein einfaches Service-Interface.
 * 
 * Diese Klasse ist das Wunschkonzert bei der Serviceerstellung. Hier beginnt die Erstellung, denn
 * an dieser Stelle wird festgelegt wie die Methoden auf dem Server aussehen sollen.
 *
 * Wenn in nachgelagerten Dateien Fehler stecken erkennt GWT das häufig schon.
 * 
 * Wir erben von RemoteService. Damit ist dieses Interface bereit in einem ServletContainer geladen
 * zu werden.
 * 
 * Denkbar ist jede beliebige Kombination von Parametern und Rückgabewerten, was Java so zu bieten
 * hat. Die Werte müssen jedoch serializierbar sein.
 */
@RemoteServiceRelativePath("StudentSelectorService")
public interface StudentSelectorService extends RemoteService {

    void addStudent(Student student);

    List<Student> getStudents();

    Student getNextStudent();

    void removeStudent(Student student);
}
