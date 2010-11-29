/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author usuario_local
 */
public class Mensaje {

    private Usuario autor;
    private String texto;
    private Date fecha;
    private SimpleDateFormat formato;

    public Mensaje(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.fecha = new Date();
        formato=new SimpleDateFormat("dd-MM-yyyy");
    }

    public Usuario getAutor() {
        return autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public String dameFecha(){
        return formato.format(fecha);
    }

    public String getTexto() {
        return texto;
    }

    


}
