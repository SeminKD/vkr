package vkr.dao;

import vkr.model.Fuel;
import vkr.model.dto.FuelNamePriceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuelDao {

    public static ArrayList<FuelNamePriceDto> getFuelPrices(){
        ArrayList<FuelNamePriceDto> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select name_fuel, price_fuel from fuel order by id_fuel");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                FuelNamePriceDto book = new FuelNamePriceDto(
                        resultSet.getString(1),
                        resultSet.getString(2));
                list.add(book);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Fuel> getSpecialFuel(){
        ArrayList<Fuel> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_fuel, name_fuel, description_fuel ,price_fuel, img_fuel from fuel where description_fuel is not null order by id_fuel");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Fuel fuel = new Fuel();
                fuel.setId_fuel(resultSet.getInt(1));
                fuel.setName_fuel(resultSet.getString(2));
                fuel.setDescription_fuel(resultSet.getString(3));
                String price = resultSet.getString(4).concat(" руб.");
                fuel.setPrice_fuel(price);
                fuel.setImg_fuel(resultSet.getString(5));
                list.add(fuel);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Fuel> getFuel(){
        ArrayList<Fuel> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_fuel, name_fuel, description_fuel, price_fuel, img_fuel from fuel order by id_fuel");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Fuel fuel = new Fuel();
                fuel.setId_fuel(resultSet.getInt(1));
                fuel.setName_fuel(resultSet.getString(2));
                fuel.setDescription_fuel(resultSet.getString(3));
                fuel.setPrice_fuel(resultSet.getString(4));
                fuel.setImg_fuel(resultSet.getString(5));
                list.add(fuel);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static void deleteFuel(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from station_fuel where id_fuel = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps = connection.prepareStatement("delete from fuel where id_fuel=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static Fuel getEditingFuel(int id){
        Fuel fuel = null;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select id_fuel, name_fuel, description_fuel, price_fuel, img_fuel from fuel where id_fuel=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            fuel = new Fuel();
            fuel.setId_fuel(resultSet.getInt(1));
            fuel.setName_fuel(resultSet.getString(2));
            fuel.setDescription_fuel(resultSet.getString(3));
            fuel.setPrice_fuel(resultSet.getString(4));
            fuel.setImg_fuel(resultSet.getString(5));
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return fuel;
    }

    public static void updateFuel(int id, String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update fuel set name_fuel=?," +
                    "description_fuel=?, price_fuel=?, img_fuel=? where id_fuel=?");
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

    public static void addFuel(String name, String desc, Double price, String img){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into fuel values(" +
                    "nextval('id_fuel_seq'), ?, ?, ?, ?)");
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
