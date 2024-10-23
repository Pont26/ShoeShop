package MainApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

//import com.shoeshop.controller.CustomerController;
import com.shoeshop.service.impl.PurchaseServiceImpl;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        String menu = null;
        do {
            System.out.println("!!!!Welcome From Lily Shoe Shop!!!");
            System.out.println("1. Purchase Shoe:" +
                    "\n2. Customer Dashboard: " +
                    "\n3. Exit");
            menu = br.readLine();
            switch (menu) {
                case "1":
                    PurchaseServiceImpl purchaseService = new PurchaseServiceImpl();
                  
                    break;
                case "2":
      //   CustomerController customerController = new CustomerController();
                  
                    break;
            }
        } while (!menu.equals("3"));
    }
}
