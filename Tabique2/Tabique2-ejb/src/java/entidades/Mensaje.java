/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author usuario_local
 */
@Entity
public class Mensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Usuario autor;
    private String texto;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private SimpleDateFormat formato;

    public Mensaje() {
    }





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

    public String getTexto() {
        return texto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mensaje[id=" + id + "]";
    }

    public String dameFecha(){
        return formato.format(fecha);
    }


}
