<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <input type="hidden" name="id" value="${accident.getId()}">
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${accident.getName()}"></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><input type='text' name='text' value="${accident.getText()}"></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="${accident.getAddress()}"></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <c:set var="typeId" value="${accident.getType().getId()}"/>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <c:choose>
                            <c:when test="${type.id == typeId}">
                                <option selected value="${type.id}">${type.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${type.id}">${type.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <c:set var="accRules" value="${accident.getRules()}"/>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <c:choose>
                            <c:when test="${fn:contains(accRules, rule)}">
                                <option selected value="${rule.id}">${rule.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${rule.id}">${rule.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <input name="submit" type="submit" value="Сохранить"/>
</form>
</body>
</html>
