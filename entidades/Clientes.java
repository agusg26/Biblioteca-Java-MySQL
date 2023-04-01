package entidades;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import db.Conex;
public class Clientes {
	private int id;
	private String nombre;
	private String apellido;
	private String tipo;
	private String correo;
	private String password;
	public Conex obj;
	
	public Clientes() {
		
	}
	
	public Clientes (Conex obj) {
		this.obj= obj;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
		Statement sta;
		ResultSet re;
		try {
			sta=obj.con.createStatement();
			String cadena="UPDATE usuarios SET login_user='"+correo+"' WHERE idusuarios="+this.id;
			sta.execute(cadena);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		Statement sta;
		ResultSet re;
		try {
			sta=obj.con.createStatement();
			String cadena="UPDATE usuarios SET pass_user='"+password+"' WHERE idusuarios="+this.id;
			sta.execute(cadena);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void datos(String correo) {
		Statement sta;
		ResultSet res;
		try {
			sta= obj.con.createStatement();
			String cadena="SELECT * FROM usuarios WHERE login_user='"+correo+"'";
			res=sta.executeQuery(cadena);
			while(res.next()) {
				this.id=res.getInt("idusuarios");
				this.nombre= res.getString("nombre");
				this.apellido= res.getString("apellido");
				this.tipo= res.getString("tipo_user");
				this.correo=res.getString("login_user");
				this.password= res.getString("pass_user");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean verificar_adm() {
		String var="";
		Statement sta;
		ResultSet res;
		try {
			sta=obj.con.createStatement();
			String cadena= "SELECT tipo_user FROM usuarios WHERE idusuarios="+this.id+" AND tipo_user='1'";
			res= sta.executeQuery(cadena);
			while(res.next()) {
				var=res.getString("tipo_user");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(var!="") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean verificar_socio() {
		String var="";
		Statement sta;
		ResultSet res;
		try {
			sta=obj.con.createStatement();
			String cadena= "SELECT tipo_user FROM usuarios WHERE idusuarios="+this.id;
			res= sta.executeQuery(cadena);
			while(res.next()) {
				var=res.getString("tipo_user");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(var!="") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean condDesc(String titulo) {
		Statement sta;
		ResultSet res;
		int var= 0;
		try {
			sta=obj.con.createStatement();
			String cadena= "SELECT cant_desc FROM descargas WHERE MONTH(CURDATE()) = MONTH(fecha) AND id_usuarios="+id+" AND titulo='"+titulo+"'";
			res= sta.executeQuery(cadena);
			while(res.next()) {
				var=res.getInt("cant_desc");
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(var==0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void descargarLibro(String titulo) {
		int var1=0;
		int var2=0;
		Statement sta;
		ResultSet res;
		Statement sta1;
		ResultSet res1;
		try {
			sta=obj.con.createStatement();
			String cadena= "SELECT id_libro FROM libros WHERE titulo='"+titulo+"'";
			res= sta.executeQuery(cadena);
			while(res.next()) {
				var1=res.getInt("id_libro");
				sta1=obj.con.createStatement();
				String cadena2="SELECT cant_desc FROM descargas WHERE id_libro="+var1+" AND id_usuarios="+this.id;
				res1=sta1.executeQuery(cadena2);
				while(res1.next()) {
					var2=res1.getInt("cant_desc");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat sdf= new SimpleDateFormat("YYYY/MM/dd");
		Date hoy= new Date();
		 String fecha=sdf.format(hoy);
		if(var2==1 || this.condDesc(titulo)) {
			try {
				sta=obj.con.createStatement();
				String cadena="UPDATE descargas SET fecha='"+fecha+"', cant_desc=cant_desc+1 WHERE id_usuarios="+this.id+" AND id_libro="+var1;
				sta.execute(cadena);
				System.out.println("Libro descargado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(!(this.condDesc(titulo))) {
			System.out.println("Usted alcanzo el limite de descargas de este libro por mes");
		}
		if(var2==0) {
			try {
				sta=obj.con.createStatement();
				String cadena="INSERT INTO descargas (id_usuarios,id_libro,fecha,cant_desc) VALUES ("+this.id+","+var1+",'"+fecha+"',1)";
				sta.execute(cadena);
				System.out.println("Libro descargado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void historial() {
		Statement sta;
		ResultSet re;
		try {
			sta=obj.con.createStatement();
			String cadena="SELECT L.titulo,D.fecha,D.cant_desc FROM usuarios AS U INNER JOIN descargas AS D ON U.idusuarios=D.id_usuarios INNER JOIN libros AS l ON L.id_libro=D.id_libro WHERE D.id_usuarios="+id;
			re=sta.executeQuery(cadena);
			while(re.next()) {
				String titulo=re.getString("titulo");
				String fecha= re.getString("fecha");
				int desc= re.getInt("cant_desc");
				System.out.print("Titulo: "+titulo);
				System.out.print(", ultima fecha: "+fecha);
				System.out.print(", cantidad descargas: "+desc);
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dar_baja(int id) {
		Statement sta;
		try {
			sta=obj.con.createStatement();
			String cadena= "UPDATE usuarios SET tipo_user="+null+" WHERE (idusuarios="+id+")";
			sta.execute(cadena);
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void hacer_socio(int id) {
		Statement sta;
		try {
			sta=obj.con.createStatement();
			String cadena= "UPDATE usuarios SET tipo_user= 2 WHERE (idusuarios="+id+")";
			sta.execute(cadena);
			
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		public void pagarCuota() {
			int id=0;
			Statement sta;
			ResultSet re;
			try {
				sta=obj.con.createStatement();
				String cadena= "SELECT id_cuota FROM usuarios WHERE idusuarios="+this.getId();
				re=sta.executeQuery(cadena);
				while(re.next()) {
					id=re.getInt("id_cuota");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SimpleDateFormat sdf= new SimpleDateFormat("YYYY/MM/dd");
			Date hoy= new Date();
			 String fecha=sdf.format(hoy);
			if(id==0) {
				Statement sta1;
				try {
					sta1=obj.con.createStatement();
					String cadena2="INSERT INTO cuota (estado_cuota,fecha_pago) VALUES (1,'"+fecha+"')";
					sta1.execute(cadena2);
					this.hacer_socio(this.getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			if(id!=0){
				Statement sta1;
				try {
					sta1=obj.con.createStatement();
					String cadena2="UPDATE cuota SET fecha_pago='"+fecha+"' WHERE idcuota="+id;
					sta1.execute(cadena2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

	}
}

