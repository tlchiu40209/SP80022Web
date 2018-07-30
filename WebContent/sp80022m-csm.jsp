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
	<h2>SP 800-22 Publication Test Suite (STS-Improve/3): </h2>
	<h3><a href = "http://nvlpubs.nist.gov/nistpubs/Legacy/SP/nistspecialpublication800-22r1a.pdf" target="_blank">A Statistical Test Suite for Random and Pseudorandom Number Generators for Cryptographic Applications - Default mode</a></h3>
	<form action = "sp80022m-csm.do" method="post" enctype="multipart/form-data">
		<table border = "2">
			<tr>
				<td>
					<b>Bitcounts and Iterations</b>
				</td>
				<td>
					<b>Random Source</b>
				</td>
			</tr>
			<tr>
				<td>
					Bitcounts: 1048576<br>
					Iterations: 1
				</td>
				<td>
					Random Source: By File<br>
					<input type = "file" name="rndFile" /> 
				</td>
			</tr>
			<tr>
				<td>
					<b>Advance Options</b>
				</td>
				<td>
					<b>Upload</b>
				</td>
			</tr>
			<tr>
				<td>
					<table border=2>
						<tr>
							<td>
								<b>Item</b>
							</td>
							<td>
								<b>Value</b>
							</td>
						</tr>
						<tr>
							<td>
								Block Frequency Test - block length(M)
							</td>
							<td>
								16384
							</td>
						</tr>
						<tr>
							<td>
								NonOverlapping Template Test - block length(m)
							</td>
							<td>
								9
							</td>
						</tr>
						<tr>
							<td>
								Overlapping Template Test - block length(m)
							</td>
							<td>
								9
							</td>
						</tr>
						<tr>
							<td>
								Approximate Entropy Test - block length(m)
							</td>
							<td>
								10
							</td>
						</tr>
						<tr>
							<td>
								Serial Test - block length(m)
							</td>
							<td>
								16
							</td>
						</tr>
						<tr>
							<td>
								Linear Complexity Test - block length(M)
							</td>
							<td>
								500
							</td>
						</tr>
						<tr>
							<td>
								Uniformity bins:
							</td>
							<td>
								sqrt(iterations)
							</td>
						</tr>
						<tr>
							<td>
								Uniformity Cutoff Level:
							</td>
							<td>
								0.0001
							</td>
						</tr>
						<tr>
							<td>
								Alpha Confidence Level:
							</td>
							<td>
								0.01
							</td>
						</tr>
					</table>
				</td>
				<td>
					File Type: <br>
					<fieldset id = "isAscii">
						<input type = "radio" value ="ascii" name="isAscii"> ASCII bitstream (in 0s and 1s) <br>
						<input type = "radio" value ="binary" name="isAscii" checked="checked" > Binary <br>
					</fieldset>
					<fieldset id = "transRequest">
						<input type = "radio" value ="tranba" name="transRequest"> Transform binary into ASCII bitstream <br>
						<input type = "radio" value ="notran" name="transRequest" checked = "checked"> Don't perform any transform <br>
					</fieldset>
					<input type = "submit" value = "Upload" name="upload"/>
				</td>
			</tr>
		</table>

	</form>
</center>

</body>

</html>