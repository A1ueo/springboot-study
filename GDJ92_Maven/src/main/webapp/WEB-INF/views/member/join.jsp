<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<c:import url="/WEB-INF/views/include/head_css.jsp" />
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.css" rel="stylesheet">

<title>${ title }</title>
</head>

<body id="page-top">
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
					<h1 class="py-2 text-uppercase">${ title } </h1>
					<article class="w-100">
						<form id="frm" method="post" enctype="multipart/form-data">
							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label">ID</label>
								<input name="username" type="text" class="form-control" id="exampleFormControlInput1" placeholder="아이디">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Password</label>
								<input name="password" type="password" class="form-control" id="exampleFormControlInput2" placeholder="비밀번호">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Name</label>
								<input name="name" type="text" class="form-control" id="exampleFormControlInput2" placeholder="이름">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Email</label>
								<input name="email" type="email" class="form-control" id="exampleFormControlInput2" placeholder="이메일">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Phone</label>
								<input name="phone" type="text" class="form-control" id="exampleFormControlInput2" placeholder="전화번호">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Birthday</label>
								<input name="birth" type="date" class="form-control" id="exampleFormControlInput2" placeholder="생년월일">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Profile</label>
								<input name="profile" type="file" class="form-control" id="exampleFormControlInput2" >
							</div>
							<!-- fn:length(board.boardFileVOs) -->
							<div>
								<c:forEach var="f" items="${ board.boardFileVOs }">
									<div class="mb-3 d-flex w-100 justify-content-between">
										<%-- <input type="file" class="form-control col-11" id="formFile" name="attaches" value="${ f.oriName }"> --%>
										<input type="button"" class="form-control col-11 text-left" id="formFile" name="attaches" value="${ f.oriName }">
										<button type="button" data-file-num="${ f.fileNum }" class="deleteFile btn btn-outline-danger">X</button>
									</div>
								</c:forEach>
							</div>
							<div id="result" data-file-count="${ board.boardFileVOs.size() }">
							</div>
						</form>
						<div class="mb-3 d-flex justify-content-end">
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

<script type="text/javascript" src="/js/board/board_form.js"></script>
</body>

</html>
