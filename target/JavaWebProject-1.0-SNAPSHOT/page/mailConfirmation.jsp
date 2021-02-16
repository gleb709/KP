<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <title>Confirmation</title>
</head>
<body>
    <jsp:include page="header/locale.jsp"/>
    <form action="controller" method="post">
        <input type="text" name="emailKey" placeholder="<fmt:message key="label.emailKey"/>"/>
        <c:if test="${not empty emailKey && emailKey.equals('invalid')}">
            <fmt:message key="label.incorrectKey"/>
        </c:if>
        <input type="submit" value="<fmt:message key="button.registrationConfirmation"/>"/>
        <input type="hidden" name="command" value="REGISTRATION_CONFIRMATION"/>
    </form>
</body>
</html>
