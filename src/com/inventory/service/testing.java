package com.inventory.service;

import com.inventory.system.service.ServiceData;
import java.sql.SQLException;


public class testing {
      
      
      public static void main(String[] args) throws SQLException {
             ServiceData transaction = new ServiceData();
            // Membuat instance dari ModelUser
            boolean result = transaction.setTransactionByUserId(1, 4, 20, "out", "kluar");

            // Cek hasil transaksi
            if (result) {
                System.out.println(result);
            } else {
                System.out.println("Transaksi gagal.");
            }
      }
}
