package jface.elements.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger LOG = LoggerFactory.getLogger(ModelProvider.class);


    private ModelProvider() {
        persons = readPersonsFromFile();
    }
    
    /**
     * return ArrayList with persons from file
     * @return ArrayList with persons from file
     */
    public List<Person> getPersons() {
        return persons;
    }
    
    /**
     * reads persons from the file and put their to the ArrayList
     * @return ArrayList with persons from file
     */
    private List<Person> readPersonsFromFile() {

        List<Person> persons = new ArrayList<Person>();

        File databasefile = new File(Utils.DATABASE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(databasefile))) {

            while (reader.ready()) {

                String personDataString = reader.readLine();
                if (!personDataString.equals("")) {
                    String[] personDataElements = personDataString.split(",");
                    try {
                        Person person = new Person(personDataElements[0], Integer.parseInt(personDataElements[1]),
                                Boolean.parseBoolean(personDataElements[2]));
                        persons.add(person);
                    } catch (Exception e) {
                        LOG.warn("failure when trying to read a person");
                        System.out.println("Cant parse line to person correctly: " + personDataString);
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            LOG.warn("failure when trying to find the DATABASE file");
        } catch (IOException e) {
            LOG.warn("failure when trying to read the DATABASE file");
        }

        return persons;

    }
}