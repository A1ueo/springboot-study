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
						<form id="frm" method="post" >
							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label">ID</label>
								<input name="username" type="text" class="form-control" id="exampleFormControlInput1" placeholder="아이디">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Password</label>
								<input name="password" type="password" class="form-control" id="exampleFormControlInput2" placeholder="비밀번호">
							</div>
							<div class="mb-3 d-flex justify-content-end">
								<button class="btn btn-success" id="submit">Login</button>
							</div>
						</form>
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

</body>

</html>
