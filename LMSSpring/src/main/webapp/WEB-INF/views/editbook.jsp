<%@page import="com.gcit.lms.entity.Author"%>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="java.util.List"%>
<div class="container">
	<!-- 	<h1>Welcome to GCIT Library Management System!</h1> -->
	<!-- 	<h2>Edit Book</h2> -->
	<form method="post" action="editBook">
		${statusMessage} <br />Enter Book Name to Edit: <input type="text"
			name="bookName" value="${book.title}"><br /> <input
			type="hidden" name="bookId" value="${book.bookId}"><br />
		<table class="table table-bordered">
			<tr>
				<th>Select Authors</th>
				<th>Select Publisher</th>
			</tr>
			<tr>
				<td><select multiple="multiple" size="10" name="authorIds">
						<gcit:forEach var="a" items="${authors}">
							<gcit:choose>
								<gcit:when test="${book.authors.contains(a)}">
									<option value="${a.authorId}" selected>${a.authorName}</option>
								</gcit:when>
								<gcit:otherwise>
									<option value="${a.authorId}">${a.authorName}</option>
								</gcit:otherwise>
							</gcit:choose>
						</gcit:forEach>
				</select></td>
				<td><select size="10" name="publisherIds">
						<gcit:forEach var="p" items="${publishers}">
							<gcit:choose>
								<gcit:when test="${book.publisher.publisherId == p.publisherId}">
									<option value="${p.publisherId}" selected>${p.publisherName}</option>
								</gcit:when>
								<gcit:otherwise>
									<option value="${p.publisherId}">${p.publisherName}</option>
								</gcit:otherwise>
							</gcit:choose>
						</gcit:forEach>
				</select></td>
				<td><select size="10" name="genreIds" multiple="multiple">
						<gcit:forEach var="g" items="${genres}">
							<gcit:choose>
								<gcit:when test="${book.genres.contains(g)}">
									<option value="${g.genreId}" selected>${g.genreName}</option>
								</gcit:when>
								<gcit:otherwise>
									<option value="${g.genreId}">${g.genreName}</option>
								</gcit:otherwise>
							</gcit:choose>
						</gcit:forEach>
				</select></td>
			</tr>
		</table>
		<br />
		<button type="submit">Update Book</button>
	</form>
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