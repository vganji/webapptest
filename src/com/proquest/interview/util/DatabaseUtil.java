package com.proquest.interview.util;

import com.proquest.interview.phonebook.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is just a utility class, you should not have to change anything
 * here
 *
 * @author rconklin
 */
public class DatabaseUtil {

    public static void initDB() {
        try {
            Connection cn = getConnection();
            Statement stmt = cn.createStatement();
            stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
            stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
            stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
            cn.commit();
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");
        return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
    }

    public static void addPerson(Person p) throws SQLException, ClassNotFoundException {
        
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('" + p.name + "','" + p.phoneNumber + "', '" + p.address + "')");
       
    }
    
     public static Person findPerson(String firstname , String lastname ) throws SQLException, ClassNotFoundException {
        
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from PHONEBOOK where NAME='"+firstname+" "+lastname+"'");
            Person p = new Person();
            
            while ( rs.next() ){
                p.address = rs.getString("ADDRESS");
                p.name = rs.getString("NAME");
                p.phoneNumber = rs.getString("PHONENUMBER");
                
            }
            
            return p;
    }
     
     public static List<Person> lookupPerson( ) throws SQLException, ClassNotFoundException {
        
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from PHONEBOOK");
            List<Person> p = new ArrayList<Person>();
            
            while ( rs.next() ){
                
                Person temp = new Person();
                temp.address = rs.getString("ADDRESS");
                temp.name = rs.getString("NAME");
                temp.phoneNumber = rs.getString("PHONENUMBER");
                p.add(temp);
            }
            
            return p;
    }
     
     
}
