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
							<h2 class="display-5 link-body-emphasis mb-1">${ board.boardTitle }</h2>
							<hr>
							<ul>
								<li><img alt="" height="200" src="/file/Profile/${ member.profileVO.saveName }"/></li>
								<li><p>${ member.username }</p></li>
								<li><p>${ member.name }</p></li>
								<li><p>${ member.birth }</p></li>
								<li><p>${ member.email }</p></li>
								<li><p>${ member.phone }</p></li>
							</ul>
							<div class="w-100 d-flex justify-content-end mb-2">
								<a class="btn btn-primary py-1 px-2" href="./update">정보 수정</a>
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
</body>
</html>