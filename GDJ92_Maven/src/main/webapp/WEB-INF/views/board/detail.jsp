<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:import url="/WEB-INF/views/include/head_css.jsp" />

<title>Insert title here</title>
</head>
<body  id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/include/sidebar.jsp" />
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/include/topbar.jsp" />
				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Contents 내용 -->
					<div class="row col-md-8 mx-auto">
						<article class="w-100">
							<h2 class="display-5 link-body-emphasis mb-1">${ board.boardTitle }</h2>
							<p class="w-100 text-right">${ board.boardDate } by ${ board.boardWriter }</p>
							<hr>
							<% pageContext.setAttribute("newLine", "\n"); %>
							<p>${ fn:replace(board.boardContent, newLine, "<br/>") }</p>
						</article>
						<div class="w-100 d-flex justify-content-end">
							<form id="frm">
								<input type="hidden" name="boardNum" value="${ board.boardNum }">
							</form>
							<button class="action btn btn-success py-1 px-2" data-kind="u">Update</button>
							<button class="action btn btn-danger py-1 px-2" data-kind="d">Delete</button>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Content Wrapper -->
			<c:import url="/WEB-INF/views/include/footer.jsp" />
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp" />
	
	<script type="text/javascript" src="/js/board/board_detail.js"></script>
</body>
</html>