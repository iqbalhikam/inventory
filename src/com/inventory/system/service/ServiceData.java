package com.inventory.system.service;

import com.inventory.connection.DatabaseConnection;
import com.inventory.model.ModelUser;
import com.inventory.system.model.ModelCategories;
import com.inventory.system.model.ModelItems;
import com.inventory.system.model.ModelTransaction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author iqbal
 */
public class ServiceData {
      private final Connection con;
      
      public ServiceData() {
            try {
                  DatabaseConnection dbConnection = DatabaseConnection.getInstance();
                  dbConnection.connectToDatabase();
                  con = dbConnection.getConnection();
            } catch (SQLException e) {
                  throw new RuntimeException("Failed to connect to the database");
            }
      }
      
//    GET CATEGORY START
      public List<ModelCategories> getCategory() throws SQLException{
            PreparedStatement pstmtInsert = null;
            int category_id;
            String category_name;
            List<ModelCategories> categoryList = new ArrayList<>();
            String sql = "SELECT * FROM categories";
            
            try {
                  pstmtInsert  = con.prepareStatement(sql);
                  ResultSet r = pstmtInsert.executeQuery();
                  while (r.next()) {
                        category_id = r.getInt("category_id");
                        category_name = r.getString("category_name");
                        ModelCategories category = new ModelCategories(category_id, category_name);
                        categoryList.add(category);
                  }
                  
            } catch (SQLException e) {
                  if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                      System.out.println(ex);
                }
            }
            System.out.println("tidak terkoneksi dengan database\ncode erorr: "+e);
            throw e;
            }finally {
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
                
        }
            
            return categoryList;
      }
//    GET CATEGORY END
      
      
      
//    CREATE CATEGORY START
      public String setCategory(String category_name,String description) throws SQLException{
            PreparedStatement pstmtInsert = null;
            String success = null;
            if (category_name.isEmpty() || description.isEmpty()) {
                  success = null;
            }else{
            try {
                  String sql = "INSERT INTO categories (category_name, description ) VALUES (?, ?)";
                  pstmtInsert = con.prepareStatement(sql);
                  pstmtInsert.setString(1, category_name);
                  pstmtInsert.setString(2, description);
                  int rowsAffected = pstmtInsert.executeUpdate();
                  if (rowsAffected > 0) {
                        success = "Berhasil";
                  }
            } catch (SQLException e) {
            if (con != null) {
                try {
                        con.rollback();
                } catch (SQLException ex) {
                        System.out.println(ex);
                }
            }
            System.out.println("tidak terkoneksi dengan database\ncode erorr: "+e);
            throw e;
            } finally {
                  if (pstmtInsert != null) {
                        pstmtInsert.close();
                  }
                  if (con != null) {
                        con.setAutoCommit(true);
                  }       
            }
            }
        return success;
      }
//    CREATE CATEGORY END
      
      
      
      
      
//    GET ALL ITEMS START
      public List<ModelItems> getAllItems() throws SQLException{
            PreparedStatement pstmtInsert = null;
            List<ModelItems> itemsList = new ArrayList<>();
            try {
                  
            pstmtInsert  = con.prepareStatement("SELECT id.category_id, id.category_name, ci.* FROM categories id JOIN items ci ON id.category_id = ci.category_id");
            ResultSet r = pstmtInsert.executeQuery();
            while (r.next()) {
                  String category_name = r.getString("category_name");
                  int item_id = r.getInt("item_id");
                  int category_id = r.getInt("category_id");
                  int quantity = r.getInt("quantity");
                  String item_name = r.getString("item_name");
                  double price = r.getDouble("price");
                  String created_at = r.getString("created_at");
                  String updated_at = r.getString("updated_at");
                  String kode_barang = r.getString("kode_item");
                  
                  ModelItems item =  new ModelItems(item_id, category_id,quantity, price, category_name, item_name, created_at, updated_at, kode_barang);
                  itemsList.add(item);
            }
            } catch (SQLException e) {
                  if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                      System.out.println(ex);
                }
            }
            System.out.println("tidak terkoneksi dengan database\ncode erorr: "+e);
            throw e;
            }finally {
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
                
        }
            
            return itemsList;
      }
//    GET ALL ITEMS END


//    CREATE ITEMS START
    public boolean setItem(int categoryID, String item_name, double price, String code_item, int quantitiy) throws SQLException {
        PreparedStatement pstmtInsert = null;
        boolean success = false;
        PreparedStatement pstmtCheck;
        ResultSet rsCheck ;
          if (item_name.isEmpty() ) {
                success = false;
          }else{
        try {
            con.setAutoCommit(false);
            
            String SQLchecking = "SELECT category_id FROM categories WHERE category_id = ?";
            pstmtCheck = con.prepareStatement(SQLchecking);
            pstmtCheck.setInt(1, categoryID);
            rsCheck = pstmtCheck.executeQuery();
            if (!rsCheck.next()) {
                  System.out.println("Category dengan ID " + categoryID + " tidak ditemukan.");
            }else{
                  String sql = "INSERT INTO items (category_id, item_name, price, kode_item, quantity) VALUES (?, ?, ?, ?, ?)";
                  pstmtInsert = con.prepareStatement(sql);
                  
                  pstmtInsert.setInt(1, categoryID);
                  pstmtInsert.setString(2, item_name);
                  pstmtInsert.setDouble(3, price);
                  pstmtInsert.setString(4, code_item);
                  pstmtInsert.setInt(5, quantitiy);
                  int rowsAffected = pstmtInsert.executeUpdate();
                  if (rowsAffected > 0) {
                        success = true;
                  }
                  con.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                      System.out.println(ex);
                }
            }
            System.out.println("tidak terkoneksi dengan database\ncode erorr: "+e);
            throw e;
        } finally {
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
                
        }
          }
        return success;
    }
//    CREATE ITEMS END
    



    // UPDATE ITEMS START
    public boolean setUpdateItem(int categoryID, String item_name, double price, String code_item, int quantity) throws SQLException {
    PreparedStatement pstmtUpdate = null;
    PreparedStatement pstmtCheck = null;
    ResultSet rsCheck = null;
    boolean success = false;

    if (item_name.isEmpty()) {
        success = false;
    } else {
        try {
            con.setAutoCommit(false);
            
            String SQLchecking = "SELECT category_id FROM categories WHERE category_id = ?";
            pstmtCheck = con.prepareStatement(SQLchecking);
            pstmtCheck.setInt(1, categoryID);
            rsCheck = pstmtCheck.executeQuery();
            
            if (!rsCheck.next()) {
                System.out.println("Category dengan ID " + categoryID + " tidak ditemukan.");
            } else {
                String sql = "UPDATE items SET category_id = ?, item_name = ?, price = ?, quantity = ? WHERE kode_item = ?";

                pstmtUpdate = con.prepareStatement(sql);
                pstmtUpdate.setInt(1, categoryID);
                pstmtUpdate.setString(2, item_name);
                pstmtUpdate.setDouble(3, price);
                pstmtUpdate.setInt(4, quantity); // This should be set here
                pstmtUpdate.setString(5, code_item); // This should be set here

                int rowsAffected = pstmtUpdate.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
                con.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Tidak terkoneksi dengan database\nCode error: " + e);
            throw e;
        } finally {
            if (rsCheck != null) {
                try {
                    rsCheck.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (pstmtCheck != null) {
                try {
                    pstmtCheck.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (pstmtUpdate != null) {
                try {
                    pstmtUpdate.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
    }
    return success;
}

    // UPDATE ITEMS END
    
    
    // DELETE ITEMS BY CODE ITEM START
    public boolean deleteItemByCode(String code) throws SQLException {
      PreparedStatement pstmtDeleted = null;
    PreparedStatement pstmtCheck = null;
    ResultSet rsCheck = null;
    boolean success = false;

    if (code.isEmpty()) {
        success = false;
    } else {
        try {
            con.setAutoCommit(false);

            String SQLchecking = "SELECT kode_item FROM items WHERE kode_item = ?";
            pstmtCheck = con.prepareStatement(SQLchecking);
            pstmtCheck.setString(1, code);
            rsCheck = pstmtCheck.executeQuery();

            if (!rsCheck.next()) {
                System.out.println("Item dengan kode " + code + " tidak ditemukan.");
                success = false;
            } else {
                String sql = "DELETE FROM items WHERE kode_item = ?";
                pstmtDeleted = con.prepareStatement(sql);
                pstmtDeleted.setString(1, code);

                int rowsAffected = pstmtDeleted.executeUpdate();
                if (rowsAffected > 0) {
                    success = true;
                }
                con.commit();
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Tidak terkoneksi dengan database\nCode error: " + e);
            throw e;
        } finally {
            if (rsCheck != null) {
                try {
                    rsCheck.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (pstmtCheck != null) {
                try {
                    pstmtCheck.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (pstmtDeleted != null) {
                try {
                    pstmtDeleted.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
            if (con != null) {
                con.setAutoCommit(true);
            }
        }
    }
    return success;
}
// DELETE ITEMS BY CODE ITEM END

      
    
    
    
    
//    GET TREANSACTION BY USER ID START
      public List<ModelTransaction> getTransactionByUserId(ModelUser userID) throws SQLException{
            int user_id;
            int transaction_id;
            int item_id;
            int quantity;
            String transcT;
            Date transcD;
            String note;
            List<ModelTransaction> dataList = new ArrayList<>();
            PreparedStatement p = con.prepareStatement("SELECT ui.user_id, tu.* FROM users ui JOIN transactions tu ON ui.user_id = tu.user_id WHERE ui.user_id = ?");
            p.setInt(1, userID.getUserID());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                  user_id = r.getInt("user_id");
                  transaction_id = r.getInt("transaction_id");
                  item_id = r.getInt("item_id");
                  quantity = r.getInt("quantity");
                  transcT= r.getString("transaction_type");
                  transcD = r.getDate("transaction_date");
                  note = r.getString("note");
            }else{
                  user_id = 0;
                  transaction_id = 0;
                  item_id = 0;
                  quantity = 0;
                  transcT= "null";
                  transcD = null;
                  note = "null";
                  System.out.println("Tidak ada transaksi dari user ini: " + userID.getUserName());
            }
            ModelTransaction data = new ModelTransaction(user_id, transaction_id, item_id,quantity,transcT,note, transcD);
            dataList.add(data);
            return dataList;
      }
//    GET TREANSACTION BY USER ID END
      
      
      
      
      
//    GET TOTAL TREANSACTION STRAT
      public List<ModelTransaction> getTotalTransactionByUserId(ModelUser userID) throws SQLException {
        List<ModelTransaction> transactions = new ArrayList<>();
        String sql = "SELECT DATE(transaction_date) as transaction_date, transaction_type, SUM(quantity) as total_quantity FROM transactions WHERE user_id = ? GROUP BY DATE(transaction_date), transaction_type;";
        PreparedStatement p = con.prepareStatement(sql);
        p.setInt(1, userID.getUserID());
        ResultSet r = p.executeQuery();

        while (r.next()) {
            Date transaction_date = r.getDate("transaction_date");
            String transaction_type = r.getString("transaction_type");
            int total_quantity = r.getInt("total_quantity");
            
            ModelTransaction dataGTTBUL = new ModelTransaction(transaction_date, transaction_type, total_quantity);
            transactions.add(dataGTTBUL);
        }
        
        return transactions;
    }
//    GET TOTAL TRNASACTION END
    
      
      
      
      
//    SET TREANSACTION BY USER ID START
    public boolean setTransactionByUserId(int userID, int itemID, int quantity, String transactionType, String note) throws SQLException {
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtUpdate = null;
        PreparedStatement pstmtCheck = null;
        ResultSet rs = null;
        boolean success = false;

          if (transactionType.isEmpty()||note.isEmpty()) {
                
          }else{
        try {
            con.setAutoCommit(false);

            if (transactionType.equals("out")) {
                // Check available quantity
                String sqlCheck = "SELECT quantity FROM items WHERE item_id = ?";
                pstmtCheck = con.prepareStatement(sqlCheck);
                pstmtCheck.setInt(1, itemID);
                rs = pstmtCheck.executeQuery();

                if (rs.next()) {
                    int availableQuantity = rs.getInt("quantity");
                    if (availableQuantity < quantity) {
                        throw new SQLException("Insufficient quantity available for item_id: " + itemID);
                    }
                } else {
                    throw new SQLException("Item not found with item_id: " + itemID);
                }
            }

            String sql = "INSERT INTO transactions (user_id, item_id, quantity, transaction_type, note) VALUES (?, ?, ?, ?, ?)";
            pstmtInsert = con.prepareStatement(sql);
            pstmtInsert.setInt(1, userID);
            pstmtInsert.setInt(2, itemID);
            pstmtInsert.setInt(3, quantity);
            pstmtInsert.setString(4, transactionType);
            pstmtInsert.setString(5, note);
            pstmtInsert.executeUpdate();

            String sqlUpdate;
            if (transactionType.equals("in")) {
                sqlUpdate = "UPDATE items SET quantity = quantity + ? WHERE item_id = ?";
            } else if (transactionType.equals("out")) {
                sqlUpdate = "UPDATE items SET quantity = quantity - ? WHERE item_id = ?";
            } else {
                throw new IllegalArgumentException("Invalid transaction type: " + transactionType);
            }

            pstmtUpdate = con.prepareStatement(sqlUpdate);
            pstmtUpdate.setInt(1, quantity);
            pstmtUpdate.setInt(2, itemID);
            pstmtUpdate.executeUpdate();

            con.commit(); // Commit transaction
            success = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmtCheck != null) {
                pstmtCheck.close();
            }
            if (pstmtInsert != null) {
                pstmtInsert.close();
            }
            if (pstmtUpdate != null) {
                pstmtUpdate.close();
            }
            if (con != null) {
                con.setAutoCommit(true); // Reset auto commit to true
                con.close();
            }
        }
          }
        
        return success;
    }
//    SET TREANSACTION BY USER ID END
    


}

      
      
      
      
      
      
      

