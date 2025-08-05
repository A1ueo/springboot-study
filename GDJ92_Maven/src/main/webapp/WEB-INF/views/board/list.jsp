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
						<h1 class="py-2">${ title } List</h1>
						<table class="table table-striped text-center">
							<thead>
								<tr>
									<th class="col-1">Num</th>
									<th>Title</th>
									<th class="col-2">Writer</th>
									<th class="col-3">Date</th>
									<th class="col-1">Hit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="board" items="${ list }">
									<tr>
										<td>${ board.boardNum }</td>
										<td class="text-left"><a class="d-block" href="./detail?boardNum=${ board.boardNum }">
											<c:catch>
												<c:forEach var="i" begin="1" end="${ board.boardDepth }">&nbsp&nbsp&nbsp&nbsp</c:forEach><c:if test="${ board.boardDepth ne 0 }">└ </c:if>
											</c:catch>
											${ board.boardTitle }
										</a></td>
										<td>${ board.boardWriter }</td>
										<td>${ fn:substring(board.boardDate, 0, 10) }</td>
										<td>${ board.boardHit }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="w-100 d-flex justify-content-end">
							<a class="btn btn-primary py-1 px-2" href="./add">글쓰기</a>
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