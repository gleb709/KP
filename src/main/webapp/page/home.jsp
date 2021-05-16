<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="userList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'ru_RU'}" scope="session"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home5.css">
</head>
<body>
<div class="mainWindow">
    <c:choose>
        <c:when test="${accountRole.equals('admin')}">
            <jsp:include page="header/admin.jsp"/>
        </c:when>
        <c:when test="${accountRole.equals('mainAdmin')}">
            <jsp:include page="header/mainAdmin.jsp"/>
        </c:when>
        <c:when test="${accountRole.equals('user')}">
            <jsp:include page="header/user.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="header/guest.jsp"/>
        </c:otherwise>
    </c:choose>
        <div class="tableWindow">
            <div class="tariffs">
                <tag:homePageTable/>
            </div>
            <div class="tableNavigation">
                <div class="navForm">
                    <label class="navLabel">Страница: ${currentTablePage}/${pageCount}</label>
                </div>
                <form class="navForm" action="controller" method="post">
                    <input class="nav_button" type="submit" name="PreviousPage" value="Назад"/>
                    <input type="hidden" name="command" value="CHANGE_TABLE_PAGE">
                    <input type="hidden" name="transfer" value="previousPage">
                </form>
                <form class="navForm" action="controller" method="post">
                    <input class="nav_button" type="submit" name="nextPage" value="Вперед"/>
                    <input type="hidden" name="command" value="CHANGE_TABLE_PAGE">
                    <input type="hidden" name="transfer" value="nextPage">
                </form>
            </div>
</div>

</body>
</html>
