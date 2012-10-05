package de.macbarfuss.studentselector.server;

import java.util.List;
import java.util.Random;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.macbarfuss.studentselector.client.StudentSelectorService;
import de.macbarfuss.studentselector.shared.Student;

/**
 * Hier implementieren wir unseren Service. Mit anderen Worten: Das ist unser Servlet.
 *
 * Beachtet, dass wir das Interface implementieren, welches wir uns auf Clientseite gewünscht haben.  
 */
public class StudentSelectorServiceImpl
        extends RemoteServiceServlet
        implements StudentSelectorService {

    private static final long serialVersionUID = -179774088492873807L;

    @Override
    public void addStudent(Student student) {
        // in die Datenbank schreiben
        PersistenceManager.getDatastore().save(student);
    }

    @Override
    public List<Student> getStudents() {
        // quasi wie "Select * from Student;"
        return PersistenceManager.getDatastore().createQuery(Student.class).asList();
    }

    @Override
    public Student getNextStudent() {
        Random rand = new Random();
        List<Student> students = getStudents();
        return students.get(rand.nextInt(students.size()));
    }

    @Override
    public void removeStudent(Student student) {
        Datastore ds = PersistenceManager.getDatastore();
        
        // wir erzeugen ein Query-Objekt über alle Studenten und fügen ihm ein Filter nach Name zu.
        // quasi wie "Select * from Student where name = student.getName();"
        // damit haben wir eine Menge von Studenten (hoffentlich nur einen) ausgewählt.
        Query<Student> q = ds.createQuery(
                Student.class).filter("name =", student.getName());
        
        // diese Auswahl löschen wir
        ds.delete(q);        
    }

}
