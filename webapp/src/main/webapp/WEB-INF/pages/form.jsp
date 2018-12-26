<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="partmanager.model.Part" %><%--
  Created by IntelliJ IDEA.
  User: Андрій
  Date: 21.12.2018
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
<style>
    table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
    }
</style>
</head>
<body>

<h1 style="text-align: center">${title}</h1>

<table border="5" bgcolor="yellow">
    <tr>
        <th>Наименование детали</th>
        <th>Производитель</th>
        <th>Необходимость для сборки</th>
        <th>Колличество на складе</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    <c:forEach var="part" items="${parts}">
        <tr>
            <td><c:out value="${part.compName}"/></td>
            <td><c:out value="${part.manuf}"/></td>
            <td><c:out value="${part.ness}"/></td>
            <td><c:out value="${part.quant}"/></td>
            <td><a href="${pageContext.request.contextPath}/edit/${part.id}">edit</a></td>
            <td><a href="${pageContext.request.contextPath}/delete/${part.id}">delete</a></td>
        </tr>
    </c:forEach>
</table>
<p>Можно собрать  компьютеров: ${number}</p>
<table>
    <tr valign="top">
        <td>
<form name="paging">
<button name="paging" value="previous" type="submit">Пред.стр.</button>
    <button name="paging" value="next" type="submit">След. стр.</button>
</form>
        </td>
        <td>
<form action="${pageContext.request.contextPath}/add">
    <button>Добавить деталь на склад</button>
</form>
        </td>
        <td>
<form>
    <p>Фильтр:</p>
    <p><label><input type="radio" name="sort" value="full">Все детали</label></p>
    <p><label><input type="radio" name="sort" value="ness">Необходимые детали</label></p>
    <p><label><input type="radio" name="sort" value="unness">Опционные детали</label></p>
    <p><input type="submit" value="Выбрать"></p>
</form>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/search">
            <p><label><input type="text" name="detailname" value="Название детали"></label></p>
                <p><input type="submit" value="Искать"></p>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
