package GUI;

import WeatherAPI1Day.Forecast;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ForecastCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setText(value.toString());
        Forecast f = (Forecast) value;
        Icon icon = new ImageIcon(getScaledImage(f.getIcon(),30 ,30));
        switch (column) {
            case 0:
                label.setText(f.getDestination());
                break;
            case 1:
                label.setIcon(icon);
                break;
            case 2:
                label.setText(String.format("%.2f °C", f.getTemp() - 273.15));
                break;
            case 3:
                label.setText(String.format("%d %%", f.getHumidity()));
                break;
            case 4:
                label.setText(String.format("%d", f.getPressure()));
                break;
            case 5:
                label.setText(String.format("%.2f km/h", f.getWindspeed()));
                break;
            case 6:
                label.setText(f.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm")));
                break;
        }

        return label;

    }

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}