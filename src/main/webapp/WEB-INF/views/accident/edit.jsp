<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident edit</title>
</head>
<body>
<form action="<c:url value='/modify'/>" method='POST'>
    <br>
    <table>
        <tr>
            <input type="hidden" name="id" value="${accident.getId()}">
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.getName()}"></td>
            <td>Текст:</td>
            <td><input type='text' name='text' value="${accident.getText()}"></td>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="${accident.getAddress()}"></td>
        </tr>
    </table>
    <br>
    <input name="submit" type="submit" value="Сохранить"/>
</form>
</body>
</html>
