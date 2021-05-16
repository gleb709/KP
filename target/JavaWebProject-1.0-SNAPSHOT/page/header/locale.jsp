<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru'}" scope="session"/>
<fmt:setBundle basename="property.language"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/locale2.css">
<html>
<body>
<details class="method-in-group">
    <summary class="summary">
        <fmt:message key="button.language"/>
    </summary>
    <div>
        <form class="formButton" action="controller" method="post">
            <input class="active_button" type="submit" value="<fmt:message key="button.language_ru"/>"/>
            <input type="hidden" name="command" value="SWITCH_LOCALE">
            <input type="hidden" name="locale" value="ru">
        </form>
        <form class="formButton" action="controller" method="post">
            <input class="active_button" type="submit" value="<fmt:message key="button.language_en"/>">
            <input type="hidden" name="command" value="SWITCH_LOCALE">
            <input type="hidden" name="locale" value="en">
        </form>
    </div>
</details>
</body>
</html>
