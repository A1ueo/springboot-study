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
						<h1 class="py-2 text-uppercase">${ title } Cart</h1>
						<article class="w-100">
							<table class="table table-striped text-center">
								<thead>
									<tr>
										<th class="col-2">
											<div class="form-check text-left">
												<input class="form-check-input" type="checkbox" id="checkAll">
												<label class="form-check-label" for="checkDefault">
													전체 선택
												</label>
											</div>
										</th>
										<!-- <th class="col-1">Num</th> -->
										<th>Title</th>
										<th class="col-3">Date</th>
										<th class="col-1">Rate</th>
										<th class="col-1">Kind</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="p" items="${ list }">
										<tr>
											<%-- <td>${ p.productNum }</td> --%>
											<td class="col-1">
												<div class="form-check text-left">
													<input class="form-check-input ch" type="checkbox" value="${ p.productNum }">
												</div>
											</td>
											<td> <a class="d-block" href="/product/detail?productNum=${ p.productNum }">${ p.productName }</a></td>
											<td>${ p.productDate }</td>
											<td>${ p.productRate }</td>
											<td>${ p.productKindVO.kindName }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<button id="delBtn" class="btn btn-danger">DELETE</button>
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
	<script type="text/javascript" src="/js/member/cartList.js"></script>
</body>
</html>