<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setBundle basename="property.language"/>

<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:forward page="controller?command=HOME_PAGE&page=HOME_PAGE&accountRole=guest"/>
</body>
</html>