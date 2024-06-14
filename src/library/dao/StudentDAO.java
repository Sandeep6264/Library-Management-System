/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dao;

/**
 *
 * @author HP
 */
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import library.dbutil.DBConnection;
import library.pojo.StudentPojo;
public class StudentDAO {
    public static int generateStudentId()throws SQLException{
        Connection con=DBConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select max(id) from library.student");
        rs.next();
        
        return rs.getInt(1)+1;
    }
    public static boolean addIssuedBook(StudentPojo s) throws SQLException{
     Connection con=DBConnection.getConnection();
     PreparedStatement ps=con.prepareStatement("Insert into library.student values(?,?,?,?,?,?)");
     ps.setInt(1, s.getId());
     ps.setString(2,s.getBookCallNo());
     ps.setInt(3,s.getStudentId());
     ps.setString(4,s.getStudentName());
     ps.setString(5,s.getStudentContact());
     ps.setString(6,s.getIssuedDate());
     return ps.executeUpdate()==1;
    }
    public static ArrayList viewAllIssuedBooks()throws SQLException{
        Connection con=DBConnection.getConnection();
      Statement st=con.createStatement();
      ResultSet rs=st.executeQuery("select * from library.student");
      ArrayList <StudentPojo> al = new ArrayList<StudentPojo>();
      StudentPojo ls;
      while(rs.next()){  
      ls= new StudentPojo();
      ls.setId(rs.getInt(1));
      ls.setBookCallNo(rs.getString(2));
      ls.setStudentId(rs.getInt(3)); 
      ls.setStudentName(rs.getString(4)); 
      ls.setStudentContact(rs.getString(5));
      ls.setIssuedDate(rs.getString(6));
      al.add(ls);
       }   
        return al; 
    }
    public static int checkValidation(int id,String bookCallNo)throws SQLException{ 
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from library.student where studentId=?");
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            String str=rs.getString(2);
            if(str.equals(bookCallNo))
                return 1;
            else
                return 0;    
        }
        return -1;
    }
    public static boolean returnBook(int id)throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("Delete from library.student where studentId=?");
        ps.setInt(1, id);
        return ps.executeUpdate()==1;
    }
    public static boolean checkAvalibility()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select count(id) from library.student");
        rs.next();
        int count=rs.getInt(1);
           if(count==0)
            return true;
        return false;
    }
}
