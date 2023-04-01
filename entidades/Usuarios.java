package entidades;
import java.sql.*;

import db.Conex;
public class Usuarios {
	private String correo;
	private String password;
	public boolean estado_log=false;
	public Conex obj;
	public Usuarios(String correo,String pass, Conex obj) {
		this.correo= correo;
		this.password= pass;
		this.obj= obj;
	}
	public String buscar_contrasenia() {
		String pas= "";
		Statement sta;
		ResultSet re;
		try {
			sta=obj.con.createStatement();
			String cadena= "SELECT pass_user FROM usuarios WHERE login_user='"+this.correo+"' AND pass_user='"+this.password+"'";
			re= sta.executeQuery(cadena);
			if(re.next()) {
				pas=re.getString("pass_user");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pas;
		
	}
	public boolean verificar_login() {
		if(this.buscar_contrasenia()!="") {
			return true;
		}
		else {
			System.out.println("Contrase�a o usuario incorrectos");
			return false;
		}
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

}
