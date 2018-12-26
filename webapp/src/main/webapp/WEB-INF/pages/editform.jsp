<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Андрій
  Date: 25.12.2018
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/handledit/${part.id}">
    <p> <label>Название:<input type="text" name="datailname" value=${part.compName}></label></p>
    <p> <label>Производитель:<input type="text" name="manufactor" value=${part.manuf}></label></p>
    <p>
        <label> Необходимость:
            <select name="ness">
                <c:if test="${part.ness}">
                <option selected>true</option>
                <option>false</option>
                </c:if>
                <c:if test="${!part.ness}">
                <option>true</option>
                <option selected>false</option>
                </c:if>
            </select>
        </label>
    </p>
    <p> <label>Колличество:<input type="text" name="count" value=${part.quant}></label></p>
    <input type="submit" value="Изменить">
</form>
</body>
</html>
