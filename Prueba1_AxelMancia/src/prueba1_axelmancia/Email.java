/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba1_axelmancia;

/**
 *
 * @author axelr
 */
public class Email {
    private String emisor;
    private String asunto;
    private String contenido;
    private boolean leido;

    public Email(String emisor, String asunto, String contenido) {
        this.emisor = emisor;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public boolean isLeido() {
        return leido;
    }
    
    public String getEstado(){
        if(leido)
           return "Leido";
        else
            return "Sin leer";
    }
    
    public void marcarLeido(){
        leido = true;
    }
    
    public void imprimir(){
        System.out.println("DE: <"+emisor+">"
                + "\nASUNTO: <"+asunto+">"
                + "\n<"+contenido+">");
    }
    
}
