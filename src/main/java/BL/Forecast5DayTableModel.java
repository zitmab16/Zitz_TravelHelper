/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import WeatherAPI5Day.Forecast5Days;
import WeatherAPI5Day.List;
import WeatherAPI5Day.OpenWeatherResponse;
import WeatherAPI5Day.Weather;
import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.AbstractTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vizug
 */
public class Forecast5DayTableModel extends AbstractTableModel {

    private ArrayList<Forecast5Days> forecasts = new ArrayList();
    private ArrayList<Forecast5Days> forecastsFiltered = new ArrayList();
    private static final String[] COLNAMES = {"Destination", "Weather", "Temperature", "Humidity", "Pressure", "Windspeed", "Time"};
    private static final String URI = "http://api.openweathermap.org/data/2.5/";
    private static final String PATH = "forecast";
    private static final String APPID = "b237d7a369f91268b22791af114ca846";
    private static String URI_ICON = "http://openweathermap.org/img/wn/";
    private static String ICON_END = "@2x.png";

    @Override
    public int getRowCount() {
        return forecastsFiltered.size();
    }

    @Override
    public int getColumnCount() {
        return COLNAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLNAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Forecast5Days f = forecastsFiltered.get(rowIndex);
        return f;
    }

    /**
     * adds a five day weather forecast to the list and displays it
     * @param f 
     */
    public void addForecast(Forecast5Days f) {
        forecasts.add(f);
        forecastsFiltered.add(f);
        fireTableRowsInserted(forecastsFiltered.size() - 1, forecastsFiltered.size() - 1);
    }

    /**
     * this method will get the data from the OpenWeatherAPI and build a five day weather forecast object
     * @param destinations 
     */
    public void show5DayForeCasts(ArrayList<Destination> destinations) {
        forecasts.clear();
        forecastsFiltered.clear();
        for (Destination d : destinations) {
            Client c = ClientBuilder.newClient();
            Response r = c.target(URI)
                    .path(PATH)
                    .queryParam("appid", APPID)
                    .queryParam("zip", d.getZipCode())
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            String jsonString = r.readEntity(String.class);
            OpenWeatherResponse owr = new Gson().fromJson(jsonString, OpenWeatherResponse.class);

            java.util.List<WeatherAPI5Day.List> data = owr.getList();

            for (List response : data) {
                String time = response.getDt_txt().split(" ")[1];
                if (time.equals("15:00:00")) {
                    for (Weather w : response.getWeather()) {
                        Image icon = getWeatherIcon(w.getIcon());
                        LocalDateTime ldt = LocalDateTime.parse(response.getDt_txt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        Forecast5Days f = new Forecast5Days(d.getDestname(), icon, response.getMain().getTemp(), response.getMain().getHumidity(),
                                response.getMain().getPressure(), response.getWind().getSpeed(), ldt);
                        addForecast(f);
                    }
                }
            }
        }

    }

    /**
     * This method will get the image with the id for the weather object above
     * @param id
     * @return 
     */
    public Image getWeatherIcon(String id) {
        Image image = null;
        try {
            URL url = new URL(URI_ICON + id + ICON_END);
            image = ImageIO.read(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(OpenWeatherResponse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OpenWeatherResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    /**
     * This methode will filter the list after a specific day the user has selected in the GUI
     * @param d 
     */
    public void showProposedDay(String d) {
        forecastsFiltered.clear();
        for (Forecast5Days forecast : forecasts) {
            if (forecast.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(d)) {
                forecastsFiltered.add(forecast);
                
            }
        }
        fireTableDataChanged();

    }
    /**
     * sort the list by temperature
     */
    public void sortByTemp(){
     Collections.sort(forecastsFiltered, new SortByTemperature());
     fireTableDataChanged();
    }
    /**
     * sort the list by temperature
     */
    public void sortByHum(){
     Collections.sort(forecastsFiltered, new SortByHumidity());
     fireTableDataChanged();
    }
    /**
     * sort the list by temperature
     */
    public void sortByPressure(){
     Collections.sort(forecastsFiltered, new SortByPressure());
     fireTableDataChanged();
    }
}
