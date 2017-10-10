<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="gcit" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
		${statusMessage}
		<spring:label path="title">Enter Book Name: </spring:label>
		<spring:input path="title" />
		<table class="table table-bordered">
			<tr>
				<th>Select Authors</th>
				<th>Select Publisher</th>
				<th>Select Branches</th>
			</tr>
			<tr>
				<td><select multiple="multiple" size="10" name="authorIds">
						<gcit:forEach var="a" items="${authors}">
							<spring:option value="${a.authorId}">${a.authorName}</spring:option>
						</gcit:forEach>
				</select></td>
				<td><select size="10" name="publisherIds">
						<gcit:forEach items="${publishers}" var="p">
						<spring:option value="${p.publisherId}">${p.publisherName}</spring:option>
						</gcit:forEach>
				</select></td>
				<td><select multiple="multiple" size="10" name="branchIds">
						<gcit:forEach items="${branch}" var="br">
						<spring:option value="${br.branchId}">${b.branchName}</spring:option>
						</gcit:forEach>
				</select></td>
				<td><select multiple="multiple" size="10" name="genreIds">
						<gcit:forEach items="${genres}" var="g">
						<spring:option value="${g.genreId}">${g.genreName}</spring:option>
						</gcit:forEach>
				</select></td>
			</tr>
		</table>
		<br />
		<button type="submit" class="btn btn-primary btn-md">Save
			Book</button>
	</form>
</div>