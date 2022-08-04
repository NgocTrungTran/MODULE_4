<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Condiment</title>
</head>
<body>
<form action="/save" method="post">
    <input type="checkbox" id="lettuce" name="condiment" value="Lettuce">
    <label for="lettuce">Lettuce</label><br>
    <input type="checkbox" id="tomato" name="condiment" value="Tomato">
    <label for="tomato">Tomato</label><br>
    <input type="checkbox" id="mustard" name="condiment" value="Mustard">
    <label for="mustard">Mustard</label><br>
    <input type="checkbox" id="sprouts" name="condiment" value="Sprouts">
    <label for="sprouts">Sprouts</label><br><hr>
    <input type="submit" value="Save">
</form>
<h4>Your choose:</h4>
<p>
    <c:forEach items="${condiment}" var="c">
        <c:out value="${c}"/><br>
    </c:forEach>
</p>
</body>
</html>
