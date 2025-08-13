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
					<div class="row col-lg-8 mx-auto">
						<h1 class="py-2 text-uppercase">${ title } Detail</h1>
						<article class="w-100">
						
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