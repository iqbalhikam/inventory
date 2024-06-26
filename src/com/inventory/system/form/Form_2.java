package com.inventory.system.form;

import com.inventory.system.model.ModelItems;
import com.inventory.system.service.ServiceData;
import java.sql.SQLException;
import java.util.List;


public final class Form_2 extends javax.swing.JPanel {
  ServiceData serviceData = new ServiceData();
  
  public Form_2() throws SQLException {
    initComponents();
    initCombobox();
    
  }
  
//    INIT COMBOBOX START
  public void initCombobox() throws SQLException{
    List<ModelItems> modelItems = (List<ModelItems>) serviceData.getAllItems();
    for (ModelItems s : modelItems) {
      String itemsName = s.getItemName();
      boolean alreadyExists = false;
      
      // Periksa apakah item sudah ada di JComboBox
      for (int i = 0; i < JCombobox.getItemCount(); i++) {
        if (JCombobox.getItemAt(i).equals(itemsName)) {
          alreadyExists = true;
          break;
        }
      }
      
      if (!alreadyExists) {
        JCombobox.addItem(itemsName);
      }
      
    }
  }
//    INIT COMBOBOX END
  
  
  @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            jLayeredPane1 = new javax.swing.JLayeredPane();
            JCombobox = new javax.swing.JComboBox<>();
            FieldStock = new com.inventory.swing.MyTextField();
            jScrollPane1 = new javax.swing.JScrollPane();
            TBLTransaction = new javax.swing.JTable();
            BTNAddBarang = new com.inventory.swing.ButtonOutLine();
            BTNUpdate = new com.inventory.swing.ButtonOutLine();
            BTNDelete = new com.inventory.swing.ButtonOutLine();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();
            FieldTotalHarga = new com.inventory.swing.MyTextField();
            FieldHarga = new com.inventory.swing.MyTextField();
            jLabel4 = new javax.swing.JLabel();
            jLabel5 = new javax.swing.JLabel();

            setBackground(new java.awt.Color(255, 255, 255));
            setForeground(new java.awt.Color(255, 255, 255));

            jLayeredPane1.setOpaque(true);

            JCombobox.setBorder(null);
            JCombobox.setPreferredSize(new java.awt.Dimension(64, 38));
            JCombobox.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        JComboboxActionPerformed(evt);
                  }
            });

            TBLTransaction.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            TBLTransaction.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                  },
                  new String [] {
                        "Kode ", "Nama ", "Kategory", "Harga", "Di buat", "Di perbarui"
                  }
            ) {
                  boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false
                  };

                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                  }
            });
            TBLTransaction.setRowHeight(30);
            jScrollPane1.setViewportView(TBLTransaction);

            BTNAddBarang.setBackground(new java.awt.Color(0, 255, 153));
            BTNAddBarang.setForeground(new java.awt.Color(0, 0, 0));
            BTNAddBarang.setText("Save");
            BTNAddBarang.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BTNAddBarangActionPerformed(evt);
                  }
            });

            BTNUpdate.setBackground(new java.awt.Color(0, 255, 153));
            BTNUpdate.setForeground(new java.awt.Color(0, 0, 0));
            BTNUpdate.setText("Update");
            BTNUpdate.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BTNUpdateActionPerformed(evt);
                  }
            });

            BTNDelete.setBackground(new java.awt.Color(0, 255, 153));
            BTNDelete.setForeground(new java.awt.Color(0, 0, 0));
            BTNDelete.setText("Delete");
            BTNDelete.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                        BTNDeleteActionPerformed(evt);
                  }
            });

            jLabel2.setBackground(new java.awt.Color(255, 255, 255));
            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(0, 0, 0));
            jLabel2.setText("ID Barang :");

            jLabel3.setBackground(new java.awt.Color(255, 255, 255));
            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(0, 0, 0));
            jLabel3.setText("Total Harga :");

            jLabel4.setBackground(new java.awt.Color(255, 255, 255));
            jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(0, 0, 0));
            jLabel4.setText("Stock Masuk:");

            jLabel5.setBackground(new java.awt.Color(255, 255, 255));
            jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(0, 0, 0));
            jLabel5.setText("Harga :");

            jLayeredPane1.setLayer(JCombobox, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(FieldStock, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(BTNAddBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(BTNUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(BTNDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(FieldTotalHarga, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(FieldHarga, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
            jLayeredPane1.setLayout(jLayeredPane1Layout);
            jLayeredPane1Layout.setHorizontalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                              .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(FieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                    .addComponent(FieldTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                              .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(jLabel2)
                                          .addComponent(JCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                          .addComponent(FieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                          .addComponent(jLabel4)
                                          .addComponent(jLabel3)))
                              .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(BTNAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(331, 331, 331)
                                    .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))
            );
            jLayeredPane1Layout.setVerticalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jLabel2)
                              .addComponent(jLabel4))
                        .addGap(6, 6, 6)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(JCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(FieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(jLabel3)
                              .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                              .addComponent(FieldTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(FieldHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                              .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(BTNAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLayeredPane1)
                        .addGap(22, 22, 22))
            );
            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLayeredPane1)
                        .addGap(22, 22, 22))
            );
      }// </editor-fold>//GEN-END:initComponents

      private void JComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboboxActionPerformed
        // TODO add your handling code here:
      }//GEN-LAST:event_JComboboxActionPerformed

      private void BTNAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddBarangActionPerformed
        
      }//GEN-LAST:event_BTNAddBarangActionPerformed

      private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        
      }//GEN-LAST:event_BTNUpdateActionPerformed

      private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        
      }//GEN-LAST:event_BTNDeleteActionPerformed
      

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private com.inventory.swing.ButtonOutLine BTNAddBarang;
      private com.inventory.swing.ButtonOutLine BTNDelete;
      private com.inventory.swing.ButtonOutLine BTNUpdate;
      private com.inventory.swing.MyTextField FieldHarga;
      private com.inventory.swing.MyTextField FieldStock;
      private com.inventory.swing.MyTextField FieldTotalHarga;
      private javax.swing.JComboBox<String> JCombobox;
      private javax.swing.JTable TBLTransaction;
      private javax.swing.JLabel jLabel2;
      private javax.swing.JLabel jLabel3;
      private javax.swing.JLabel jLabel4;
      private javax.swing.JLabel jLabel5;
      private javax.swing.JLayeredPane jLayeredPane1;
      private javax.swing.JScrollPane jScrollPane1;
      // End of variables declaration//GEN-END:variables
}
