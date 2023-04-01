package entidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Conex;

public class Libros {
	
	// declaramos las variables
	private String consulta;
	private Conex obj;
	
	public Libros(String cons, Conex obj) { // creamos el constructor 
		super();
		this.consulta= cons;
		this.obj=obj;
	}
	
	public Libros () {
		
	}
	
	// generamos el metod get y set 

	
	public String getconsulta() {
		return consulta;
	}
	public void setconsulta(String cons) {
		this.consulta = cons;
	
	}
	
	// funcion para traer datos de libros
	
	public void obtener_titulo () {
		Statement stmt;
		try {
			stmt = obj.con.createStatement();
			String cadena = "SELECT * FROM libros WHERE titulo='"+this.consulta+"'";
			ResultSet res= stmt.executeQuery(cadena);
			while (res.next()) {
				int id=res.getInt("id_libro");
				String titulo=res.getString("titulo");
				String autor=res.getString("autor");
				String genero=res.getString("genero");
				int paginas=res.getInt("paginas");
			
				System.out.print("id:" + id );
				System.out.print(" | titulo: " + titulo);
				System.out.print(" | autor: "+ autor);
				System.out.print(" | genero: "+ genero);
				System.out.print(" | paginas: "+ paginas);
				System.out.println();
			
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
	}
	
	public void obtener_autor () {
		Statement stmt;
		try {
			stmt = obj.con.createStatement();
			String cadena = "SELECT * FROM libros WHERE autor='"+this.consulta+"'";
			ResultSet res= stmt.executeQuery(cadena);
			
			while (res.next()) {
				int id=res.getInt("id_libro");
				String titulo=res.getString("titulo");
				String autor=res.getString("autor");
				String genero=res.getString("genero");
				int paginas=res.getInt("paginas");
			
				System.out.print("id:" + id );
				System.out.print(" | titulo: " + titulo);
				System.out.print(" | autor: "+ autor);
				System.out.print(" | genero: "+ genero);
				System.out.print(" | paginas: "+ paginas);
				System.out.println();
			
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	
	public void obtener_genero () {
		Statement stmt;
		try {
			stmt = obj.con.createStatement();
			String cadena = "SELECT * FROM libros WHERE genero='"+this.consulta+"'";
			ResultSet res= stmt.executeQuery(cadena);
			while (res.next()) {
				int id=res.getInt("id_libro");
				String titulo=res.getString("titulo");
				String autor=res.getString("autor");
				String genero=res.getString("genero");
				int paginas=res.getInt("paginas");
			
				System.out.print("id:" + id );
				System.out.print(" | titulo: " + titulo);
				System.out.print(" | autor: "+ autor);
				System.out.print(" |  genero: "+ genero);
				System.out.print(" | paginas: "+ paginas);
				System.out.println();
			
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
	}
	
	
}
