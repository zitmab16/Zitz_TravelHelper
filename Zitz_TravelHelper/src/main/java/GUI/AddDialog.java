/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BL.Destination;

/**
 *
 * @author vizug
 */
public class AddDialog extends javax.swing.JDialog {

    private boolean ok;
    private Destination dest;
    /**
     * Creates new form AddDialog
     */
    public AddDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public Destination getDest() {
        return dest;
    }

    public boolean isOk() {
        return ok;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbZipCode = new javax.swing.JLabel();
        tfZipCode = new javax.swing.JTextField();
        lbDestination = new javax.swing.JLabel();
        tfDestination = new javax.swing.JTextField();
        btOk = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(3, 2));

        lbZipCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbZipCode.setText("ZIP - Code");
        lbZipCode.setPreferredSize(new java.awt.Dimension(120, 80));
        getContentPane().add(lbZipCode);

        tfZipCode.setText("e.g. 8452,AT");
        getContentPane().add(tfZipCode);

        lbDestination.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDestination.setText("Destination");
        getContentPane().add(lbDestination);

        tfDestination.setText("e.g. Großklein");
        getContentPane().add(tfDestination);

        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        getContentPane().add(btOk);

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btCancel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        ok = true;
        dest=new Destination(tfZipCode.getText(),tfDestination.getText());
        this.dispose();
    }//GEN-LAST:event_btOkActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        ok=false;
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

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
            java.util.logging.Logger.getLogger(AddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddDialog dialog = new AddDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel lbDestination;
    private javax.swing.JLabel lbZipCode;
    private javax.swing.JTextField tfDestination;
    private javax.swing.JTextField tfZipCode;
    // End of variables declaration//GEN-END:variables
}
