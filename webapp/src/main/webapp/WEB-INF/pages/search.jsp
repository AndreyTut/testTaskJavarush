<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Андрій
  Date: 24.12.2018
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search result</title>
</head>
<body>
<h1>Результаты поиска:</h1>
<c:if test="${details.size()>0}">
    <table border="5" bgcolor="aqua">
        <tr>
            <th>Наименование детали</th>
            <th>Производитель</th>
            <th>Необходимость для сборки</th>
            <th>Колличество на складе</th>
        </tr>
    <c:forEach items= "${details}" var="part">
        <tr>
            <td><c:out value="${part.compName}"/></td>
            <td><c:out value="${part.manuf}"/></td>
            <td><c:out value="${part.ness}"/></td>
            <td><c:out value="${part.quant}"/></td>
        </tr>
    </c:forEach>
    </table>

</c:if>
<c:if test="${details.size()==0}">
    <p>Поиск не дал результатов</p>
</c:if>
</body>
</html>
