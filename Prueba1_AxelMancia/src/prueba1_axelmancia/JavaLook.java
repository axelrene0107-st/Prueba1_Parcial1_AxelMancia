/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_axelmancia;
import java.util.Scanner;
/**
 *
 * @author axelr
 */
public class JavaLook {
    private static EmailAccount[] cuentas = new EmailAccount[100];
    private static EmailAccount cuentaActiva = null;
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        int opcion;
        
        do{
            if(cuentaActiva == null){
                opcion = menuInicial();
                switch(opcion){
                    case 1:
                        login();
                        break;
                        
                    case 2:
                        crearCuenta();
                        break;
                        
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                        
                    default:
                        System.out.println("Opcion invalida");
                }
            }else{
                opcion = menuPrincipal();
                switch (opcion) {
                    case 1:
                        cuentaActiva.mostrarInbox();
                        break;
                    case 2:
                        mandarCorreo();
                        break;
                    case 3:
                        leerCorreo();
                        break;
                    case 4:
                        cuentaActiva.eliminarCorreoLeido();
                        break;
                    case 5:
                        cerrarSesion();
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }             
            }
        }while(true);
    }
    
    public static int menuInicial(){
        System.out.println("\n===== MENÚ INICIAL =====");
        System.out.println("1. Login");
        System.out.println("2. Crear cuenta");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return leerEntero();
    }
    
    public static int menuPrincipal(){
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("Cuenta activa: " + cuentaActiva.getDireccion());
        System.out.println("1. Ver inbox");
        System.out.println("2. Mandar correo");
        System.out.println("3. Leer un correo");
        System.out.println("4. Limpiar inbox");
        System.out.println("5. Cerrar sesión");
        System.out.print("Seleccione una opción: ");
        return leerEntero();
    }
    
    public static void login(){
        System.out.print("Ingrese dirección de correo: ");
        String correo = sc.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = sc.nextLine();

        EmailAccount cuenta = buscarCuenta(correo);

        if (cuenta != null && cuenta.getContrasena().equals(password)) {
            cuentaActiva = cuenta;
            System.out.println("Login exitoso. Bienvenido/a " + cuentaActiva.getNombreCompleto());
        } else {
            System.out.println("Credenciales incorrectas.");
        }
    }
    
    public static void crearCuenta(){
        if (buscarEspacioDisponible() == -1) {
            System.out.println("No hay espacio para crear más cuentas.");
            return;
        }

        System.out.print("Ingrese dirección de correo: ");
        String correo = sc.nextLine();

        if (buscarCuenta(correo) != null) {
            System.out.println("Ya existe una cuenta con esa dirección.");
            return;
        }

        System.out.print("Ingrese nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese contraseña: ");
        String password = sc.nextLine();

        int posicion = buscarEspacioDisponible();
        cuentas[posicion] = new EmailAccount(correo, password, nombre);
        cuentaActiva = cuentas[posicion];

        System.out.println("Cuenta creada exitosamente.");
        System.out.println("Sesión iniciada como: " + cuentaActiva.getDireccion());
    }
    
    public static void mandarCorreo(){
        System.out.print("Ingrese dirección del destinatario: ");
        String destinatario = sc.nextLine();

        EmailAccount cuentaDestino = buscarCuenta(destinatario);

        if (cuentaDestino == null) {
            System.out.println("El destinatario no existe.");
            return;
        }

        System.out.print("Ingrese asunto: ");
        String asunto = sc.nextLine();

        System.out.print("Ingrese contenido: ");
        String contenido = sc.nextLine();

        Email correo = new Email(cuentaActiva.getDireccion(), asunto, contenido);

        boolean recibido = cuentaDestino.recibirCorreo(correo);

        if (recibido) {
            System.out.println("Correo enviado exitosamente.");
        } else {
            System.out.println("No se pudo enviar el correo. Inbox del destinatario lleno.");
        }
    }
    
    public static void leerCorreo() {
        System.out.print("Ingrese la posición del correo: ");
        int posicion = leerEntero();
        cuentaActiva.leerCorreo(posicion);
    }

    public static void cerrarSesion() {
        System.out.println("Sesión cerrada.");
        cuentaActiva = null;
    }

    public static EmailAccount buscarCuenta(String correo) {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] != null && cuentas[i].getDireccion().equalsIgnoreCase(correo)) {
                return cuentas[i];
            }
        }
        return null;
    }

    public static int buscarEspacioDisponible() {
        for (int i = 0; i < cuentas.length; i++) {
            if (cuentas[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int leerEntero() {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        int numero = sc.nextInt();
        sc.nextLine();
        return numero;
    }
}
