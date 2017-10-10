<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function searchAuthors() {
		$.ajax({
			method : "POST",
			url : "searchAuthors",
			data : {
				searchString : $('#searchString')
			}
		}).done(function(data) {
			$('#authorsTable').html(data);
		});
	}
</script>
<div class="container">
	<h1>List of Books in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Books in LMS: ${totalCount} Books</h1>
	<input class="form-control mr-sm-2" type="text"
			placeholder="Search Authors" aria-label="Search" name="searchString" id="searchString">
		<button class="btn btn-outline-success my-2 my-sm-0" type="button" onclick="searchAuthors()">Search</button>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<gcit:forEach begin="1" end="${ numOfPages}" var="i">
				<li class="page-item"><a class="page-link" href="viewbooks?pageNo=${i}">${i}</a></li>	
			</gcit:forEach>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book Title</th>
			<th>Authors</th>
			<th>Publisher</th>
			<th>Genres</th>
			<th>Edit Book</th>
			<th>Delete Book</th>
		</tr>
		<gcit:forEach var="b" items="${books}" varStatus="booksLoop">
		<tr>
			<td>${booksLoop.index+1}</td>
			<td>${b.title}</td>
			<td>
				<gcit:forEach var="a" items="${b.authors}">${a.authorName} | </gcit:forEach>
			</td>
			<td>
				${b.publisher.publisherName}
			</td>
			<td>
				<gcit:forEach var="g" items="${b.genres}">${g.genreName} | </gcit:forEach>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='editbook?bookId=${b.bookId}'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBook?bookId=${b.bookId}'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		</gcit:forEach>
	</table>
</div>