<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/signIn.css">
    <title>SignIn</title>
</head>
<body>

    <jsp:include page="header/locale.jsp"/>
<div class="signInBox">
    <form action="controller" method="post">
        <label><fmt:message key="label.login"/></label>
        <input type="text" name="login" placeholder= "<fmt:message key="helpMessage.enterLogin"/>"/>
        <label><fmt:message key="label.password"/></label>
        <input type="password" name="password" placeholder="<fmt:message key="helpMessage.enterPassword"/>"/>
        <c:if test="${not empty incorrectData}">
            <label><fmt:message key="label.signInFail"/> </label>
        </c:if>
        <input type="submit" name="SignIn" value="<fmt:message key="button.signIn"/>"/>
        <input type="hidden" name="command" value="SIGN_IN">
        <c:set var="postTime" value="<%= (new java.util.Date()).getTime()%>" scope="page"/>
    </form>
</div>
</body>
</html>
