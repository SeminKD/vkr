package vkr.service;

import org.springframework.ui.Model;
import vkr.dao.FuelDao;
import vkr.dao.MapDao;
import vkr.dao.NewsDao;
import vkr.model.Station;
import vkr.model.dto.Date;
import vkr.model.dto.FuelNamePriceDto;
import vkr.model.dto.NewsDto;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Util {

    private static final String[] monthNames = {"январь", "февраль", "март", "апрель", "май",
            "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    public static void whichNews(Model model, String year, String month) {
        ArrayList<FuelNamePriceDto> fuel = FuelDao.getFuelPrices();
        model.addAttribute("fuel", fuel);
        ArrayList<String> years = NewsDao.getYears();
        model.addAttribute("yearss", years);
        if (month.equals("null")) {
            ArrayList<NewsDto> news = NewsDao.getNewsByYear(year);
            model.addAttribute("news", news);
            model.addAttribute("currentYear", year);
            ArrayList<Date> months = NewsDao.getMonthsByYear(year);
            model.addAttribute("monthss", months);
        }
        if (!month.equals("null")) {
            ArrayList<NewsDto> news = NewsDao.getNewsByYearMonth(year, month);
            model.addAttribute("news", news);
            model.addAttribute("currentYear", year);
            String curMonth = monthNames[Integer.parseInt(month) - 1].concat(" ");
            model.addAttribute("currentMonth", curMonth);
            ArrayList<Date> months = NewsDao.getMonthsByYear(year);
            model.addAttribute("monthss", months);
        }
    }

    public static String whichStation(String fuel, String service, String product) {
        String query;
        if (fuel.equals("all") && service.equals("all") && product.equals("all")) {
            query = "select address_station from station order by id_station";
        } else {
            query = "select distinct s.address_station from station as s, station_fuel as sf, fuel as f, \n" +
                    "service as ser, station_service as ss, station_product as sp, \n" +
                    " product as p where ";

            boolean flag = false;
            if (!fuel.equals("all")) {
                String[] array = fuel.split(",");
                for (String s : array) {
                    if (flag) {
                        query = query.concat(" and ");
                    }
                    flag = true;
                    query = query.concat("s.id_station in (select distinct s.id_station from station as s, station_fuel as sf, fuel as f\n" +
                            " where s.id_station=sf.id_station and sf.id_fuel=f.id_fuel and f.name_fuel=" + s + ")");
                }
            }
            if (!service.equals("all")) {
                String[] array = service.split(",");
                for (String s : array) {
                    if (flag) {
                        query = query.concat(" and ");
                    }
                    flag = true;
                    query = query.concat("s.id_station in (select distinct s.id_station from station as s, station_service as ss, service as ser \n" +
                            " where s.id_station=ss.id_station and ss.id_service=ser.id_service and ser.name_service=" + s + ")");
                }
            }
            if (!product.equals("all")) {
                String[] array = product.split(",");
                for (String s : array) {
                    if (flag) {
                        query = query.concat(" and ");
                    }
                    flag = true;
                    query = query.concat("s.id_station in (select distinct s.id_station from station as s, station_product as sp, product as p \n" +
                            " where s.id_station=sp.id_station and sp.id_product=p.id_product and p.name_product=" + s + ")");
                }
            }
        }
        return query;
    }

    public static java.sql.Date getDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        java.util.Date docDate = null;
        try {
            docDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(docDate.getTime());
        return sqlDate;
    }

    public static ArrayList<String> getFiles(String path, String progPath,String extend) {
        ArrayList<String> list = new ArrayList<>();
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String result = (progPath + file.getName());
                if (!result.equals(extend))
                    list.add(result);
            }
        }
        return list;
    }
}
