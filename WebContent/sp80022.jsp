<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>U.S. NIST SP80022 Test Suite</title>
</head>
<body>
<center>
	<h1>United States National Institute of Standards and Technology</h1>
	<h2> SP 800-22 Publication Test Suite:</h2>
	<h3><a href = "https://nvlpubs.nist.gov/nistpubs/Legacy/SP/nistspecialpublication800-22r1a.pdf" target="__blank">A Statistical Test Suite for Random and Pseudorandom Number Generators for Cryptographic Applications</a></h3>
	<table border = "5">
		<tr>
			<td>
				<h3>STS System 2</h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022c-std.jsp" target="_blank">Standard Opt.</a></h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022c-adv.jsp" target="_blank">Advance Opt.</a></h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022c-csm.jsp" target="_blank">Default Opt.</a></h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022c-down.jsp" target="_blank">Result Collect</a></h3>
			</td>
		</tr>
		<tr>
			<td>
				<h3>STS System 3</h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022m-std.jsp" target="_blank">Standard Opt.</a></h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022m-adv.jsp" target="_blank">Advance Opt.</a></h3>
			</td>
			<td>
				<h3><a href = "/nwSP80022/sp80022m-csm.jsp" target="_blank">Default Opt.</a></h3>
			</td>
			<td>
			</td>
		</tr>
	</table>
</center>

<h3>Please be advised:</h3>
<h3>STS System 2:</h3>
<ul>
	<li>This is the original STS Test Suite provided by NIST.
	<li>The program can only runs one instance at a time; In order to handle multiple requests, a task scheduler is under control.
	<li>After submit your task, please write down the Job ID the server gave.
	<li>Please wait at least a reasonable length of time for the server to complete the task, and click Result Collect to get your result by entering the job ID.
</ul>

<h3>STS System 3:</h3>
<ul>
	<li>This is a modified version of original STS Test Suite. <a href = "https://github.com/arcetri/sts" target="_blank">Click here for more info.</a>
	<li>The program supports multi-core and multi-thread. You can obtain the result while the server finish the process.
</ul>



</body>
</html>