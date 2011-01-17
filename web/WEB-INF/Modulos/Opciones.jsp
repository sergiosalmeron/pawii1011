<%-- 
    Document   : Opciones
    Created on : 24-nov-2010, 19:28:05
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

    <div id="headerleft">
        Hola &nbsp <%=session.getAttribute("usuario")%> &nbsp
    </div>
    <div id="menulinks">
        <form action="Inicio" method="post">
            <input type="hidden" name="salir" value="true" />
            <INPUT type="submit" value="Salir" />
        </form>
    </div>



