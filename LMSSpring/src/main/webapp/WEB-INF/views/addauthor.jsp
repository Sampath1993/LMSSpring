<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<!-- 	<h1>Welcome to GCIT Library Management System!</h1> -->
	<!-- 	<h2>Edit Author</h2> -->
	<spring:form method="post" action="addauthordone"
		modelAttribute="author">
		${statusMessage}
		<br />
		<spring:label path="authorName">Enter Author Name to Edit: </spring:label>
		<spring:input path="authorName" />
		<br />
		Select Books from list Below: <br />
		<spring:select multiple="true" path="books">
			<gcit:forEach var="b" items="${books}" varStatus="booksLoop">
				<spring:option value="${bookLoops.authorId}">${b.title}</spring:option>
			</gcit:forEach>
		</spring:select>
		<br />
		<input type="submit" value="Add Author" />
	</spring:form>
</div>
<script>
	$('body').on(
			'click',
			'[data-toggle="modal"]',
			function() {
				$($(this).data("target") + ' .modal-body').load(
						$(this).data("remote"));
			});
</script>