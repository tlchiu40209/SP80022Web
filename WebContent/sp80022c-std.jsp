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
	<h2>SP 800-22 Publication Test Suite (STS-2): </h2>
	<h3><a href = "http://nvlpubs.nist.gov/nistpubs/Legacy/SP/nistspecialpublication800-22r1a.pdf" target="_blank">A Statistical Test Suite for Random and Pseudorandom Number Generators for Cryptographic Applications - Standard mode</a></h3>
	<form action = "sp80022c-std.do" method="post" enctype="multipart/form-data">
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
					<table border="2">
						<tr>
							<td>
								BitCounts:
							</td>
							<td>
								<input type = "text" name="bitCounts" value="100000">
							</td>
						</tr>
						<tr>
							<td>
								Iterations:
							</td>
							<td>
								<input type = "text" name="iterations" value="100">
							</td>
						</tr>
					</table>
				</td>
				<td>
					Random Source:
					<fieldset id = "testTargets">
						<table border = "2">
							<tr>
								<td>
									<input type = "radio" value="file" name="testTargets" checked = "checked"> File
								</td>
								<td>
									<input type = "radio" value="lc" name="testTargets"> Linear Congruential
								</td>
							</tr>
							<tr>
								<td>
									<input type = "radio" value="qc1" name="testTargets"> Quadratic Congruential I
								</td>
								<td>
									<input type = "radio" value="qc2" name="testTargets"> Quadratic Congruential II
								</td>
							</tr>
							<tr>
								<td>
									<input type = "radio" value="cc" name="testTargets"> Cubic Congruential
								</td>
								<td>
									<input type = "radio" value="xor" name="testTargets"> XOR
								</td>
							</tr>
							<tr>
								<td>
									<input type = "radio" value="me" name="testTargets"> Modular Exponentiation
								</td>
								<td>
									<input type = "radio" value="bbs" name="testTargets"> Blum-Blum-Shub
								</td>
							</tr>
							<tr>
								<td>
									<input type = "radio" value="ms" name="testTargets"> Micali-Schnorr
								</td>
								<td>
								<input type = "radio" value="gsha1" name="testTargets"> G Using SHA-1
								</td>
							</tr>
						</table>
					</fieldset>
					<br>
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
								128
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