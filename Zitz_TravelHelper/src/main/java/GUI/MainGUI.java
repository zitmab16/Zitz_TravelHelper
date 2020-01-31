/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Destination;
import BL.DestinationTableModel;

/**
 *
 * @author vizug
 */
public class MainGUI extends javax.swing.JFrame {

    DestinationTableModel dbm = new DestinationTableModel();
    ForeCastGUI fcgui= new ForeCastGUI();
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        tbWeatherData.setModel(dbm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbWeatherData = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        meEdit = new javax.swing.JMenu();
        miAddDest = new javax.swing.JMenuItem();
        miDelete = new javax.swing.JMenuItem();
        meForecasts = new javax.swing.JMenu();
        miShowForecasts = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbWeatherData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Destination", "Zip-Code"
            }
        ));
        jScrollPane1.setViewportView(tbWeatherData);

        meEdit.setText("Edit");
        meEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meEditActionPerformed(evt);
            }
        });

        miAddDest.setText("Add Destination");
        miAddDest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddDestActionPerformed(evt);
            }
        });
        meEdit.add(miAddDest);

        miDelete.setText("Delete Destination");
        miDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDeleteActionPerformed(evt);
            }
        });
        meEdit.add(miDelete);

        jMenuBar1.add(meEdit);

        meForecasts.setText("Forecasts");

        miShowForecasts.setText("show Forecast for all Destinations");
        miShowForecasts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miShowForecastsActionPerformed(evt);
            }
        });
        meForecasts.add(miShowForecasts);

        jMenuBar1.add(meForecasts);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meEditActionPerformed

    }//GEN-LAST:event_meEditActionPerformed

    private void miAddDestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddDestActionPerformed
        AddDialog dlg = new AddDialog(this, true);
        dlg.setVisible(true);
        if (dlg.isOk()) {
            Destination d = dlg.getDest();
            dbm.addDestination(d);
        }
    }//GEN-LAST:event_miAddDestActionPerformed

    private void miDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDeleteActionPerformed
       int rowIdx = tbWeatherData.getSelectedRow();
       dbm.deleteDestination(rowIdx);
    }//GEN-LAST:event_miDeleteActionPerformed

    private void miShowForecastsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miShowForecastsActionPerformed
       fcgui.setVisible(true);
       fcgui.showForecasts(dbm.getDestinations());
    }//GEN-LAST:event_miShowForecastsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu meEdit;
    private javax.swing.JMenu meForecasts;
    private javax.swing.JMenuItem miAddDest;
    private javax.swing.JMenuItem miDelete;
    private javax.swing.JMenuItem miShowForecasts;
    private javax.swing.JTable tbWeatherData;
    // End of variables declaration//GEN-END:variables
}
