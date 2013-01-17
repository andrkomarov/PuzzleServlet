<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <table border=3 cellpadding=5 cellspacing=5 width=25%>
	<c:forEach var="elem" items="${field.field}" varStatus="status">
    	<c:if test="${status.index % field.side == 0}">
    		<tr>
    	</c:if>
		<td ALIGN=center width="<c:out value="${100 / field.side}"/>%">
		
		<c:choose>
			<c:when test="${elem.movable}">
				<a href="<c:out value="Game?action=move&name=${elem.name}"/>">
	      			<c:out value="${elem.name}"/>
	      		</a>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${elem.name=='0'}">
						<b><c:out value="${elem.name}"/></b>
					</c:when>
					<c:otherwise>
						<c:out value="${elem.name}"/>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		
		</td>
    	<c:if test="${status.index % field.side == field.side - 1}">
    		</tr>
    	</c:if>
    </c:forEach>
    
    </table>
    
    <br>
	<a href="Game?action=shuffle">shuffle</a>

</body>
</html>