<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setBundle basename="property.language"/>
<html>
<body>
<details>
    <summary>
        <fmt:message key="button.language"/>
    </summary>
    <form action="controller" method="post">
        <input type="submit" value="<fmt:message key="button.language_ru"/>"/>
        <input type="hidden" name="command" value="SWITCH_LOCALE">
        <input type="hidden" name="locale" value="ru_RU">
    </form>
    <form action="controller" method="post">
        <input type="submit" value="<fmt:message key="button.language_en"/>">
        <input type="hidden" name="command" value="SWITCH_LOCALE">
        <input type="hidden" name="locale" value="en_EN">
    </form>
</details>
</body>
</html>
