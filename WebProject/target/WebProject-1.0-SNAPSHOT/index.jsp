<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculadora 2do Proyecto</title>
</head>
<body>
<h1><%= "Calculadora de Operaciones Aritméticas Múltiples" %></h1>
<a href="DatosProgramadores">Datos Programadores</a>
<br><br>
<form action="Calcular" method="get">
    Expression: <input type="text" name="expression">
    <br><br>

    Username: <input type="text" name="username">
    <br><br>

    <input type="submit" value="Calcular expresión">
</form>
<br><br>
<br><br>
<form action="historialExpresiones" method="get">
    Username: <input type="text" name="username">
    <br><br>

    <input type="submit" value="Historial">
</form>
</body>
</html>