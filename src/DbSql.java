import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class DbSql {
	Connection conn=null;
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:/Users/John/Desktop/My GAWA/Sample.sqlite");
			//JOptionPane.showMessageDialog(null, "You are Connected");
			return conn;
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
