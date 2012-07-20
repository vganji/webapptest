package com.proquest.interview.phonebook;

import java.util.List;

import com.proquest.interview.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhoneBookImpl implements PhoneBook {

    public List people;

    @Override
    public void addPerson(Person newPerson) {
        try {
            DatabaseUtil.addPerson(newPerson);
            System.out.println("Added Successfully");
        } catch (SQLException ex) {
            System.out.println("ERROR:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR:" + ex.getMessage());
        }
    }

    @Override
    public Person findPerson(String firstName, String lastName) {
        try {
            return DatabaseUtil.findPerson(firstName, lastName);
        } catch (SQLException ex) {
            System.out.println("ERROR:" + ex.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR:" + ex.getMessage());
            return null;
        }
    }

    // I added a lookup method in DatabaseUtil to get a list of all Persons
    // Also the findPeople might not return accurate result as there might be multiple people with the same name
    // and we are not returning any array
    
    
    
    public static void main(String[] args) {
        DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

        /*
         * TODO: create person objects and put them in the PhoneBook and
         * database John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
         * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
         */
        // TODO: print the phone book out to System.out
        // TODO: find Cynthia Smith and print out just her entry
        // TODO: insert the new person objects into the database

        PhoneBookImpl pbi = new PhoneBookImpl();
        Person p1 = new Person();
        Person p2 = new Person();;

        p1.name = "John Smith";
        p1.phoneNumber = "(248) 123-4567";
        p1.address = "1234 Sand Hill Dr, Royal Oak, MI";

        p2.name = "Cynthia Smith";
        p2.phoneNumber = "(824) 128-8758";
        p2.address = "875 Main St, Ann Arbor, MI";

        pbi.addPerson(p1);
        pbi.addPerson(p2);

        // Will list all the people in the phone book
        try {
            List people = DatabaseUtil.lookupPerson();

            for (int i = 0; i < people.size(); i++) {

                Person temp = (Person) people.get(i);
                System.out.println("Name:" + temp.name);
                System.out.println("Phone Number:" + temp.phoneNumber);
                System.out.println("Address:" + temp.address);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR:" + ex.getMessage());
        }

        // Search for only particular people in phone book

        Person p = pbi.findPerson("Cynthia", "Smith");

        System.out.println("Name:" + p.name);
        System.out.println("Phone Number:" + p.phoneNumber);
        System.out.println("Address:" + p.address);

    }
}
