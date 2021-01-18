package jface.elements.model;

import java.io.Serializable;

/**
 * The main model class
 * 
 * @author SZabara
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int group;

    private boolean swtDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public boolean isSwtDone() {
        return swtDone;
    }

    public void setSwtDone(boolean swtDone) {
        this.swtDone = swtDone;
    }

    public Person() {
    }

    public Person(String name, int group, boolean swtDone) {
        super();
        this.name = name;
        this.group = group;
        this.swtDone = swtDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + group;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (swtDone ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Person other = (Person) obj;
        if (group != other.group) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (swtDone != other.swtDone) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name + " " + group + " " + swtDone;
    }
}