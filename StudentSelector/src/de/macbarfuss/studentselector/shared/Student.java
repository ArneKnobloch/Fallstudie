package de.macbarfuss.studentselector.shared;

import java.io.Serializable;

/**
 * Ein POJO. Eine serializierbare KLasse zum rumschubsen von Daten.
 * 
 * Man könnte auch sagen: Das ist unser Datenbankschema.
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -8981268387553255156L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String fullname) {
        name = fullname;
    }

}
