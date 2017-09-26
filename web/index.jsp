<%-- 
    Document   : index.jsp
    Created on : 24-08-2016, 19:42:42
    Author     : cetecom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Sitio Virtual</title>
  <link rel="shortcut icon" href="imagenes/icono.png">
  <link rel="stylesheet" href="misestilos.css">

</head>

<body>
<%String nombre= (String) session.getAttribute("nombreUsuario");
if (nombre != null)
    out.println("<p align='right'><font color='green'>USUARIO CONECTADO: "+nombre+"</font></p>");
else
    out.println("Debe Loguearse en el Sistema");
%>

<!-- Menú de navegación del sitio -->
<ul class="navbar">
    <li><a href="login.jsp">Ingreso Usuario</a>
    <li><a href="productos.jsp">Ingreso Productos</a>
    <li><a href="muestra_productos.jsp">Muestra Productos</a>
    <li><a href="salir.jsp">Salida del Sistema</a>
</ul>
<!-- Contenido principal -->
<h1>Tienda Virtual</h1>

<p>¡Bienvenido a nuestra tienda virtual!

<p>Aquí encontrará lo que usted está buscando.
<br>También le informamos que tenemos importantes promociones que no lo dejarán indiferente...

<p>Estamos a su disposición

<!-- Este es un comentario en HTML -->
<address>Modificada el 23 de septiembre de 2017<br></address>
</body>
</html>