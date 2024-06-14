/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.dao;
import java.sql.Connection;
import library.dbutil.DBConnection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.sql.SQLException;
import library.pojo.UserLogin;
import java.sql.ResultSet;
/**
 *
 * @author HP
 */
public class LoginDAO {
    public static boolean loginFrame(UserLogin p) throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from library.admin where name = ? and password= ?  and filed='admin'");
        ps.setString(1,p.getName());
        ps.setString(2, p.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return true;
        return false;
    }
    public static boolean LibrarianLogin(UserLogin p) throws SQLException{
        Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement("select * from library.admin where name = ? and password= ?  and filed='Labrarian'");
        ps.setString(1,p.getName());
        ps.setString(2, p.getPassword());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return true;
        return false;
    }
    
}
//LibrarianLogin
//       ResultSet rs= ps.executeQuery();
//       HashMap<Boolean,String>m=new HashMap();
//       if(rs.next()){
//           String a=rs.getString(8);
//            System.out.println(a);
//            m.put(true, a);
//           }
//         return m;  