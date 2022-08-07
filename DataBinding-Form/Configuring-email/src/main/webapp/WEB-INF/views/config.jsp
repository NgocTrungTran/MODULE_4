<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<h3>Config Email</h3>
<form:form action="settings-info" method="post" modelAttribute="config">
  <fieldset>
    <legend>Settings</legend>
    <table>
      <tr>
        <td><form:label path="languages">Languages:</form:label></td>
        <td><form:select path="languages" items="${languages}" /></td>
      </tr>
      <tr>
        <td><form:label path="pageSize">Page Size:</form:label></td>
        <td>Show <form:select path="pageSize" items="${pageSize}"/> emails per page</td>
      </tr>
      <tr>
        <td><form:label path="spamsFilter">Spams filler:</form:label></td>
        <td><form:checkbox path="spamsFilter" value="Enable spams filter"/> Enable spams filter</td>
      </tr>
      <tr>
        <td><form:label path="signature">Signature:</form:label></td>
        <td><form:textarea path="signature"/></td>
      </tr>
      <tr>
        <td></td>
        <td><button type="submit">Update</button> <button type="reset">Cancel</button></td>
      </tr>
    </table>
  </fieldset>
</form:form>
</body>
</html>