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

    /**
     * add the destination to the list and displays it in the table
     * @param dest 
     */
    public void addDestination(Destination dest) {
        destinations.add(dest);
        fireTableRowsInserted(destinations.size() - 1, destinations.size() - 1);
    }

    /**
     * delets the selected destination from the list
     * @param row 
     */
    public void deleteDestination(int row) {
        destinations.remove(row);
        fireTableRowsDeleted(row, row);
    }

    /**
     * returns all destinations
     * @return 
     */
    public ArrayList getDestinations() {
        return destinations;
    }

    /**
     * returns a specific destination
     * @param idx
     * @return 
     */
    public Destination getDestination(int idx) {
        return destinations.get(idx);
    }

    /**
     * loads the destinations based from a xml file
     * @param dests 
     */
    public void loadDestinations(ArrayList<Destination> dests) {
        destinations.addAll(dests);
        fireTableRowsInserted(0, destinations.size() - 1);
    }
}
