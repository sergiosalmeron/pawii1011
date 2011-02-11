<%-- 
    Document   : Opciones
    Created on : 05-feb-2011, 12:01:56
    Author     : sergio
--%>


<%@page import="utilesInterfaz.DetectorMovil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

    <div id="headerleft">
        Hola &nbsp <%=session.getAttribute("usuario")%> &nbsp
    </div>
    <div id="menulinks">
        <%if (!DetectorMovil.esMovil(request)){%>
                <form action="Inicio" method="post">
                    <select name="css" onchange="this.form.submit()">
                        <option value="0">estilos</option>
                        <option value="1">Estilo 1</option>
                        <option value="2">Estilo 2</option>
                        <option value="3">Estilo 3</option>
                    </select>
                </form>
        <%}
        %>
        <form action="Inicio" method="post">
            <input type="hidden" name="salir" value="true" />
            <INPUT type="submit" value="Salir" />
        </form>
    </div>
