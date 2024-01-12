package DB;
import java.sql.*;
public class DBConnection {
        private static final String url = "jdbc:mysql://localhost:3306/Products";
        private static final  String uname = "root";
        private static final String password="root1234";
        public static Connection con = null;
        public static Connection getConnection() throws SQLException {
            if(con==null){
                try{
                    con = DriverManager.getConnection(url, uname, password);
                }catch(SQLException e){
                    throw new SQLException(e.getMessage());
                }}
            return con;
        }
        public static void closeConnection() throws SQLException {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new SQLException(e);
                }
            }
        }}