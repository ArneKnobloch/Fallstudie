package de.macbarfuss.studentselector.server;

import org.junit.Assert;
import org.junit.Test;

import de.macbarfuss.studentselector.shared.Student;

public class TestStudentSelectorService {

    @Test
    public void testgetNextStudent() {
        StudentSelectorServiceImpl impl = new StudentSelectorServiceImpl();
        Student i = impl.getNextStudent();
        Assert.assertNotNull(i);
    }

}
