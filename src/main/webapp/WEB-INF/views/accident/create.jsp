<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident create</title>
</head>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <br>
    <table>
        <input type="hidden" name="id" value="0">
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' required></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><input type='text' name='text' required></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' required></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input name="submit" type="submit" value="Сохранить"/>
</form>
</body>
</html>
