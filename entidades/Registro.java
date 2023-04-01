package entidades;
import java.sql.*;

import db.Conex;
public class Registro  {
	private String correo;
	private String password;
	private String nombre;
	private String apellido;
	public Conex obj;
	private int dni;
	
	public Registro(String correo, String name, String pass, String surn, int dni, Conex obj) {
		this.correo= correo;
		this.password= pass;
		this.nombre= name;
		this.apellido= surn;
		this.dni= dni;
		this.obj= obj;
		
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	public String buscar_Correo() throws ClassNotFoundException, SQLException {
		String ba="";
		try {
			Statement stmt= obj.con.createStatement();
			String sql= "SELECT login_user FROM usuarios WHERE login_user='"+this.correo+"'";
			ResultSet st= stmt.executeQuery(sql);
			if(st.next()) {
				ba=st.getString("login_user");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return ba;
		
	}
	public boolean verificar_Correo() throws ClassNotFoundException, SQLException {
		if(this.buscar_Correo()!="") {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean registrar_Correo() {
		Statement sta;
		try {
			sta=obj.con.createStatement();
			String cadena="INSERT INTO usuarios (nombre,apellido,login_user,pass_user) VALUES ('"+this.nombre+"','"+this.apellido+"','"+this.correo+"','"+this.password+"')";
			sta.execute(cadena);
			System.out.println("Inicie Sesion con sus datos registrados");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
