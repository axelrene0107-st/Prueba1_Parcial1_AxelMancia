/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_axelmancia;

/**
 *
 * @author axelr
 */
public class EmailAccount {
    private String direccion;
    private String contrasena;
    private String nombreCompleto;
    private Email[] inbox;
    
    int sinLeer;
    int recibidos;

    public EmailAccount(String direccion, String contrasena, String nombreCompleto) {
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.inbox = new Email[100];
    }

    public String getDireccion() {
        return direccion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    
    public boolean recibirCorreo(Email correo){
        for (int i = 0; i < inbox.length; i++) {
            if(inbox[i]== null){
                inbox[i]=correo;
                return true;
            }
        }
        return false;
    }
    
    public void mostrarInbox(){
        System.out.println("------------INBOX------------");
        System.out.println("Cuenta: "+direccion);
        System.out.println("Nombre: "+nombreCompleto);
        System.out.println("---------------------------------");
        
        int recibidos =0;
        int sinLeer = 0;
        
        for (int i = 0; i < inbox.length; i++) {
            if(inbox[i]!=null){
                recibidos++;
                System.out.println((i+1)+"-"+inbox[i].getEmisor()+"-"+inbox[i].getAsunto()+"-"+inbox[i].getEstado());
                if(!inbox[i].isLeido()){
                    sinLeer++;
                }
            }
        }
        System.out.println("-----------------------------");
        System.out.println("Sin leer: "+sinLeer);
        System.out.println("Recibidos: "+inbox.length);
    }
        
    public void leerCorreo(int posicion){
        if(posicion>=0 && posicion< inbox.length && inbox[posicion]!=null){
            inbox[posicion].imprimir();
            inbox[posicion].marcarLeido();
        }else{
            System.out.println("Correo no existe!");
        }
    }
    
    public void eliminarCorreoLeido(){
        for (int i = 0; i < inbox.length; i++) {
            if(inbox[i]!=null && inbox[i].isLeido()){
                inbox[i]=null;
            }
        }
        System.out.println("Se eliminaron los correos leidos.");
    }
    
}
