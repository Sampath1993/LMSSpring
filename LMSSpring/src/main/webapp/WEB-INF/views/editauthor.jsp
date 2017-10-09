<%@page import="com.gcit.lms.entity.Author"%>
<%-- <%@page import="com.gcit.lms.service.AdminService"%> --%>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<div class = "container">
<!-- 	<h1>Welcome to GCIT Library Management System!</h1> -->
<!-- 	<h2>Edit Author</h2> -->
	<form method="post" action="editauthordone">
		${statusMessage}
		<br/>Enter Author Name to Edit: <input type="text" name="authorName" value="${author.authorName}"><br />
		<input type="hidden" name="authorId" value="${author.authorId}"><br />
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds"> <option value="">Select Book to associate</option> 
		<gcit:forEach var="b" items="${books}" varStatus="authorsLoop">
			<option value="${b.bookId}" ${author.books.contains(b) ? '' : 'selected'}>${b.title}</option>
			</gcit:forEach>
		</select>
		<br/>
		<button type="submit">Update Author</button>
	</form>
</div>
	<script>
		$('body').on(
				'click', '[data-toggle="modal"]',
				function() {
					$($(this).data("target") + ' .modal-body').load(
							$(this).data("remote"));
				});
	</script>