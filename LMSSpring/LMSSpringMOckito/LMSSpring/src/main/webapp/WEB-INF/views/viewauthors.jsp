<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
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
	<h1>
		List of Authors in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Authors in LMS:
		${totalCount}
		Authors
	</h1>
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
				<li class="page-item"><a class="page-link" href="pageAuthors?pageNo=${i}">${i}</a></li>	
			</gcit:forEach>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped" id="authorsTable">
		<tr>
			<th>#</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th>Edit Author</th>
			<th>Delete Author</th>
		</tr>
		<gcit:forEach var="a" items="${authors}" varStatus="authorsLoop">
			<tr>
			<td>${authorsLoop.index+1}</td>
			<td>${a.authorName}</td>
			<td>
				<gcit:forEach var="b" items="${a.books}">${b.title} | </gcit:forEach>
			</td>
			<td><button type="button"
					href="editauthor.jsp?authorId=${a.authorId}"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteAuthor?authorId=${a.authorId}'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		</gcit:forEach>
	</table>
</div>

