
package com.inventory.system.form;

import com.inventory.model.ModelUser;
import com.inventory.service.ServiceUser;
import com.inventory.system.model.ModelData;
import com.inventory.system.model.ModelItems;
import com.inventory.system.model.ModelTransaction;
import com.inventory.system.model.Model_Card;
import com.inventory.system.service.ServiceData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author iqbal
 */
public final class FormHome extends javax.swing.JPanel {
  private final ServiceData serviceData = new ServiceData();
  private final ModelUser ModelUser = new ModelUser();
  private final LocalDateTime sekarang = LocalDateTime.now();
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private String barangMasuk;
  
  
  
  public FormHome() throws SQLException {
    initComponents();
    setTabel();
    setDateNow();
  }
  
//    SET DATE NOW START
  private void setDateNow() throws SQLException{
    List<ModelTransaction> transactions = serviceData.getTotalTransactionByUserId(ModelUser);
    System.out.println("tanggal sekarang = " + sekarang.format(formatter));
    ModelTransaction transaksiTerbaru = null;
    
    for (ModelTransaction transaction : transactions) {
      LocalDate tanggalTransaksi = LocalDate.parse(transaction.getTransactionDate().toString(), formatter);
      // Membandingkan tanggal transaksi dengan tanggal saat ini
      if (tanggalTransaksi.isEqual(sekarang.toLocalDate())) {
        // Jika transaksi terbaru masih kosong atau transaksi saat ini lebih baru
        if (transaksiTerbaru == null || tanggalTransaksi.isAfter(LocalDate.parse(transaksiTerbaru.getTransactionDate().toString(), formatter))) {
          transaksiTerbaru = transaction;
        }
      }
    }
    
    if (transaksiTerbaru != null) {
      System.out.println("Transaksi terbaru pada tanggal " + sekarang.format(formatter) + ":");
      System.out.println("Date: " + transaksiTerbaru.getTransactionDate());
      System.out.println("Type: " + transaksiTerbaru.getTransactionType());
      System.out.println("Total Quantity: " + transaksiTerbaru.getQuantity());
      System.out.println("---------------------------");
    } else {
      System.out.println("Tidak ada transaksi pada tanggal " + sekarang.format(formatter));
    }
  }
//    SET DATE NOW END
  
  
//    SET ROW TABLE START
  private void setTabel() throws SQLException{
    DefaultTableModel model =(DefaultTableModel)TBItems.getModel();
    model.setRowCount(0);
    List<ModelItems> itemsList = (List<ModelItems>) serviceData.getAllItems();
    
    for(ModelItems s : itemsList ) {
      if (s.getQuntity() != 0) {
        
        Object[] rowData = {s.getItemName(), s.getKodeBarang(), s.getQuntity(), s.getCreatedAt(), s.getUpdatedAt(), s.getCategoryName()};
        model.addRow(rowData);
      }
      
    }
  }
//    SET ROW TABLE END
  
  
//    SET VALUE CART START
  public void setDataP(int transaction){
    
    
    if (transaction == 0) {
      barangMasuk = "null";
    }
    else{
      barangMasuk = String.valueOf(transaction);
      
    }
    card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/system/icon/1.png")), "Total barang", barangMasuk, "Null"));
    card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/system/icon/1.png")),"Barang Masuk","null","null"));
    card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/inventory/system/icon/1.png")),"Barang Kluar","null","null"));
  }
//    SET VALUE CART END
  
  
  
  @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
      private void initComponents() {

            jLayeredPane1 = new javax.swing.JLayeredPane();
            card1 = new com.inventory.system.componets.Card();
            card2 = new com.inventory.system.componets.Card();
            card3 = new com.inventory.system.componets.Card();
            jScrollPane1 = new javax.swing.JScrollPane();
            TBItems = new javax.swing.JTable();

            setBackground(new java.awt.Color(255, 255, 255));
            setForeground(new java.awt.Color(102, 102, 102));

            card1.setColor1(new java.awt.Color(0, 153, 102));
            card1.setColor2(new java.awt.Color(0, 204, 102));

            card2.setColor1(new java.awt.Color(0, 153, 102));
            card2.setColor2(new java.awt.Color(0, 204, 102));

            card3.setColor1(new java.awt.Color(0, 153, 102));
            card3.setColor2(new java.awt.Color(0, 204, 102));

            TBItems.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
            TBItems.setModel(new javax.swing.table.DefaultTableModel(
                  new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                  },
                  new String [] {
                        "Nama Barang", "Deskripsi", "Stock", "Di buat", "Di perbarui", "kategori"
                  }
            ) {
                  boolean[] canEdit = new boolean [] {
                        false, false, false, false, false, false
                  };

                  public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                  }
            });
            TBItems.setRowHeight(30);
            jScrollPane1.setViewportView(TBItems);

            jLayeredPane1.setLayer(card1, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(card2, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(card3, javax.swing.JLayeredPane.DEFAULT_LAYER);
            jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
            jLayeredPane1.setLayout(jLayeredPane1Layout);
            jLayeredPane1Layout.setHorizontalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                              .addComponent(jScrollPane1)
                              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(22, 22, 22)
                                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            jLayeredPane1Layout.setVerticalGroup(
                  jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                              .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLayeredPane1)
                        .addGap(22, 22, 22))
            );
            layout.setVerticalGroup(
                  layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))
            );
      }// </editor-fold>//GEN-END:initComponents
      

      // Variables declaration - do not modify//GEN-BEGIN:variables
      private javax.swing.JTable TBItems;
      private com.inventory.system.componets.Card card1;
      private com.inventory.system.componets.Card card2;
      private com.inventory.system.componets.Card card3;
      private javax.swing.JLayeredPane jLayeredPane1;
      private javax.swing.JScrollPane jScrollPane1;
      // End of variables declaration//GEN-END:variables
}
