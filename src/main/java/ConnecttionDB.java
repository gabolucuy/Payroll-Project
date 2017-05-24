import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecttionDB {
	
	public static void main(String args[]){
		String localhost = "jdbc:mysql://localhost/payroll";
		String user = "admin";	
		String password = "admin";
		try {
			Connection connect = DriverManager.getConnection(localhost, user, password);
			System.out.println("works :)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
