package com.inventory.system.form;

import com.inventory.connection.DatabaseConnection;
import com.inventory.system.model.ModelCategories;
import com.inventory.system.model.ModelItems;
import com.inventory.system.service.ServiceData;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author iqbal
 */
public final class Form_1 extends javax.swing.JPanel {
  ServiceData serviceData = new ServiceData();
  private Connection con;
  
  
  public Form_1() throws SQLException {
    initComponents();
    initTabel();
    initCombobox();
    int selectedRow = tblBarang.getSelectedRow();
    if (selectedRow == -1) {
      
      initKodeBarang();
    }
    kategoryId.setRenderer(new DefaultListCellRenderer() {
      
      @Override
      public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        Color transparentGreen = new Color(0, 255, 0, 51);
        if (isSelected) {
          c.setBackground(transparentGreen);
          c.setForeground(Color.BLACK);
        } else {
          c.setBackground(Color.WHITE);
          c.setForeground(Color.BLACK);
        }
        return c;
      }
    });
  }
  
  
  
  
  
  
  
//    INIT CODE BARANG START
  public void initKodeBarang(){
    namaBarang.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        kodeBarang.setText("");
      }
      
      @Override
      public void focusLost(FocusEvent e) {
        kodeBarang.setText(generateCode(namaBarang.getText()));
      }
      
    });
  }
//    INIT CODE BARANG END
  
  
//    INIT COMBOBOX START
  private void initCombobox() throws SQLException{
    List<ModelCategories> categoryList = (List<ModelCategories>) serviceData.getCategory();
    for (ModelCategories s : categoryList) {
      String categoryName = s.getCategoryName();
      boolean alreadyExists = false;
      
      // Periksa apakah item sudah ada di JComboBox
      for (int i = 0; i < kategoryId.getItemCount(); i++) {
        if (kategoryId.getItemAt(i).equals(categoryName)) {
          alreadyExists = true;
          break;
        }
      }
      
      if (!alreadyExists) {
        kategoryId.addItem(categoryName);
      }
      
    }
  }
//    INIT COMBOBOX END
  
  
//    GENERATE CODE BARANG STRAT
  // Metode untuk menghasilkan kode produk
  public static String generateCode(String productName) {
    StringBuilder code = new StringBuilder();
    
    // Memisahkan nama produk menjadi kata-kata terpisah
    String[] words = productName.split(" ");
    
    // Mengambil huruf pertama dari setiap kata dan menambahkannya ke kode
    for (String word : words) {
      if (!word.isEmpty()) {
        code.append(Character.toUpperCase(word.charAt(0)));
      }
    }
    
    // Menghasilkan angka acak 3 digit
    Random random = new Random();
    int randomNumber = random.nextInt(900) + 100; // Menghasilkan angka antara 100 dan 999
    
    // Menambahkan angka acak ke kode
    code.append(randomNumber);
    return "BRG"+code.toString();
  }
//    GENERATE CODE BARANG END
  
  
//    FORMAT MATA UANG START
  public String formatRupiah(double amount) {
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    return formatRupiah.format(amount);
  }
//    FORMAT MATA UANG END
  
  
//    SET RUPIAH TO DOUBLE START
  public double setRupiahToDouble(String price) {
    String cleanedPrice = price.replaceAll("(?i)rp", "").replace(" ", "");
    
    cleanedPrice = cleanedPrice.replace(".", "").replace(",", ".");
    
    return Double.parseDouble(cleanedPrice);
  }
//    SET RUPIAH TO DOUBLE END
  
  
//    INIT TABLE START
  private void initTabel() throws SQLException{
    DefaultTableModel model =(DefaultTableModel)tblBarang.getModel();
    model.setRowCount(0);
    List<ModelItems> itemsList = (List<ModelItems>) serviceData.getAllItems();
    
    for(ModelItems s : itemsList ) {
      
      Object[] rowData = {s.getKodeBarang(), s.getItemName(), s.getCategoryName(),formatRupiah(s.getPrice()), s.getCreatedAt().substring(0, 16), s.getUpdatedAt().substring(0, 16)};
      model.addRow(rowData);
    }
  }
//    INIT TABLE END
  
  
//    GET ID CATEGORY BY ID START
  public int getIdCategoryByName(String name){
    try {
      DatabaseConnection dbConnection = DatabaseConnection.getInstance();
      dbConnection.connectToDatabase();
      con = dbConnection.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException("tidak terkoneksi ke database");
    }
    
    
    PreparedStatement p;
    int category_id = 0;
    
    String sql = "SELECT category_id FROM categories WHERE category_name = ? ";
    try {
      p = con.prepareStatement(sql);
      p.setString(1, name);
      
      ResultSet r = p.executeQuery();
      
      if (r.next()) {
        category_id = r.getInt("category_id");
      }
    } catch (SQLException ex) {
      Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return category_id;
  }
//    GET ID CATEGORY BY ID END
  
  
//    GET PRICE BY CODE ITEMS START
  public double getPriceByKode(String kode){
    try {
      DatabaseConnection dbConnection = DatabaseConnection.getInstance();
      dbConnection.connectToDatabase();
      con = dbConnection.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException("tidak terkoneksi ke database");
    }
    
    
    PreparedStatement p;
    double price = 0;
    
    String sql = "SELECT price FROM items WHERE kode_item = ? ";
    try {
      p = con.prepareStatement(sql);
      p.setString(1, kode);
      
      ResultSet r = p.executeQuery();
      
      if (r.next()) {
        price = r.getDouble("price");
      }
    } catch (SQLException ex) {
      Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return price;
  }
//    GET PRICE BY CODE ITEMS END
  
  
  
  
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLayeredPane1 = new javax.swing.JLayeredPane();
    kodeBarang = new com.inventory.swing.MyTextField();
    namaBarang = new com.inventory.swing.MyTextField();
    kategoryId = new javax.swing.JComboBox<>();
    hargaBarang = new com.inventory.swing.MyTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    BTNUpdate = new com.inventory.swing.ButtonOutLine();
    BTNDelete = new com.inventory.swing.ButtonOutLine();
    BTNAddBarang = new com.inventory.swing.ButtonOutLine();
    jScrollPane1 = new javax.swing.JScrollPane();
    tblBarang = new javax.swing.JTable();

    setBackground(new java.awt.Color(255, 255, 255));

    jLayeredPane1.setBackground(new java.awt.Color(153, 153, 153));

    kodeBarang.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        kodeBarangActionPerformed(evt);
      }
    });

    kategoryId.setBackground(new Color(30, 149, 87, 30));
    kategoryId.setForeground(new java.awt.Color(0, 0, 0));
    kategoryId.setBorder(null);
    kategoryId.setLightWeightPopupEnabled(false);
    kategoryId.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        kategoryIdActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(0, 0, 0));
    jLabel1.setText("Nama Barang :");

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(0, 0, 0));
    jLabel2.setText("Kode Barang:");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(0, 0, 0));
    jLabel3.setText("Kategory Barang:");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(0, 0, 0));
    jLabel4.setText("Harga Barang:");

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

    BTNAddBarang.setBackground(new java.awt.Color(0, 255, 153));
    BTNAddBarang.setForeground(new java.awt.Color(0, 0, 0));
    BTNAddBarang.setText("Save");
    BTNAddBarang.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BTNAddBarangActionPerformed(evt);
      }
    });

    tblBarang.setBackground(new Color(30, 149, 87, 30));
    tblBarang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
    tblBarang.setOpaque(false);
    tblBarang.setRowHeight(30);
    tblBarang.setSelectionBackground(new java.awt.Color(0, 204, 102));
    jScrollPane1.setViewportView(tblBarang);

    jLayeredPane1.setLayer(kodeBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(namaBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(kategoryId, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(hargaBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(BTNUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(BTNDelete, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(BTNAddBarang, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
    jLayeredPane1.setLayout(jLayeredPane1Layout);
    jLayeredPane1Layout.setHorizontalGroup(
      jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPane1Layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
          .addComponent(kodeBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(namaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(120, 120, 120)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(hargaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(kategoryId, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
        .addComponent(BTNAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(356, 356, 356)
        .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
    );
    jLayeredPane1Layout.setVerticalGroup(
      jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPane1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(6, 6, 6)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(kategoryId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(23, 23, 23)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(hargaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(18, 18, 18)
        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(BTNUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(BTNDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(BTNAddBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 0, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap(22, Short.MAX_VALUE)
        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(22, 22, 22))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(22, 22, 22)
        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(24, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

      private void kodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeBarangActionPerformed
        // TODO add your handling code here:
      }//GEN-LAST:event_kodeBarangActionPerformed

      private void kategoryIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kategoryIdActionPerformed
        // TODO add your handling code here:
      }//GEN-LAST:event_kategoryIdActionPerformed

      private void BTNDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNDeleteActionPerformed
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow == -1) {
          Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_LEFT, "KLIK TABLE UNTUK DI HAPUS!!!");
          return;
        }
        String kode_barang = tblBarang.getValueAt(selectedRow, 0).toString();
        try {
          boolean status = serviceData.deleteItemByCode(kode_barang);
          if (status) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.BOTTOM_LEFT, "DATA BERHASIL DI HAPUS");
            initTabel();
          }else{
            Notifications.getInstance().show(Notifications.Type.ERROR,Notifications.Location.BOTTOM_LEFT, "DATA GAGAL DI HAPUS!");
          }
        } catch (SQLException ex) {
          Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
      }//GEN-LAST:event_BTNDeleteActionPerformed

      private void BTNUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNUpdateActionPerformed
        int selectedRow = tblBarang.getSelectedRow();
        if (selectedRow == -1) {
          Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_LEFT, "KLIK TABLE UNTUK DI PERBARUI");
          return;
        }
        
        String kode_barang  = tblBarang.getValueAt(selectedRow, 0).toString();
        String nama_barang  = tblBarang.getValueAt(selectedRow, 1).toString();
        String haarga = tblBarang.getValueAt(selectedRow, 3).toString();
        kodeBarang.setText(kode_barang);
        namaBarang.setText(nama_barang);
        hargaBarang.setText(haarga);

      }//GEN-LAST:event_BTNUpdateActionPerformed

      private void BTNAddBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAddBarangActionPerformed
        int idCategory = getIdCategoryByName(kategoryId.getSelectedItem().toString());
        String kodeBarang = generateCode(namaBarang.getText());
        
        try {
          
          if (idCategory == 0 || namaBarang.getText().isEmpty() || hargaBarang.getText().isEmpty() || kodeBarang.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.INFO,Notifications.Location.BOTTOM_LEFT, "JANGAN LUPA DI ISI SEMUA FORM DI ATAS!!");
          }else{
            int selectedRow = tblBarang.getSelectedRow();
            
            if (selectedRow == -1) {
              
              initKodeBarang();
              boolean status = serviceData.setItem(idCategory, namaBarang.getText(), Double.parseDouble(hargaBarang.getText()), kodeBarang, 0);
              if (status) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.BOTTOM_LEFT, "DATA BARANG BERHASIL DI TAMBAH");
                initTabel();
                namaBarang.setText("");
                this.kodeBarang.setText("");
                hargaBarang.setText("");
              }else{
                Notifications.getInstance().show(Notifications.Type.ERROR,Notifications.Location.BOTTOM_LEFT, "DATA BARANG GAGAL DI TAMBAH!");
              }
            }else{
              String kode_barang  = tblBarang.getValueAt(selectedRow, 0).toString();
              this.kodeBarang.setText(kode_barang);
              boolean status = serviceData.setUpdateItem(idCategory, namaBarang.getText(), setRupiahToDouble(hargaBarang.getText()), kode_barang, 0);
              if (status) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS,Notifications.Location.BOTTOM_LEFT, "DATA BARANG BERHASIL DI UPDATE");
                initTabel();
                namaBarang.setText("");
                this.kodeBarang.setText("");
                hargaBarang.setText("");
              }else{
                Notifications.getInstance().show(Notifications.Type.ERROR,Notifications.Location.BOTTOM_LEFT, "DATA BARANG GAGAL DI UPDATE!");
              }
              
            }
            
          }
          
        } catch (SQLException ex) {
          Logger.getLogger(Form_1.class.getName()).log(Level.SEVERE, null, ex);
        }
      }//GEN-LAST:event_BTNAddBarangActionPerformed
      
      
      
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private com.inventory.swing.ButtonOutLine BTNAddBarang;
  private com.inventory.swing.ButtonOutLine BTNDelete;
  private com.inventory.swing.ButtonOutLine BTNUpdate;
  private com.inventory.swing.MyTextField hargaBarang;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLayeredPane jLayeredPane1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JComboBox<String> kategoryId;
  private com.inventory.swing.MyTextField kodeBarang;
  private com.inventory.swing.MyTextField namaBarang;
  private javax.swing.JTable tblBarang;
  // End of variables declaration//GEN-END:variables
}
