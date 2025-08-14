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
					<div class="row col-lg-8 mx-auto">
						<article class="w-100">
							<h2 class="display-5 link-body-emphasis mb-1">${ product.productName }</h2>
							<ul class="list-unstyled">
								<li>상품 유형: ${ product.productKindVO.kindName }</li>
								<li>금리: ${ product.productRate }</li>
								<li>가입 마감: ${ fn:substring(product.productDate, 0, 10) }</li>
							</ul>
							<hr>
							<% pageContext.setAttribute("newLine", "\n"); %>
							<p class="fs-1">${ fn:replace(product.productContent, newLine, "<br/>") }</p>
						</article>
						<div class="w-100 d-flex justify-content-end">
							<form id="frm">
								<input type="hidden" name="productNum" id="productNum" value="${ product.productNum }">
							</form>
							<div class="justify-content-evenly">
								<button class="action btn btn-success py-1 px-2" id="upd">Update</button>
								<button class="action btn btn-danger py-1 px-2" id="del">Delete</button>
							</div>
						</div>
						<div>
							<button class="action btn btn-primary py-1 px-2" id="signUp">Sign Up</button>
							<button class="action btn btn-outline-primary py-1 px-2" id="cart">Cart</button>
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
<script type="text/javascript" src="/js/product/detail.js"></script>
</body>
</html>