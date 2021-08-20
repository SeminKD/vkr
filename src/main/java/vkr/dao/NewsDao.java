package vkr.dao;

import vkr.model.Article;
import vkr.model.dto.Date;
import vkr.model.dto.NewsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsDao {

    public static ArrayList<NewsDto> getNewsByYear(String currentYear){
        ArrayList<NewsDto> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_article, name_article, " +
                    "preview_article, text_article, date_article from article " +
                    "where to_char(date_article, 'YYYY') = ? order by date_article desc;");
            ps.setString(1,currentYear);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                NewsDto article = new NewsDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
                list.add(article);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<Date> getMonthsByYear(String year){
        ArrayList<Date> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select distinct to_char(date_article, 'MM'), to_char(date_article, 'TMMonth') from article \n" +
                    "where to_char(date_article, 'YYYY')=? order by to_char(date_article, 'MM');");
            ps.setString(1,year);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                Date month = new Date(
                        resultSet.getString(1),
                        resultSet.getString(2)
                );
                list.add(month);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<String > getYears(){
        ArrayList<String> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select distinct to_char(date_article, 'YYYY') from article order by to_char(date_article, 'YYYY') asc;");
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

    public static ArrayList<NewsDto> getNewsByYearMonth(String currentYear, String month){
        ArrayList<NewsDto> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_article, name_article, " +
                    "preview_article, text_article, date_article from article " +
                    "where to_char(date_article, 'YYYY') = ? and to_char(date_article, 'MM')=? order by date_article desc;");
            ps.setString(1,currentYear);
            ps.setString(2, month);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                NewsDto article = new NewsDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
                list.add(article);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static ArrayList<NewsDto> getAllNews(){
        ArrayList<NewsDto> list = new ArrayList<>();
        try(Connection connection = DataAccess. getNewConnection()){
            PreparedStatement ps = connection.prepareStatement("select id_article, name_article, preview_article, text_article, date_article " +
                    "from article order by date_article desc");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                NewsDto article = new NewsDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5)
                );
                list.add(article);
            }
        }
        catch (SQLException e){
            DataAccess.writeError(e);
            return null;
        }
        return list;
    }

    public static void deleteArticle(int id){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from article where id_article=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static NewsDto getEditingArticle(int id){
        NewsDto article = null;
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("select id_article, name_article, preview_article, text_article, date_article" +
                    " from article where id_article=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            article = new NewsDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5)
            );
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
        return article;
    }

    public static void updateArticle(int id, String name, String prev, String text, java.sql.Date date){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("update article set name_article=?," +
                    "preview_article=?, text_article=?, date_article=? where id_article=?");
            ps.setString(1,name);
            ps.setString(2,prev);
            ps.setString(3,text);
            ps.setDate(4, date);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }

    public static void addArticle(String name, String prev, String text, java.sql.Date date){
        try (Connection connection = DataAccess.getNewConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into article values(nextval('id_article_seq')," +
                    "?,?,?,1,?)");
            ps.setString(1,name);
            ps.setString(2,prev);
            ps.setString(3,text);
            ps.setDate(4, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            DataAccess.writeError(e);
        }
    }
}
