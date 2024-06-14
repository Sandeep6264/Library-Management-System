/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dbutil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author HP
 */
public class DBConnection {
    private static Connection con;
    static{
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Sandeep@123");
           JOptionPane.showMessageDialog(null,"Connect open successfully","Success",JOptionPane.INFORMATION_MESSAGE);
       }
     catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error in loading the driver","Driver Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
        catch(SQLException 
                e){
             JOptionPane.showMessageDialog(null,"Error in Opening Connection","DB Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection  getConnection(){
           return con;
    }
    public static void closeConnection(){
         try{
              con.close();
         }
         catch(SQLException e){
             JOptionPane.showMessageDialog(null,"Error in Closing Connection");
             e.printStackTrace();
    }
    }
}
