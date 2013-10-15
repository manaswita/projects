<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Bookmarks</title>
<script type="text/javascript" src="js/common.js"></script> 
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="title">My Bookmarks</div>
<%-- <%
   // Set refresh, autoload time as 5 seconds
   response.setIntHeader("Refresh", 5);
   // Get current time
   Calendar calendar = new GregorianCalendar();
   String am_pm;
   int hour = calendar.get(Calendar.HOUR);
   int minute = calendar.get(Calendar.MINUTE);
   int second = calendar.get(Calendar.SECOND);
   if(calendar.get(Calendar.AM_PM) == 0)
      am_pm = "AM";
   else
      am_pm = "PM";
   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
   out.println("Crrent Time: " + CT + "\n");
%> --%>
<form:form action="searchBookmarks.do" method="post" commandName="BookmarkList" modelAttribute="bookmarkBean">
	<fieldset><legend><b>Bookmark List</b></legend>
	<p>
		<a href="#" onclick="javascript:go('gotoAddBookmark.do');" class="button_example" title="Add Bookmark">ADD NEW BOOKMARK</a>
	</p>
		<table class="tabledefault" >
		<tr>
			<td style="color:blue;font-size: small;" colspan="3">${ERROR_MSG}</td>
		</tr>
		<tr>
			<td colspan="3" align="left">Filter on Tags
			<form:input path="tagFilter"/>
			<input type="submit" title="Filter" value="FILTER" class="button_example"/> 
			<a href="#" title="Clear Filters" onclick="javascript:go('clearFilters.do');" class="button_example">CLEAR FILTERS</a>
			<i style="font-size: small;">*To filter on multiple tags, separate tags by commas,wildcards supported </i></td>

		</tr>
			<tr class="tabhead">
				<th width="25%">Title</th>
				<th width="20%">URL</th>
				<th width="45%">Notes</th>
				<th width="10%">Tags</th>
				<th></th>
			</tr>
	<c:choose>
		<c:when  test="${!empty bookmarkList}">
			<c:forEach var="bookmark" items="${bookmarkList}" varStatus="loopStatus">
			<tr class="${loopStatus.index % 2 == 0 ? 'roweven' : 'rowodd'}">
					<td width="25%">
						<c:out value="${bookmark.title}"></c:out>
					</td>
					<td width="20%">
						<a href="${bookmark.url}" target="_blank"><c:out value="${bookmark.url}"></c:out></a>
					</td>
					<td width="45%">
						<c:out value="${bookmark.notes}"></c:out>
					</td>
					<td width="10%">
						<c:out value="${bookmark.tagString}"></c:out>
					</td>
					<td><a href="#" onclick="javascript:go('deleteBookmark.do?bookmarkId=${bookmark.bookmarkId}');" class="button_example" title="Delete Bookmark">X</a></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<td colspan="5" align="center" class="rowodd">
				<c:out value="No Records Found"></c:out>
			</td>
		</c:otherwise>
	</c:choose>
		</table>
	</fieldset>
</form:form>
</body>
</html>