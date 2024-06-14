/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dao;
import java.sql.SQLException;
import java.sql.Connection;
import library.dbutil.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import library.pojo.AdminPojo;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class AdminDAO {
   public static int genarateId()throws SQLException{
       Connection con=DBConnection.getConnection();
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("select max(id) from library.admin");  
       rs.next();
       return rs.getInt(1)+1;
   }
    public static boolean addLibrarian(AdminPojo p) throws SQLException {
          Connection con=DBConnection.getConnection();
          PreparedStatement ps=con.prepareStatement("Insert into library.admin values(?,?,?,?,?,?,?,?)");
          ps.setInt(1, p.getId());
          ps.setString(2, p.getName());
          ps.setString(3,p.getPassword());
          ps.setString(4,p.getEmail());
          ps.setString(5,p.getAddress());
          ps.setString(6,p.getCity());
          ps.setInt(7, Integer.parseInt( p.getContact()));
          ps.setString(8,p.getFilds());
          return ps.executeUpdate()==1;   
    }
    public static ArrayList<AdminPojo> viewLabrarian()throws SQLException{
         Connection con=DBConnection.getConnection();
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("select * from library.admin where filed='Labrarian'");
        ArrayList<AdminPojo> ls=new ArrayList<AdminPojo>();
        while(rs.next()){
            AdminPojo a=new AdminPojo();
            a.setId(Integer.parseInt(rs.getString(1)));
            a.setName(rs.getString(2));
              a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4)); 
                a.setAddress(rs.getString(5));
                a.setCity(rs.getString(6));
                a.setContact(rs.getString(7));
                ls.add(a);
        }
        return ls;
    }
//    public static ArrayList loadAllId()throws SQLException{
//           Connection con=DBConnection.getConnection();
//           Statement st=con.createStatement();
//           ResultSet rs=st.executeQuery("Select id from from library.admin where filed='Labrarian' ");
//           ArrayList <int>al;
//       al = new ArrayList<int>();
//           while(rs.next()){
//               al.add(rs.getInt(1));
//           }
//           return al;
//           
//       
//    }
    public static boolean deleteAdmin(int num) throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("delete from library.admin where id=? and filed='admin'");
        ps.setInt(1, num);
        return ps.executeUpdate()==1;
        
      
    }
}
