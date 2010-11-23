/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public class Usuarios {

    private static Usuarios insUsuarios = null;
    private ArrayList<Usuario> usuarios;
    private static String monitor="Monitor";

    private static void usuariosPrueba() {
        insUsuarios.addUsuario(Rol.Invitado, "Aleix");
        insUsuarios.addUsuario(Rol.Autorizado, "Sergio");
        insUsuarios.addUsuario(Rol.Administrador, "Dios");
    }

    private Usuarios(){
        usuarios=new ArrayList<Usuario>();
    }

    public static Usuarios getInstance() {

        synchronized (monitor) {
            if (insUsuarios == null) {
                insUsuarios = new Usuarios();
                usuariosPrueba();
            }
        }

        return insUsuarios;
    }

    public boolean addUsuario(Rol rol, String nombre){
        if (nombre.length()<1)
            return false;

        for (int i=0; i<usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre))
                return false;
        }

        Usuario u=new Usuario(rol,nombre);
        usuarios.add(u);
        return true;
    }

    public boolean deleteUsuario(String nombre){
        for (int i=0; i<usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre)) {
                usuarios.remove(i);
                return true;
            }

        }
        return false;
    }


    public Rol getRolUsuario(int numero){
        if ((numero>=0)&&(numero<usuarios.size()))
                return usuarios.get(numero).getRol();
        else
            return null;

    }


    public String getNombreUsuario(int numero){
        if ((numero>=0)&&(numero<usuarios.size()))
                return usuarios.get(numero).getNombre();
        else
            return null;

    }

    public boolean setRolUsuario(int numero, Rol rol){
        if ((numero>=0)&&(numero<usuarios.size())){
                usuarios.get(numero).setRol(rol);
                return true;
        }
        else
            return false;

    }

    public boolean setRolUsuario(String nombre, Rol rol){
        for (int i=0; i<usuarios.size(); i++){
            if (usuarios.get(i).getNombre().equals(nombre)) {
                return setRolUsuario(i, rol);
            }
        }
        return false;

    }

    public int getNumUsuarios(){
        return usuarios.size();
    }



}
