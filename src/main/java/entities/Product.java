package entities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static DB.DBConnection.con;
public class Product {
    private String prodName;
    private double prodPrice;
    private int quantity;
    private int cep;
    public String getProdName() {
        return prodName;
    }
    public double getProdPrice() {
        return prodPrice;
    }
    public int getQuantity() {
        return quantity;
    }

    public Product(String prodName, double prodPrice, int quantity, int cep) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.quantity = quantity;
        this.cep = cep;
    }

    public static void consultProducts() throws SQLException {
        String product = "SELECT PRODUCT, PRICE FROM productsList;" ;
        Statement st = con.createStatement();
        ResultSet rst;

        try{
            rst = st.executeQuery(product);
            while(rst.next()){
                System.out.println(rst.getString("PRODUCT")  +
                        " | Preço: " + rst.getDouble("PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
    public static double getProdPrice(String prodName) throws SQLException {
        String getProdPriceSet = "SELECT PRICE from productsList WHERE PRODUCT= "+ "'" + prodName+"';";
        Statement st = con.createStatement();
        ResultSet rst;
        try{
            rst = st.executeQuery(getProdPriceSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rst.next()){
            return rst.getInt("PRICE");
        }else return 0;
    }
    public static int getProdCode(String prodName) throws SQLException {
        String getProdPriceSet = "SELECT CODE from productsList WHERE PRODUCT= " + "'" + prodName+"';";
        Statement st = con.createStatement();
        ResultSet rst;
        try{
            rst = st.executeQuery(getProdPriceSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rst.next()){
            return rst.getInt("CODE");
        }else return 0;
    }
    //sends the products data to database
    public static void sendProd(int prodCode, double prodPrice, int quantity, int zipCode){
        String setCommand = "INSERT INTO productsToShip VALUES (?,?,?,?)";
        try{
            PreparedStatement pst = con.prepareStatement(setCommand);
            pst.setInt(1, prodCode);
            pst.setDouble(2,prodPrice);
            pst.setInt(3,quantity);
            pst.setInt(4,zipCode);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static double frete(double sum) {
        if (sum > 1000) {
            return sum * 0.07;
        } else return sum * 0.1;
    }
}
