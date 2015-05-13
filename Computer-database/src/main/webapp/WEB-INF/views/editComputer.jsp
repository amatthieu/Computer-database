<!DOCTYPE html>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> <spring:message
					code="application.title" />
			</a>
			<div class="navbar-right">
				<a href="?id=${computer.id}&lang=fr"><img src="img/flag_fr.jpg"></a>
				<a href="?id=${computer.id}&lang=en"><img src="img/flag_en.jpg"></a>
			</div>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1><spring:message code="editComputer.title" /></h1>
					<c:if test="${error}">
						<div class="alert alert-danger">You have an error</div>
					</c:if>

					<form:form modelAttribute="computerDTO" method="POST" action="editComputer" onsubmit="return checkValues()">
						<fieldset>
							<div class="form-group" id="computerNameDiv">
								<label for="computerName"><spring:message
										code="addComputer.name" /></label>
								<form:input type="text" class="form-control" path="name"
									value="${computer.name}" />
							</div>
							<div class="text-center form-group"><form:errors class="alert alert-danger" path="name"/></div>
							<div class="form-group" id="introducedDiv">
								<label for="introduced"><spring:message
										code="addComputer.introduced" /></label>
								<form:input type="date" class="form-control" path="introduced"
									value="${computer.introduced}" />
							</div>
							<div class="text-center form-group"><form:errors class="alert alert-danger" path="introduced"/></div>
							<div class="form-group" id="discontinuedDiv">
								<label for="discontinued"><spring:message
										code="addComputer.discontinued" /></label>
								<form:input type="date" class="form-control" path="discontinued"
									value="${computer.discontinued}" />
							</div>
							<div class="text-center form-group"><form:errors class="alert alert-danger" path="discontinued"/></div>
							<div class="form-group" id="companyDiv">
								<label for="companyId"><spring:message
										code="addComputer.company" /></label>
								<form:select class="form-control" path="companyId">
									<!-- Actual value -->
									<c:if test="${computer.companyName != null}">
										<option value="${computer.companyId}">${computer.companyName}</option>
									</c:if>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<c:if test="${company.id != computer.companyId}">
											<option value="${company.id}">${company.name}</option>
										</c:if>
									</c:forEach>
								</form:select>
							</div>
							<div class="text-center form-group"><form:errors class="alert alert-danger" path="companyId"/></div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit"
								value="<spring:message code='addComputer.button.add' />"
								class="btn btn-primary">
							<spring:message code="addComputer.or" />
							<a href="dashboard" class="btn btn-default"><spring:message
									code="addComputer.button.cancel" /></a>
						</div>
					</form:form>

					<!--<form action="editComputer" onsubmit="return checkValues()"
						method="POST">
						<input type="hidden" name="id" id="id" value="${computer.id}" />
						<fieldset>
							<div class="form-group" id="computerNameDiv">
								<label for="computerName"><spring:message code="addComputer.name" /></label> <input
									type="text" class="form-control" id="name" name="name"
									value="${computer.name}">
							</div>
							<div class="form-group" id="introducedDiv">
								<label for="introduced"><spring:message code="addComputer.introduced" /></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" value="${computer.introduced}">
							</div>
							<div class="form-group" id="discontinuedDiv">
								<label for="discontinued"><spring:message code="addComputer.discontinued" /></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" value="${computer.discontinued}">
							</div>
							<div class="form-group" id="companyDiv">
								<label for="companyId"><spring:message code="addComputer.company" /></label> <select
									class="form-control" name="companyId" id="companyId">
									<!-- Actual value 
									<c:if test="${computer.companyName != null}">
										<option value="${computer.companyId}">${computer.companyName}</option>
									</c:if>
									<option value="0">--</option>
									<c:forEach items="${companies}" var="company">
										<c:if test="${company.id != computer.companyId}">
											<option value="${company.id}">${company.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code='editComputer.button.edit' />" class="btn btn-primary">
							<spring:message code="addComputer.or" /> <a href="dashboard" class="btn btn-default"><spring:message code="addComputer.button.cancel" /></a>
						</div>
					</form>-->
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.cookie.js"></script>
	<script src="js/moment.min.js"></script>
	<!-- <script src="js/editComputer.js"></script> -->
</body>
</html>