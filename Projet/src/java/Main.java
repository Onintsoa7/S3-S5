
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
         java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        System.out.println("Current Date: " + currentDate);

        // Converting java.sql.Date to java.time.LocalDate
        LocalDate localDate = currentDate.toLocalDate();

        // Adding 2 years to the date
        LocalDate newDate = localDate.plusYears(2);

        // Converting java.time.LocalDate back to java.sql.Date
        java.sql.Date newSqlDate = java.sql.Date.valueOf(newDate);
         System.out.println("Date after adding 2 years: " + newSqlDate);
    }
    
}
