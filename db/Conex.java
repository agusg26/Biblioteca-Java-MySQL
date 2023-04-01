package db;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conex {
	public Connection con; 
	private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String USER = "root";
	private String BD_URL= "jdbc:mysql://localhost:3306/biblioteca";
	private String PASS = "2003";
	//Este metodo iniciara la conexion
	public Connection conect() {
		try {
			Class.forName(DRIVER); //solamente vamos a decirle que utilice un driver(linea6)
			con = DriverManager.getConnection(BD_URL,USER,PASS); //ayuda a hacer el vinculo
			System.out.println("Conexion exitosa");
		}catch(Exception error) {
			System.out.println("Problemas al intentar conectar a la bd" );
		}
		return con;
	}
	public void desconectar() {
		con=null;
		if(con==null) {
			System.out.println("Conexion terminada");
		}
	}
}
