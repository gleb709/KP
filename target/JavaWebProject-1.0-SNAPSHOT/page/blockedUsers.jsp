<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tag" uri="userList" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="property.language"/>

<html>
<head>
    <link rel="stylesheet" href="css/userList3.css">
    <title>User menu</title>
</head>
<body>
<c:choose>
    <c:when test="${accountRole.equals('mainAdmin')}">
        <jsp:include page="header/mainAdmin.jsp"/>
    </c:when>
    <c:when test="${accountRole.equals('admin')}">
        <jsp:include page="header/admin.jsp"/>
    </c:when>
</c:choose>
<div class="userInfoBox">
    <p class="info"><fmt:message key="button.account"/></p>
    <hr/>
    <div class="box">
        <jsp:include page="navigation.jsp"/>
        <div class="tableWindow">
            <div>
                <tag:blockedUserTable/>
            </div>
            <div class="tableNavigation">
                <div class="navForm">
                    <label class="tableLabel"><fmt:message key="label.page"/>${currentTablePage}/${pageCount}</label>
                </div>
                <form class="navForm" action="controller" method="post">
                    <input class="nav_button" type="submit" name="PreviousPage" value="<fmt:message key="button.previousPage"/>"/>
                    <input type="hidden" name="command" value="CHANGE_TABLE_PAGE">
                    <input type="hidden" name="transfer" value="previousPage">
                </form>
                <form class="navForm" action="controller" method="post">
                    <input class="nav_button" type="submit" name="nextPage" value="<fmt:message key="button.nextPage"/>"/>
                    <input type="hidden" name="command" value="CHANGE_TABLE_PAGE">
                    <input type="hidden" name="transfer" value="nextPage">
                </form>
            </div>
            <div class="actionBox">
                <div>
                    <form class="actionForm" action="controller" method="post">
                        <input class="listButton" type="submit" name="users" value="<fmt:message key="button.allUsers"/>"/>
                        <input type="hidden" name="command" value="TRANSFER"/>
                        <input type="hidden" name="page" value="USER_LIST"/>
                    </form>
                    <form class="actionForm" action="controller" method="post">
                        <input class="listButton" type="submit" name="debtors" value="<fmt:message key="button.debtors"/>"/>
                        <input type="hidden" name="command" value="TRANSFER"/>
                        <input type="hidden" name="page" value="DEBTORS_LIST"/>
                    </form>
                </div>
                <div>
                    <div>
                        <form class="actionForm" action="controller" method="post">
                            <input class="listButton" type="submit" name="userInfo" value="<fmt:message key="button.userInfo"/>"/>
                            <input class="listText" type="text" name="contractNumber" placeholder="<fmt:message key="label.contractNumber"/>"/>
                            <input type="hidden" name="command" value="TRANSFER"/>
                            <input type="hidden" name="page" value="USER_ACCOUNT_INFO"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>

