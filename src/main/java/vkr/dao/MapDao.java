package vkr.dao;

import vkr.model.Fuel;
import vkr.model.Product;
import vkr.model.Service;
import vkr.model.Station;
import vkr.model.dto.FPS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MapDao {

    public static ArrayList<String> getStations(String query){
        ArrayList<String> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString(1));
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Fuel> getAllFuel(){
        ArrayList<Fuel> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_fuel, name_fuel from fuel order by id_fuel");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Fuel fuel = new Fuel();
                fuel.setId_fuel(resultSet.getInt(1));
                fuel.setName_fuel(resultSet.getString(2));
                list.add(fuel);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Product> getAllProduct(){
        ArrayList<Product> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_product, name_product from product order by id_product");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Product product = new Product();
                product.setId_product(resultSet.getInt(1));
                product.setName_product(resultSet.getString(2));
                list.add(product);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Service> getAllService(){
        ArrayList<Service> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_service, name_service from service order by id_service");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Service service = new Service();
                service.setId_service(resultSet.getInt(1));
                service.setName_service(resultSet.getString(2));
                list.add(service);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Station> getAllStations(){
        ArrayList<Station> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_station, address_station from station order by id_station");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Station station = new Station();
                station.setId_station(resultSet.getInt(1));
                station.setAddress_station(resultSet.getString(2));
                list.add(station);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static Station getEditingStation(int id){
        Station station = null;
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_station, address_station from station where id_station=?");
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
                station = new Station();
                station.setId_station(resultSet.getInt(1));
                station.setAddress_station(resultSet.getString(2));
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return station;
    }

    public static void updateStation(int id, String address){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update station set address_station=? where id_station=?");
            ps.setString(1, address);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void addStation(String address){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into station values(nextval('id_station_seq'), 1, ?)");
            ps.setString(1, address);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void deleteStation(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_fuel where id_station = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from station_service where id_station = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from station_product where id_station = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from station where id_station=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static ArrayList<FPS> getFPSFuel(int id){
        ArrayList<FPS> list = new ArrayList<>();
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select f.id_fuel, f.name_fuel " +
                    "from fuel as f, station_fuel as sf where sf.id_station=? and sf.id_fuel=f.id_fuel");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                FPS fuel = new FPS();
                fuel.setId(resultSet.getInt(1));
                fuel.setName(resultSet.getString(2));
                list.add(fuel);
            }
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return list;
    }

    public static ArrayList<FPS> getFPSProduct(int id){
        ArrayList<FPS> list = new ArrayList<>();
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select f.id_product, f.name_product " +
                    "from product as f, station_product as sf where sf.id_station=? and sf.id_product=f.id_product");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                FPS product = new FPS();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                list.add(product);
            }
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return list;
    }

    public static ArrayList<FPS> getFPSService(int id){
        ArrayList<FPS> list = new ArrayList<>();
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select f.id_service, f.name_service " +
                    "from service as f, station_service as sf where sf.id_station=? and sf.id_service=f.id_service");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                FPS service = new FPS();
                service.setId(resultSet.getInt(1));
                service.setName(resultSet.getString(2));
                list.add(service);
            }
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return list;
    }

    public static void deleteStationFuel(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_fuel where id_station=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void deleteStationService(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_service where id_station=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void deleteStationProduct(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_product where id_station=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void updateFPSFuel(int id, String fuel){
        if(!fuel.equals("all")){
            String[] array = fuel.split(",");
            for (String s : array) {
                String query = "insert into station_fuel values( (select id_fuel from fuel where name_fuel=" + s + ")," + id+")";
                try (Connection connection = DataAccess.getNewConnection()) {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    DataAccess.writeError(e);
                }
            }
        }
    }

    public static void updateFPSService(int id, String service){
        if(!service.equals("all")){
            String[] array = service.split(",");
            for (String s : array) {
                String query = "insert into station_service values( (select id_service from service where name_service=" + s + ")," + id+")";
                try (Connection connection = DataAccess.getNewConnection()) {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    DataAccess.writeError(e);
                }
            }
        }
    }

    public static void updateFPSProduct(int id, String product){
        if(!product.equals("all")){
            String[] array = product.split(",");
            for (String s : array) {
                String query = "insert into station_product values( (select id_product from product where name_product=" + s + ")," + id+")";
                try (Connection connection = DataAccess.getNewConnection()) {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    DataAccess.writeError(e);
                }
            }
        }
    }
}
