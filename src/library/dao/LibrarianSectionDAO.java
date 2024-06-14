/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dao;

import library.pojo.LibrarianSectionPojo;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import library.dbutil.DBConnection;
/**
 *
 * @author HP
 */
public class LibrarianSectionDAO {
    public static boolean CheckAvalibility(LibrarianSectionPojo ls)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from library.librariansection where callno=?");
        ps.setString(1, ls.getCallNo());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
           PreparedStatement ps1=conn.prepareStatement("update  library.librariansection set quantity=? ,added_date=?  where callno=?");
            Date d=new Date();
            String str=d.toLocaleString();
            System.out.println(str);
            System.out.println( rs.getInt(6)+ls.getQuantity());
            ps1.setInt(1, rs.getInt(6)+ls.getQuantity());
            ps1.setString(2,str);
            ps1.setString(3, ls.getCallNo());
            return ps1.executeUpdate()==1;
        }
        else
        {  
         Statement st=conn.createStatement();
         ResultSet rs1=st.executeQuery("select max(id)from library.librariansection");
         rs1.next();
         int id=rs1.getInt(1);
             PreparedStatement ps1=conn.prepareStatement("Insert into library.librariansection values(?,?,?,?,?,?,?,?)");
             ps1.setInt(1, id+1);
             ps1.setString(2, ls.getCallNo());
             ps1.setString(3, ls.getName());
             ps1.setString(4, ls.getAuthor());
             ps1.setString(5,ls.getPublisher());
             ps1.setInt(6, ls.getQuantity());
             ps1.setInt(7,0);
             Date d=new Date();
             String str=d.toLocaleString();
             ps1.setString(8,str);
            return ps1.executeUpdate()==1;
        }
      
    }
 public static ArrayList viewAllBook()throws SQLException{
     Connection con=DBConnection.getConnection();
      Statement st=con.createStatement();
      ResultSet rs=st.executeQuery("select * from library.librariansection");
      ArrayList <LibrarianSectionPojo> al = new ArrayList<LibrarianSectionPojo>();
      LibrarianSectionPojo ls;
      while(rs.next()){  
      ls= new LibrarianSectionPojo();
      ls.setId(rs.getInt(1));
      ls.setCallNo(rs.getString(2));
      ls.setName(rs.getString(3)); 
      ls.setAuthor(rs.getString(4)); 
      ls.setPublisher(rs.getString(5));
      ls.setQuantity(rs.getInt(6));
      ls.setIssued(rs.getInt(7));
      ls.setAddedDate(rs.getString(8));
      al.add(ls);
       }   
        return al;    
       }
      public static int IssuedBook(String str)throws SQLException{
          Connection con=DBConnection.getConnection();
          PreparedStatement p=con.prepareStatement("select * from library.librariansection where callno=? ");
          p.setString(1, str);
          ResultSet rs=p.executeQuery();
          rs.next();
          int q=rs.getInt(6);
          if(q>0){
          PreparedStatement ps=con.prepareStatement("update  library.librariansection set quantity=quantity-1,issued=issued+1 where callno=?");
          ps.setString(1, str);
          if(ps.executeUpdate()==1)
              return 1;
          else 
              return -1;
          }
          return 0;
      }
      public static boolean returnBook(String str)throws SQLException{
          Connection con=DBConnection.getConnection();
          PreparedStatement ps=con.prepareStatement("update  library.librariansection set quantity=quantity+1,issued=issued-1 where callno=?");
          ps.setString(1, str);
          return ps.executeUpdate()==1;
      }
}
