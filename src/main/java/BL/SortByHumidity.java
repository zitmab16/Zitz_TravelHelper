/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import WeatherAPI5Day.Forecast5Days;
import java.util.Comparator;

/**
 *
 * @author vizug
 */
public class SortByHumidity implements Comparator<Forecast5Days>{
 
    @Override
    public int compare(Forecast5Days f1, Forecast5Days f2) {
        if(f1.getHumidity()<f2.getHumidity()){
            return 1;
        }
        else if (f1.getHumidity()>f2.getHumidity()){
            return -1;
        }
        return 0;
    }
}
