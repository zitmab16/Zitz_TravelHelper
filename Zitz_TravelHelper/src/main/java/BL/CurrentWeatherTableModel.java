package BL;

import WeatherAPI.Forecast;
import WeatherAPI.OpenWeatherResponse;
import WeatherAPI.Weather;
import com.google.gson.Gson;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.table.AbstractTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CurrentWeatherTableModel extends AbstractTableModel {

    private ArrayList<Forecast> forecasts = new ArrayList();
    private static final String[] COLNAMES = {"Destination", "Weather", "Temperature", "Humidity", "Pressure", "Windspeed", "Time"};
    private static final String URI = "http://api.openweathermap.org/data/2.5/";
    private static final String PATH = "weather";
    private static final String APPID = "b237d7a369f91268b22791af114ca846";
    private static String URI_ICON = "http://openweathermap.org/img/wn/";
    private static String ICON_END = "@2x.png";

    @Override
    public int getRowCount() {
        return forecasts.size();
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
        Forecast f = forecasts.get(rowIndex);
        return f;
    }

    public void addForecast(Forecast f) {
        forecasts.add(f);
        fireTableRowsInserted(forecasts.size() - 1, forecasts.size() - 1);
    }

    public void showForeCasts(ArrayList<Destination> destinations) {

        Client c = ClientBuilder.newClient();
        for (Destination destination : destinations) {
            Response r = c.target(URI)
                    .path(PATH)
                    .queryParam("appid", APPID)
                    .queryParam("zip", destination.getZipCode())
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            String jsonString = r.readEntity(String.class);
            OpenWeatherResponse owr = new Gson().fromJson(jsonString, OpenWeatherResponse.class);
            for (Weather w : owr.getWeather()) {
                Image icon = getWeatherIcon(w.getIcon());
                Forecast f = new Forecast(destination.getDestname(), icon, owr.getMain().getTemp(), owr.getMain().getHumidity(),
                        owr.getMain().getPressure(), owr.getWind().getSpeed(), LocalDateTime.now());
                addForecast(f);
            }

        }
    }

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

}
