package jface.elements.model;

import java.util.List;

import ua.com.rcp.zabara.Utils;

/**
 * 
 * The model provider basis on two things: the list of persons and text file
 * 
 * @author SZabara
 */
public enum ModelProvider {

    INSTANCE;

    private List<Person> persons;

    private ModelProvider() { 
        persons = Utils.readPersonsFromFile();
    }

    /**
     * return ArrayList with persons from file
     * 
     * @return ArrayList with persons from file
     */
    public List<Person> getPersons() {
        return persons;
    }


}