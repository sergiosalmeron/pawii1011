<%-- 
    Document   : CreadorUsuarios
    Created on : 27-nov-2010, 2:15:03
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<h2>Crear usuario:</h2>
<FORM action="FuncionesUsuarios" method="post">
    Nombre de usuario: <INPUT type="text" name="nuevoUsuario" value="Escriba aquí el nombre del usuario"> <br />
    Rol:  <select name="nuevoRol" onchange="salta(this.form)">
                    <option value="Invitado">Invitado
                    <option value="Autorizado">Autorizado
                    <option value="Administrador">Administrador
                </select><br />
    <input type="submit" value="Crear">
</FORM>
<br />

<h2>Eliminar usuario:</h2>
<FORM action="FuncionesUsuarios" method="post">
    Nombre de usuario: <INPUT type="text" name="viejoUsuario" value="Escriba aquí el nombre del usuario"><br />
     <input type="submit" value="Borrar">
</FORM>

