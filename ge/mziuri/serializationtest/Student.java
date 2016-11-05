
package ge.mziuri.serializationtest;

import java.io.Serializable;

public class Student implements Serializable {
    
    private String firstName;
    
    private String secondName;

    public Student() {
    }

    public Student(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    
    
    
}
