package de.macbarfuss.studentselector.server;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Diese Klasse dürft ihr (vorerst) mal einfach kopieren. Ersetzt aber bitte den Datenbanknamen.
 */
public final class PersistenceManager {

    private PersistenceManager() {
    }

    /**
     * @return eine Datastore-Instanz, welche auf unserer Applikationsdatenbank aufistzt.
     */
    public static Datastore getDatastore() {
        return new Morphia().createDatastore(getMongo(), "studentselector");
    }

    // hier wird ein Client für die MongoDB erzeugt.
    private static Mongo getMongo() {
        Mongo m = null;
        try {
            m = new Mongo();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return m;
    }
}
