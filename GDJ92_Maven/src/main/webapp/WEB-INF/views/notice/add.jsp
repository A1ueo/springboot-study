<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
							<form id="frm" method="post">
								<input type="hidden" name="boardNum" id="boardNum" value="${ notice.boardNum }">
								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label">Writer</label>
									<input name="boardWriter" type="text" class="form-control" id="exampleFormControlInput1" value="${ notice.boardWriter }" <c:if test="${ not empty notice.boardNum }">readonly</c:if> placeholder="작성자">
								</div>
								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label">Title</label>
									<input name="boardTitle" type="text" class="form-control" id="exampleFormControlInput1" value="${ notice.boardTitle }" placeholder="제목">
								</div>
								<div class="mb-3">
									<label for="exampleFormControlTextarea1" class="form-label">Contents</label>
									<textarea name="boardContent" class="form-control" id="exampleFormControlTextarea1" rows="9" style="resize: none;" placeholder="내용">${ notice.boardContent }</textarea>
								</div>
							</form>
							<div class="d-flex justify-content-end">
								<button class="btn btn-success" id="submit">Submit</button>
							</div>
						</article>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Content Wrapper -->
			<c:import url="/WEB-INF/views/include/footer.jsp" />
		</div>
	</div>
	<c:import url="/WEB-INF/views/include/tail.jsp" />
	
<script type="text/javascript" src="/js/board/board_update.js"></script>
</body>
</html>