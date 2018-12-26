<%--
  Created by IntelliJ IDEA.
  User: Андрій
  Date: 23.12.2018
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding new Part</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/handleadd">
    <p> <label><input type="text" name="datailname" value="Название детали"></label></p>
    <p> <label><input type="text" name="manufactor" value="Производитель"></label></p>
    <p>
        <label> Необходимость:
        <select name="ness">
            <option>true</option>
            <option>false</option>
        </select>
        </label>
    </p>
    <p> <label><input type="text" name="count" value="Колличество на складе"></label></p>
    <input type="submit" value="Добавить деталь">
</form>
</body>
</html>
