package org.example;

import DB.DBConnection;
import entities.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        DBConnection.getConnection();
        System.out.println("Produtos em estoque:");
        Product.consultProducts();
        System.out.println("Vamos consultar seu CEP");
        int cep = sc.nextInt();

        try {
            System.out.println("Quantos produtos diferentes você deseja comprar?");
            int loopSize = sc.nextInt();
            List<Product> productList = new ArrayList<>();

            //gets the price from database, the product and quantity and prints the information
            for (int i = 0; i < loopSize; i++) {
                System.out.println("Produto " + (i + 1));
                String prodName = sc.next();
                System.out.println("Quantidade: ");
                sc.nextLine();
                int quantity = sc.nextInt();
                double price = Product.getProdPrice(prodName);
                if (price > 0) {
                    productList.add(new Product(prodName, price, quantity,cep));
                } else System.out.println("Reinicie o programa e insira um valor válido!");
            }

            //to calculate the delivery fee and total price
            double total = 0.0;
            for (Product e : productList) {
                System.out.println(e.getProdName() + " - R$" + e.getProdPrice() + " X " + e.getQuantity());
                total = total + e.getProdPrice() * e.getQuantity();
                Product.sendProd(Product.getProdCode(e.getProdName()), e.getProdPrice(), e.getQuantity(), cep);
            }
            System.out.printf("Seu total é: R$%.2f%n + frete = R$%.2f%n ", total, Product.frete(total));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sc.close();
        DBConnection.closeConnection();
    }
}