package vkr.dao;

import vkr.model.Product;
import vkr.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDao {

    public static ArrayList<Service> getServiceWithRub(){
        ArrayList<Service> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_service, name_service, description_service, " +
                    "price_service, img_service from service order by id_service");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Service service = new Service();
                service.setId_service(resultSet.getInt(1));
                service.setName_service(resultSet.getString(2));
                service.setDescription_service(resultSet.getString(3));
                String price = "Средняя цена: ".concat(resultSet.getString(4));
                price = price.concat(" руб.");
                service.setPrice_service(price);
                service.setImg_service(resultSet.getString(5));
                list.add(service);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Service> getService(){
        ArrayList<Service> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_service, name_service, description_service, " +
                    "price_service, img_service from service order by id_service");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Service service = new Service();
                service.setId_service(resultSet.getInt(1));
                service.setName_service(resultSet.getString(2));
                service.setDescription_service(resultSet.getString(3));
                service.setPrice_service(resultSet.getString(4));
                service.setImg_service(resultSet.getString(5));
                list.add(service);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static void deleteService(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_service where id_service = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from service where id_service=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static Service getEditingService(int id){
        Service service = null;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select id_service, name_service, description_service, price_service, img_service from service where id_service=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            service = new Service();
            service.setId_service(resultSet.getInt(1));
            service.setName_service(resultSet.getString(2));
            service.setDescription_service(resultSet.getString(3));
            service.setPrice_service(resultSet.getString(4));
            service.setImg_service(resultSet.getString(5));
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return service;
    }

    public static void updateService(int id, String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update service set name_service=?," +
                    "description_service=?, price_service=?, img_service=? where id_service=?");
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

    public static void addService(String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into service values(" +
                    "nextval('id_service_seq'), ?, ?, ?, ?)");
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
