<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setBundle basename="property.language"/>

<html>
<body>
<div class="header">
    <a class="logo">FoxCorp</a>
    <jsp:include page="locale.jsp"/>
    <div class="header_submit">
        <form action="controller" method="post">
            <input type="submit" name="signIn" value="<fmt:message key="button.signIn"/>"/>
            <input type="hidden" name="command" value="TRANSFER"/>
            <input type="hidden" name="page" value="SIGN_IN_PAGE">
        </form>
        <form action="controller" method="post">
            <input type="submit" name="signUp"value="<fmt:message key="button.signUp"/>"/>
            <input type="hidden" name="command" value="TRANSFER"/>
            <input type="hidden" name="page" value="SIGN_UP_PAGE"/>
        </form>
    </div>

</div>
</body>
</html>
