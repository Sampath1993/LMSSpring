<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
	Author author = service.readAuthorByPK(Integer.parseInt(request.getParameter("authorId")));
%>

<div class="modal fade bs-example-modal-lg" id="editAuthorModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form method="post" action="editAuthor">
				<div class="modal-body">
					${statusMessage} <br />Enter Author Name to Edit: <input
						type="text" name="authorName" value="<%=author.getAuthorName()%>"><br />
					<input type="hidden" name="authorId"
						value="<%=author.getAuthorId()%>">
					<button type="submit">Update Author</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>