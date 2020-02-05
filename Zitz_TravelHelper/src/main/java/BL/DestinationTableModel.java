package BL;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class DestinationTableModel extends AbstractTableModel {

    private ArrayList<Destination> destinations = new ArrayList();
    private static final String[] COLNAMES = {"Destination", "Zip-Code"};

    @Override
    public int getRowCount() {
        return destinations.size();
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
        Destination dest = destinations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dest.getDestname();
            case 1:
                return dest.getZipCode();
            default:
                return "??";
        }
    }

    public void addDestination(Destination dest) {
        destinations.add(dest);
        fireTableRowsInserted(destinations.size() - 1, destinations.size() - 1);
    }

    public void deleteDestination(int row) {
        destinations.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public ArrayList getDestinations() {
        return destinations;
    }

    public Destination getDestination(int idx) {
        return destinations.get(idx);
    }

    public void loadDestinations(ArrayList<Destination> dests) {
        destinations.addAll(dests);
        fireTableRowsInserted(0, destinations.size() - 1);
    }
}
