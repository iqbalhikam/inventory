package com.inventory.main;

import com.inventory.model.ModelUser;
import com.inventory.system.event.EventMenuSelected;
import com.inventory.system.form.FormHome;
import com.inventory.system.form.Form_1;
import com.inventory.system.form.Form_2;
import com.inventory.system.form.Form_3;
import com.inventory.system.form.form_4;
import com.inventory.system.model.ModelTransaction;
import com.inventory.system.service.ServiceData;
import com.inventory.system.session.UserSession;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import raven.toast.Notifications;

/**
 *
 * @author iqbal
 */
public class MainSystem extends javax.swing.JFrame {
  
  private FormHome home;
  private Form_1 form1;
  private Form_2 form2;
  private Form_3 form3;
  private form_4 form4;
  
  private final ModelUser user;
  private final ServiceData serviceData = new ServiceData();
  private int transaction;
  
  public MainSystem(ModelUser user) throws SQLException {
    initComponents();
    initMenuSelected();
    
    this.user = user;
    System.out.println(user.getUserID());
    setBackground(new Color(0,0,0,0));
    
    panelMenu1.initMoving(MainSystem.this);
    lbUser.setText(user.getUserName());
    home.setDataP(transaction);
    setLocationRelativeTo(null);
    setForm(home);
    home.setDataP(transaction);
    Notifications.getInstance().setJFrame(this);
    System.out.println("total transaction"+totalTransaction(user));
  }
  
  private int totalTransaction(ModelUser user) throws SQLException{
    int totalBarang = 0;
    List<ModelTransaction> modelTransaction = (List<ModelTransaction>) serviceData.getTransactionByUserId(user);
    totalBarang = modelTransaction.size();
    
    return totalBarang;
  }
  
//    INIT MENU SELECTED START
  private void initMenuSelected() throws SQLException{
    
    home = new FormHome();
    form1 = new Form_1();
    form2 = new Form_2();
    form3 = new Form_3();
    form4 = new form_4();
    
    panelMenu1.addEventMenuSelected((int index) -> {
      System.out.println("index : " + index);
      switch (index) {
        case 0:
          setForm(home);
          home.setDataP(transaction);
          break;
        case 1:
          setForm(form1);
          
          break;
        case 2:
          setForm(form2);
          break;
        case 3:
          setForm(form3);
          break;
        case 4:
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          break;
        case 8:
          setForm(form4);
          break;
        case 9:
          break;
        case 10:
          break;
        case 11:
          break;
        case 12:
          Main.clearUserIdFromPersistentStorage();
          setDefaultCloseOperation(EXIT_ON_CLOSE);
          System.exit(0);
        default:
          break;
      }
    });
    
  }
//    INIT MENU SELECTED END
  
  
//    SET FORM MENU START
  private void setForm(JComponent com) {
    mainPanel.removeAll();
    mainPanel.add(com);
    mainPanel.repaint();
    mainPanel.revalidate();
  }
//    SET FORM MENU END
  
  
  @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
            panelBorder1 = new com.inventory.swing.panelBorder();
            layer1 = new javax.swing.JLayeredPane();
            jLayeredPane1 = new javax.swing.JLayeredPane();
            btnExit = new javax.swing.JButton();
            mainPanel = new javax.swing.JPanel();
            panelMenu1 = new com.inventory.system.componets.panelMenu();
            lbUser = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setUndecorated(true);

            panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

            btnExit.setBackground(new java.awt.Color(204, 204, 204));
            btnExit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            btnExit.setForeground(new java.awt.Color(204, 0, 0));
            btnExit.setText("X");
            btnExit.setBorder(null);
            btnExit.setFocusable(false);
            btnExit.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        btnExitActionPerformed(evt);
                  }
            });

            jLayeredPane1.setLayer(btnExit, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
            jLayeredPane1.setLayout(jLayeredPane1Layout);
            jLayeredPane1Layout.setHorizontalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0))
            );
            jLayeredPane1Layout.setVerticalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnExit)
                        .addGap(27, 27, 27))
            );

            mainPanel.setLayout(new java.awt.BorderLayout());

            lbUser.setBackground(new java.awt.Color(255, 255, 255));
            lbUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
            lbUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lbUser.setText("User Name");

            layer1.setLayer(jLayeredPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
            layer1.setLayer(mainPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
            layer1.setLayer(panelMenu1, javax.swing.JLayeredPane.DEFAULT_LAYER);
            layer1.setLayer(lbUser, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout layer1Layout = new javax.swing.GroupLayout(layer1);
            layer1.setLayout(layer1Layout);
            layer1Layout.setHorizontalGroup(
                  layer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layer1Layout.createSequentialGroup()
                        .addComponent(panelMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addGroup(layer1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(layer1Layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1064, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 20, Short.MAX_VALUE)))
                        .addContainerGap())
            );
            layer1Layout.setVerticalGroup(
                  layer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(panelMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGroup(layer1Layout.createSequentialGroup()
                        .addGroup(layer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(jLayeredPane1)
                              .addGroup(layer1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
            );

            javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
            panelBorder1.setLayout(panelBorder1Layout);
            panelBorder1Layout.setHorizontalGroup(
                  panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(layer1)
            );
            panelBorder1Layout.setVerticalGroup(
                  panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(layer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );

            pack();
            setLocationRelativeTo(null);
      }// </editor-fold>//GEN-END:initComponents

      private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        setVisible(false);
        System.exit(0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
      }//GEN-LAST:event_btnExitActionPerformed
      
      
      
      public static void main(ModelUser user) {
        java.awt.EventQueue.invokeLater(() -> {
          try {
            new MainSystem(user).setVisible(true);
          } catch (SQLException ex) {
            Logger.getLogger(MainSystem.class.getName()).log(Level.SEVERE, null, ex);
          }
        });
      }
      
      
      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JButton btnExit;
      private javax.swing.Box.Filler filler1;
      private javax.swing.JLayeredPane jLayeredPane1;
      private javax.swing.JLayeredPane layer1;
      private javax.swing.JLabel lbUser;
      private javax.swing.JPanel mainPanel;
      private com.inventory.swing.panelBorder panelBorder1;
      private com.inventory.system.componets.panelMenu panelMenu1;
      // End of variables declaration//GEN-END:variables
}
