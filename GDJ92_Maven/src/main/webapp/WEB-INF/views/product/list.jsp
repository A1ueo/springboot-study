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
						<h1 class="py-2">Product List</h1>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Num</th>
									<th>Title</th>
									<th>Rate</th>
									<th>Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${ list }">
									<tr>
										<td>${ p.productNum }</td>
										<td> <a class="d-block" href="./detail?productNum=${ p.productNum }">${ p.productName }</a></td>
										<td>${ p.productRate }</td>
										<td>${ fn:substring(p.productDate, 0, 10) }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="w-100 d-flex justify-content-end">
							<a class="btn btn-success py-1 px-2">상품 등록</a>
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
</body>
</html>