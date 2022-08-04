<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Accident list</title>
</head>
<body>
<%--<c:forEach items="${accidents}" var="accident">
    <div>
        <span>
            <c:out value="${accident.name}"/>
        </span>
        <span>
             <a href="<c:url value='/update?id=${accident.id}'/>">Изменить наименование</a>
        </span>
    </div>
</c:forEach>
<br>--%>
<a href="<c:url value='/create'/>">
    <input type="submit" value="Добавить инцидент"/>
</a>
<form action="<c:url value='/edit'/>" method='GET'>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Наименование</th>
            <th scope="col">Описание</th>
            <th scope="col">Адрес</th>
            <th scope="col">Тип инцидента</th>
            <th scope="col">Выбор</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${accidents}" var="accident">
            <tr>
                <td>${accident.getId()}</td>
                <td>${accident.getName()}</td>
                <td>${accident.getText()}</td>
                <td>${accident.getAddress()}</td>
                <td>${accident.getType().getName()}</td>
                <td><input type="radio" name="id" value="${accident.getId()}" required></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit" value="Редактировать инцидент"/>
</form>
</body>
</html>
