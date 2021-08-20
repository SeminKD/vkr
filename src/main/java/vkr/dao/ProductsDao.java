package vkr.dao;

import vkr.model.Fuel;
import vkr.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsDao {

    public static ArrayList<Product> getProductsWithRub(){
        ArrayList<Product> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_product, name_product," +
                    " description_product ,price_product, img_product from product order by id_product");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId_product(resultSet.getInt(1));
                product.setName_product(resultSet.getString(2));
                product.setDescription_product(resultSet.getString(3));
                String price = resultSet.getString(4).concat(" руб.");
                product.setPrice_product(price);
                product.setImg_product(resultSet.getString(5));
                list.add(product);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Product> getProducts(){
        ArrayList<Product> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_product, name_product," +
                    " description_product ,price_product, img_product from product order by id_product");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId_product(resultSet.getInt(1));
                product.setName_product(resultSet.getString(2));
                product.setDescription_product(resultSet.getString(3));
                String price = resultSet.getString(4);
                product.setPrice_product(price);
                product.setImg_product(resultSet.getString(5));
                list.add(product);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static void deleteProduct(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_product where id_product = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from product where id_product=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static Product getEditingProduct(int id){
        Product product = null;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select id_product, name_product, description_product, price_product, img_product from product where id_product=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            product = new Product();
            product.setId_product(resultSet.getInt(1));
            product.setName_product(resultSet.getString(2));
            product.setDescription_product(resultSet.getString(3));
            product.setPrice_product(resultSet.getString(4));
            product.setImg_product(resultSet.getString(5));
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return product;
    }

    public static void updateProduct(int id, String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update product set name_product=?," +
                    "description_product=?, price_product=?, img_product=? where id_product=?");
            ps.setString(1,name);
            ps.setString(2,desc);
            ps.setDouble(3,price);
            ps.setString(4, img);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void addProduct(String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into product values(" +
                    "nextval('id_product_seq'), ?, ?, ?, ?)");
            ps.setString(1,name);
            ps.setString(2,desc);
            ps.setDouble(3,price);
            ps.setString(4, img);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }
}
