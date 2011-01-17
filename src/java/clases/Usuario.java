/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

/**
 *
 * @author usuario_local
 */
public class Usuario {
    private Rol rol;
    private String nombre;

    public Usuario(Rol rol, String nombre){
        this.rol=rol;
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /*public void setNombre(String nombre) {
        this.nombre = nombre;
    }*/

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String toString(){
        return nombre+"("+rol.toString()+")";
    }


}
