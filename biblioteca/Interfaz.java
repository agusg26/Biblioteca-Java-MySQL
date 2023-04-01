package biblioteca;
import java.sql.SQLException;
import java.util.Scanner;

import db.Conex;
import entidades.Clientes;
import entidades.Libros;
import entidades.Registro;
import entidades.Usuarios;

public class Interfaz {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Conex base= new Conex();
		base.conect();
		int opcion= 0;
		Scanner scan= new Scanner(System.in);
		while((opcion!=1)&&(opcion!=2)) {
			System.out.println("1. Iniciar Sesion\n"
					+ "2. Registrarse\n");
			opcion= scan.nextInt();
		}

		if(opcion==2) {
			int ba=0;
			while(ba==0) {
				Scanner teclado= new Scanner(System.in);
				Scanner teclado2= new Scanner(System.in);
				Scanner teclado3= new Scanner(System.in);
				Scanner teclado4= new Scanner(System.in);
				Scanner teclado5= new Scanner(System.in);
				System.out.println("Ingresar correo");
				String correo= teclado.next();
				System.out.println("Ingresar nombre");
				String nombre= teclado2.next();
				System.out.println("Crear una contrase�a");
				String contra= teclado3.next();
				System.out.println("Ingresar apellido");
				String apellido= teclado4.next();
				System.out.println("Ingresar dni");
				int dni= teclado5.nextInt();
				Registro nuevo= new Registro(correo,nombre,contra,apellido,dni,base);
				if(nuevo.verificar_Correo()) {
					System.out.println("Correo valido");
					nuevo.registrar_Correo();
					ba=1;
				}
				else {
					System.out.println("Correo ya existente");
				}
			}
		}
		int ba=0;
		while(ba==0) {
			String nom;
			String pas;
			Scanner teclado= new Scanner(System.in);
			System.out.println("Ingresar correo");
			nom=teclado.next();
			Scanner teclado2= new Scanner(System.in);
			System.out.println("Ingresar contrase�a");
			pas= teclado2.next();
			Usuarios us= new Usuarios(nom,pas,base);
			if(us.verificar_login()) {
				us.estado_log=true;
				ba=1;
				Clientes usuario= new Clientes(base);
				usuario.datos(nom);
				System.out.println("Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
				do {
					try {
						System.out.println("|MENU DE OPCIONES|\n"
								+ "---------------------\n"
								+"1.Buscar Libros\n"
								+"2.Descargar Libros\n"
								+"3.Historial de Descargas\n"
								+"4.Cambiar Correo\n"
								+"5.Cambiar Contrase�a\n"
								+"6.Pagar Cuota\n"
								+"7.Hacer Socio(solo admin)\n"
								+"8.Dar de baja(solo admin)\n"
								+"9.Salir\n");
						Scanner tecld3=new Scanner(System.in);
						System.out.print("Ingrese valor: ");
						opcion=tecld3.nextInt();
						switch(opcion) {
						case 1:
							System.out.println("Buscar libro\n"
									+ "1.Por titulo\n"
									+ "2.Por autor\n"
									+ "3.Por genero");
							Scanner tecld4= new Scanner(System.in);
							int opcion2= tecld4.nextInt();
							if(opcion2==1) {
								System.out.println("Ingrese el nombre del titulo:");
								Scanner tecld51= new Scanner(System.in);
								String cons= tecld51.nextLine();
								Libros lib= new Libros(cons,base);
								lib.obtener_titulo();
							}
							if(opcion2==2) {
								System.out.println("Ingrese el nombre del autor:");
								Scanner tecld52= new Scanner(System.in);
								String cons= tecld52.nextLine();
								Libros lib= new Libros(cons,base);
								lib.obtener_autor();
							}
							if(opcion2==3) {
								System.out.println("Ingrese el nombre del genero:");
								Scanner tecld53= new Scanner(System.in);
								String cons= tecld53.nextLine();
								Libros lib= new Libros(cons,base);
								lib.obtener_genero();
							}
							break;
						case 2:
							if(usuario.verificar_socio()) {
								System.out.println("Ingrese el titulo del libro: ");
								Scanner tecld50= new Scanner(System.in);
								String tit=tecld50.nextLine();
								usuario.descargarLibro(tit);
								
							}
							else {
								System.out.println("Usted no es socio de la biblioteca");
							}

							break;
						case 3:
							usuario.historial();

							break;
						case 4:
							System.out.println("Ingrese su nuevo correo: ");
							Scanner tcld54= new Scanner(System.in);
							String nwnom=tcld54.nextLine();
							usuario.setCorreo(nwnom);

							break;
						case 5:
							System.out.println("Ingrese su contrase�a anterior:");
							Scanner tecld55= new Scanner(System.in);
							String pass=tecld55.nextLine();
							if(pass==usuario.getPassword()) {
								System.out.println("Ingrese su nueva contrase�a: ");
								Scanner tcld6=new Scanner(System.in);
								String nwpas= tcld6.nextLine();
								usuario.setPassword(nwpas);
							}
							else {
								System.out.println("Contrase�a incorrecta");
							}

							break;
						case 6:
							usuario.pagarCuota();

							break;
						case 7:
							if(usuario.verificar_adm()) {
								System.out.println("Ingrese el id del usuario");
								Scanner teccld56= new Scanner(System.in);
								int num=teccld56.nextInt();
								usuario.hacer_socio(num);
							}
							else {
								System.out.println("Usted no es administrador");
							}

							break;
						case 8:
							if(usuario.verificar_adm()) {
								System.out.println("Ingrese el id del usuario");
								Scanner teccld57= new Scanner(System.in);
								int num=teccld57.nextInt();
								usuario.dar_baja(num);
							}
							else {
								System.out.println("Usted no es administrador");
							}
						}
					}catch(NumberFormatException e) {
						
					}	
				}while(opcion!=9);
			}
			else {
				System.out.println("Volver a intentar");
			
			}
		}
	}
}

