<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<c:import url="/WEB-INF/views/include/head_css.jsp" />

<title>${ title }</title>
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
						<div class="w-100">
							<form id="searchForm">
								<div class="input-group mb-3">
									<input type="hidden" id="pageNum" name="pageNum">
									<select class="form-control col-2 col-xl-1 rounded-left" name="kind" aria-label="Default select example">
										<option ${ pager.kind eq '' ? 'selected' : '' }>Select</option>
										<option value="k1" ${ pager.kind eq 'k1' ? 'selected' : '' }>Title</option>
										<option value="k2" ${ pager.kind eq 'k2' ? 'selected' : '' }>Content</option>
										<option value="k3" ${ pager.kind eq 'k3' ? 'selected' : '' }>Writer</option>
									</select>
									<input type="text" class="form-control col-10 rounded-right" name="keyword" value="${ pager.keyword }" placeholder="Recipient’s username" aria-label="Recipient’s username" aria-describedby="button-addon2">
									<button class="btn btn-outline-secondary ml-1" type="submit" id="button-addon2">Button</button>
								</div>
							</form>
						</div>
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
										<c:choose>
											<c:when test="${ empty board.boardHit }">
												<td class="text-left" colspan="4"><span class="d-block">
													<c:catch>
														<c:forEach var="i" begin="1" end="${ board.boardDepth }">&nbsp&nbsp&nbsp&nbsp</c:forEach><c:if test="${ board.boardDepth ne 0 }">└ </c:if>
													</c:catch>
														삭제된 게시물 입니다.
												</span></td>
											</c:when>
											<c:otherwise>
												<td class="text-left"><a class="d-block" href="./detail?boardNum=${ board.boardNum }">
													<c:catch>
														<c:forEach var="i" begin="1" end="${ board.boardDepth }">&nbsp&nbsp&nbsp&nbsp</c:forEach><c:if test="${ board.boardDepth ne 0 }">└ </c:if>
													</c:catch>
														${ board.boardTitle }
												</a></td>
												<td>${ board.boardWriter }</td>
												<td>${ fn:substring(board.boardDate, 0, 10) }</td>
												<td>${ board.boardHit }</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="w-100 d-flex justify-content-center">
							<nav aria-label="Page navigation example">
								<ul class="pagination">
									<c:if test="${ pager.startNum gt 1 }">
										<li class="page-item">
											<button class="page-link pn" data-pn="${ pager.startNum - 1 }" aria-label="Previous">
												&laquo;
											</button>
										</li>
									</c:if>
									<c:forEach var="i" begin="${ pager.startNum }" end="${ pager.endNum }">
										<li class="page-item <c:if test="${ pager.pageNum eq i }">active</c:if>">
											<button class="page-link pn" data-pn="${ i }">${ i }</button>
										</li>
									</c:forEach>
									<c:if test="${ pager.endNum lt pager.totalPage }">
										<li class="page-item">
											<button class="page-link pn" data-pn="${ pager.endNum + 1 }" aria-label="Next">
												&raquo;
											</button>
										</li>
									</c:if>
								</ul>
							</nav>
						</div>
						<div class="w-100 d-flex justify-content-end mb-2">
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
	
	<script type="text/javascript" src="/js/board/board_list.js"></script>
</body>
</html>