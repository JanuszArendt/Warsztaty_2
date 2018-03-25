package pl.coderslab.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pl.coderslab.model.User;

public class AdminUserApp {
	
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/warsztaty"
                + "?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {
            Statement statement = conn.createStatement();
            User jan = new User();
            
            jan.setUsername("Jan");
            jan.setEmail("jan22@gmail.com");
            jan.setPassword("passwd1");
            jan.setUserGroupId(1);
            jan.saveToDB(conn);
          
           User[] data = User.loadAll(conn);
           for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
           
   
        
           
            
            conn.close();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
}
