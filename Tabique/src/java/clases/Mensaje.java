/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.Date;

/**
 *
 * @author usuario_local
 */
public class Mensaje {

    private Usuario autor;
    private String texto;
    private Date fecha;

    public Mensaje(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.fecha = new Date();
    }

    public Usuario getAutor() {
        return autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getTexto() {
        return texto;
    }

    


}
