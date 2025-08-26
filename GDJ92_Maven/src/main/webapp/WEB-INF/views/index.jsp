<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal" var="member"/>
					</sec:authorize>
					<h1>Index</h1>
					<h3>
						<spring:message code="welcome.message2" text="Hi"/>
					</h3>
					<c:if test="${ not empty member }">
						<h3>Add GitHub</h3>
						<h3><sec:authentication property="principal.name"/></h3>
						<h3>
							<spring:message code="user.info" arguments="${ member.username }, ${ member.email }" argumentSeparator=","/>
						</h3>
					</c:if>
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
