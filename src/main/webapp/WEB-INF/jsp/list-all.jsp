<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
<title>List of all Gifs</title>
</head>
<body>

<c:forEach items="${gifs}" var="gif">
    ${gif.getTitle()}<br>
<image src="${gif.getGifDetails()}"/>
    ${gif.getCaption()}<br><br>
</c:forEach>

</body>
</html>

