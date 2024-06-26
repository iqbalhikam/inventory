
package com.inventory.system.model;

import com.inventory.model.ModelUser;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author iqbal
 */
public class ModelTransaction {
      private int user_id;
      private int transaction_id;
      private int item_id;
      private int quantity;
      private String transactionType;
      private String note;
      private Date transaction_date; // Tidak ada setter untuk transaction_date
      
      // Getter and setter for user_id
      public int getUserID() {
            return user_id;
      }
      
      public void setUserID(int user_id) {
            this.user_id = user_id;
      }
      
      // Getter and setter for transaction_id
      public int getTransactionID() {
            return transaction_id;
      }
      
      public void setTransactionID(int transaction_id) {
            this.transaction_id = transaction_id;
      }
      
      // Getter and setter for item_id
      public int getItemID() {
            return item_id;
      }
      
      public void setItemID(int item_id) {
            this.item_id = item_id;
      }
      
      // Getter and setter for quantity
      public int getQuantity() {
//            if (quantity == 0 ) {
//                  return 0;
//            }else{
return quantity;
//            }
      }
      
      public void setQuantity(int quantity) {
            this.quantity = quantity;
      }
      
      // Getter and setter for transactionType
      public String getTransactionType() {
            return transactionType;
      }
      
      public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
      }
      
      // Getter for transaction_date (no setter)
      public Date getTransactionDate() {
            return transaction_date;
      }
      
      // Getter and setter for note
      public String getNote() {
            return note;
      }
      
      public void setNote(String note) {
            this.note = note;
      }

      public ModelTransaction() {
      }
      
      public ModelTransaction(int user_id){
            this.user_id = user_id;
      }
      public ModelTransaction(Date transaction_date, String transaction_type, int total_transaction){
            this.transaction_date = transaction_date;
            this.transactionType = transaction_type;
            this.quantity = total_transaction;
      }
      public ModelTransaction(int user_id, int transaction_id, int item_id, int quantity, String transactionType, String note, Date  transaction_date){
            this.user_id = user_id;
            this.transaction_id = transaction_id;
            this.item_id = item_id;
            this.quantity = quantity;
            this.transactionType = transactionType;
            this.note = note;
            this.transaction_date = transaction_date;
      }

      
      
}
