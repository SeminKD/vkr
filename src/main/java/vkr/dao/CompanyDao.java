package vkr.dao;

import vkr.model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class    CompanyDao {

    public static String getTextCompany() {
        String text;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select information_company from company");
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            text = resultSet.getString(1);
        } catch (SQLException e) {
            DataAccess.writeError(e);
            return null;
        }
        return text;
    }

    public static Company getCompany(){
        Company company;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select id_company, name_company, information_company from company");
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            company = new Company(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        } catch (SQLException e) {
            DataAccess.writeError(e);
            return null;
        }
        return company;
    }

    public static void editCompany(String name, String text){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update company set name_company=?, information_company=?");
            ps.setString(1,name);
            ps.setString(2,text);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }
}
