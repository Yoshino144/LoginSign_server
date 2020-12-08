package dao;

import org.json.JSONObject;
import utils.GetUUID;

import java.sql.*;

public class UserDao {

    JSONObject jsonObj = new JSONObject();
    public String queryUserByUserName(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kssk?&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "pc654789");
        String sql = "select * from user where username='"+username+"'";
        Statement stmt=connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            jsonObj.put("userid", rs.getString("userid"));
            jsonObj.put("username", rs.getString("username"));
            jsonObj.put("password", rs.getString("password"));
            return rs.getString("password");
        }
        connection.close();
        return null;
    }
    public String getJson(){
        return jsonObj.toString();
    }

    public int addNewUser(String uuid,String username,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kssk?&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "pc654789");
        while(checkUUID(uuid)){
            uuid = GetUUID.get();
        }
        String sql = "insert into user (userid, username,password) values ('"+uuid+"','"+username+"','"+password+"') ";
        Statement stmt=connection.createStatement();
        int rs;
        try{
            rs = stmt.executeUpdate(sql);
            System.out.println("UserDao_AddNew==="+rs);
        }catch (SQLIntegrityConstraintViolationException e){
            return 0;
        }
        connection.close();
        return rs;
    }

    public boolean checkUUID(String uuid) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kssk?&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "pc654789");
        String sql = "select * from user where userid='"+uuid+"'";
        Statement stmt=connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            if(rs.getString("userid").equals(uuid)){
                return true;
            }
        }
        connection.close();
        return false;
    }

    public String qq(String uuid,String username,String sex,String qqid,String year,String imgUrl) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kssk?&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "pc654789");
        String sql = "select * from user where qqid='"+qqid+"'";
        Statement stmt=connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        JSONObject jsonQQ = new JSONObject();
        if(rs.next()){
            if(rs.getString("qqid").equals(qqid)){
                jsonQQ.put("userid", rs.getString("userid"));
                jsonQQ.put("username", rs.getString("username"));
                jsonQQ.put("year", rs.getString("year"));
                jsonQQ.put("sex", rs.getString("sex"));
                jsonQQ.put("imgurl", rs.getString("imgurl"));
                return jsonQQ.toString();
            }
        }
        if(newQQ(uuid,username,sex,qqid,year,imgUrl)){
            jsonQQ.put("userid", uuid);
            jsonQQ.put("username", username);
            jsonQQ.put("year", year);
            jsonQQ.put("sex", sex);
            jsonQQ.put("imgurl", imgUrl);
            connection.close();
            return jsonQQ.toString();
        }else{
            connection.close();
            return "false";
        }
    }

    public boolean newQQ(String uuid,String username,String sex,String qqid,String year,String imgurl) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kssk?&useSSL=false&serverTimezone=UTC";
        Connection connection = DriverManager.getConnection(url, "root", "pc654789");
        String sql = "insert into user values ('"+uuid+"','"+username+"','"+"null"+"','"+qqid+"','"+year+"','"+sex+"','"+imgurl+"') ";
        Statement stmt=connection.createStatement();
        int rs;
        try{
            rs = stmt.executeUpdate(sql);
        }catch (SQLIntegrityConstraintViolationException e){
            return false;
        }
        connection.close();
        if (rs!=0){
            return true;
        }else {
            return false;
        }
    }
}
