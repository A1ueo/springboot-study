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
				<div class="row col-lg-8 mx-auto">
					<h1 class="py-2 text-uppercase">${ title } </h1>
					<article class="w-100">
						<form id="frm" method="post" enctype="multipart/form-data">
							<input type="hidden" name="boardNum" id="boardNum" value="${ board.boardNum }">
<%-- 
							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label">Writer</label>
								<input name="boardWriter" type="text" class="form-control" id="exampleFormControlInput1" value="${ board.boardWriter }" <c:if test="${ not empty board.boardWriter }">readonly</c:if> placeholder="작성자">
							</div>
 --%>
							<div class="mb-3">
								<label for="exampleFormControlInput2" class="form-label">Title</label>
								<input name="boardTitle" type="text" class="form-control" id="exampleFormControlInput2" value="${ board.boardTitle }" placeholder="제목">
							</div>
							<div class="mb-3">
								<label for="exampleFormControlTextarea1" class="form-label">Contents</label>
								<textarea name="boardContent" class="form-control" id="contentsBox" rows="9" style="resize: none;" placeholder="내용">${ board.boardContent }</textarea>
							</div>
							
							<div class="mb-3">
								<button type="button" class="btn btn-primary" id="add">ADD</button>
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

<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-lite.min.js"></script>
<script type="text/javascript">
	$('#contentsBox').summernote({
		callbacks: {
			onImageUpload: function (files) {
				let f = new FormData();
				f.append("bf", files[0]);
				
				fetch('./boardFile', {
					method: 'POST', 
					body: f
				})
				.then(r=>r.text())
				.then(r=>{
					$('#contentsBox').summernote('editor.insertImage', r);
				})
				.catch(e=>console.log(e));
			},
			onMediaDelete: function(files) {
				let f = $(files[0]).attr('src');
				
				let params = new URLSearchParams();
				params.append("fileName", f);
				
				fetch('./boardFileDelete', {
					method: 'POST',
					body: params
				})
				.then(r=>r.json())
				.then(r=>{
					console.log(r);
				});
			}
		}
	});
</script>
</body>

</html>
