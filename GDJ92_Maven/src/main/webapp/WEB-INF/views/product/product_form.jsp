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
					<h1>Products Form</h1>
					<div class="row col-md-8 mx-auto">
						<article class="w-100">
							<form id="frm" method="post">
								<input type="hidden" name="boardNum" id="boardNum" value="${ product.productNum }">
								<div class="mb-3">
									<label for="exampleFormControlInput1" class="form-label">Title</label>
									<input name="productName" type="text" class="form-control" id="exampleFormControlInput1" value="${ product.productName }" placeholder="상품명">
								</div>
								<div class="mb-3">
									<label for="exampleFormControlInput2" class="form-label">Type</label>
									<select name="kindNum" class="form-control" id="exampleFormControlInput2">
										<option>선택</option>
										<c:forEach var="k" items="${ list }">
											<option value="${ k.kindNum }" ${ k.kindNum eq product.productKindVO.kindNum ? 'selected':'' } >${ k.kindName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="mb-3">
									<label for="exampleFormControlInput3" class="form-label">Rate</label>
									<input name="productRate" type="text" class="form-control" id="exampleFormControlInput3" value="${ product.productRate }" placeholder="금리">
								</div>
								<div class="mb-3">
									<label for="exampleFormControlInput4" class="form-label">Date</label>
									<input name="productDate" type="date" class="form-control" id="exampleFormControlInput4" value="${ product.productDate }">
								</div>
								<div class="mb-3">
									<label for="exampleFormControlTextarea1" class="form-label">Contents</label>
									<textarea name="productContents" class="form-control" id="exampleFormControlTextarea1" rows="9" style="resize: none;" placeholder="내용">${ product.productContents }</textarea>
								</div>
								<div class="d-flex justify-content-end">
									<button class="btn btn-success" id="submit">Submit</button>
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
