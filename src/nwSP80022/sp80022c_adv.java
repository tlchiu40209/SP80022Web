package nwSP80022;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location="/home/sp80022/sts2/upload")
@WebServlet("/sp80022c-adv.do")

public class sp80022c_adv extends HttpServlet{
	
	private final String home_directory = "/home/sp80022/sts2/";
	private final String upload_directory = "/home/sp80022/sts2/upload/";
	private final String jobTicket_directory = "/home/sp80022/sts2/jobTicket/";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter respWriter = resp.getWriter();
		
		/** File Upload **/
		Part rndFile_part;
		
		/** End of File Upload**/
		
		/** jobTicket Params **/
		String bitCounts = req.getParameter("bitCounts");
		String testTarget = req.getParameter("testTargets");
		String rndFile = null;
		String blockFreq = req.getParameter("blockFreq"); //4-1
		String novLapping = req.getParameter("novLapping"); //4-2
		String ovLapping = req.getParameter("ovLapping"); //4-3
		String approxEnt = req.getParameter("approxEnt"); //4-4
		String srTest = req.getParameter("srTest"); //4-5
		String linComplex = req.getParameter("linComplex"); //4-6
		String iterations = req.getParameter("iterations");
		String isAscii = req.getParameter("isAscii");
		String trans = req.getParameter("transRequest");
		/** End of jobTicket Params **/
		
		/** Utilities **/
		String SP = " ";
		Date today = new Date();
		String thisJobID = Long.toString(today.getTime());
		FileOutputStream jobWriter = new FileOutputStream(jobTicket_directory + thisJobID + ".log");
		/** End of Utilities **/
		
		rndFile_part = req.getPart("rndFile");
		
		/** Checking Invalid Parameters **/
		String jobTicket = null;
		boolean checkLock = true;
		if (bitCounts.isEmpty() || bitCounts == null){ // 1
			checkLock = false;
			respWriter.println("BitCounts not specified!");
		} else {
			if (!checkInteger(bitCounts)){
				checkLock = false;
				respWriter.println("BitCounts contains non-numerical charecters.");
			} else {
				int bitCounts_int = Integer.parseInt(bitCounts);
				if (bitCounts_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity BitCounts specified!");
				} else if (bitCounts_int < 1) {
					checkLock = false;
					respWriter.println("BitCounts should not be less than 1 or a negative number!");
				} else {
					jobTicket = bitCounts + SP;
				}
			}
		}
		
		if (testTarget.isEmpty() || testTarget == null){
			respWriter.println("Random Source not specified!");
			checkLock = false;
		} else {
			switch(testTarget){
			case "file":
				String rndFileName = rndFile_part.getSubmittedFileName();
				if (rndFileName.isEmpty()){
					respWriter.println("No rndFile specified!");
					checkLock = false;
				} else {
					if (checkFileName(rndFileName)){
						writePart(rndFile_part);
						rndFile = upload_directory + rndFileName;
						jobTicket = jobTicket + "0" + SP + rndFile + SP;
					} else {
						respWriter.println("The filename of your file should only contains alphanumeric characters");
						checkLock = false;
					}
				}
				break;
			case "lc":
				jobTicket = jobTicket + "1" + SP + "0" + SP; 
				break;
			case "qc1":
				jobTicket = jobTicket + "2" + SP + "0" + SP;
				break;
			case "qc2":
				jobTicket = jobTicket + "3" + SP + "0" + SP;
				break;
			case "cc":
				jobTicket = jobTicket + "4" + SP + "0" + SP;
				break;
			case "xor":
				jobTicket = jobTicket + "5" + SP + "0" + SP;
				break;
			case "me":
				jobTicket = jobTicket + "6" + SP + "0" + SP;
				break;
			case "bbs":
				jobTicket = jobTicket + "7" + SP + "0" + SP;
				break;
			case "ms":
				jobTicket = jobTicket + "8" + SP + "0" + SP;
				break;
			case "gsha1":
				jobTicket = jobTicket + "9" + SP + "0" + SP; 
				break;
			default:
				respWriter.println("You specified a bad random source.");
				checkLock = false;	
			}	
		}
		//jobTicket = jobTicket + "0" + SP;
		
		if (blockFreq.isEmpty() || blockFreq == null){ //4-1
			checkLock = false;
			respWriter.println("Block Freq not specified");
		} else {
			if (!checkInteger(blockFreq)){
				checkLock = false;
				respWriter.println("blockFreq contains non-numerical characters");
			} else {
				int blockFreq_int = Integer.parseInt(blockFreq);
				if (blockFreq_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity blockFreq specified!");
				} else if (blockFreq_int < 1){
					checkLock = false;
					respWriter.println("blockFreq should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "1_" + blockFreq + "_";
				}
			}
		}
		
		if (novLapping.isEmpty() || novLapping == null){ //4-2
			checkLock = false;
			respWriter.println("novLapping not specified");
		} else {
			if (!checkInteger(novLapping)){
				checkLock = false;
				respWriter.println("novLapping contains non-numerical characters");
			} else {
				int novLapping_int = Integer.parseInt(novLapping);
				if (novLapping_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity novLapping specified!");
				} else if (novLapping_int < 1){
					checkLock = false;
					respWriter.println("novLapping should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "2_" + novLapping + "_";
				}
			}
		}
		
		if (ovLapping.isEmpty() || ovLapping == null){ //4-3
			checkLock = false;
			respWriter.println("ovLapping not specified");
		} else {
			if (!checkInteger(ovLapping)){
				checkLock = false;
				respWriter.println("ovLapping contains non-numerical characters");
			} else {
				int ovLapping_int = Integer.parseInt(ovLapping);
				if (ovLapping_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity ovLapping specified!");
				} else if (ovLapping_int < 1){
					checkLock = false;
					respWriter.println("ovLapping should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "3_" + ovLapping + "_";
				}
			}
		}
		
		if (approxEnt.isEmpty() || approxEnt == null){ //4-4
			checkLock = false;
			respWriter.println("approxEnt not specified");
		} else {
			if (!checkInteger(approxEnt)){
				checkLock = false;
				respWriter.println("approxEnt contains non-numerical characters");
			} else {
				int approxEnt_int = Integer.parseInt(approxEnt);
				if (approxEnt_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity approxEnt specified!");
				} else if (approxEnt_int < 1){
					checkLock = false;
					respWriter.println("approxEnt should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "4_" + approxEnt + "_";
				}
			}
		}
		
		if (srTest.isEmpty() || srTest == null){ //4-5
			checkLock = false;
			respWriter.println("srTest not specified");
		} else {
			if (!checkInteger(srTest)){
				checkLock = false;
				respWriter.println("srTest contains non-numerical characters");
			} else {
				int srTest_int = Integer.parseInt(srTest);
				if (srTest_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity srTest specified!");
				} else if (srTest_int < 1){
					checkLock = false;
					respWriter.println("srTest should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "5_" + srTest + "_";
				}
			}
		}
		
		if (linComplex.isEmpty() || linComplex == null){ //4-6
			checkLock = false;
			respWriter.println("linComplex not specified");
		} else {
			if (!checkInteger(linComplex)){
				checkLock = false;
				respWriter.println("linComplex contains non-numerical characters");
			} else {
				int linComplex_int = Integer.parseInt(linComplex);
				if (linComplex_int > 1000000) {
					checkLock = false;
					respWriter.println("An over capacity linComplex specified!");
				} else if (linComplex_int < 1){
					checkLock = false;
					respWriter.println("linComplex should not be less than 1 more negative number!");
				} else {
					jobTicket = jobTicket + "6_" + linComplex;
				}
			}
		}
		jobTicket = jobTicket + SP;
		
		if (iterations.isEmpty() || iterations == null){ // 5
			checkLock = false;
			respWriter.println("Iterations not specified!");
		} else {
			if (!checkInteger(iterations)){
				checkLock = false;
				respWriter.println("Iterations contains non-numercial charecters");
			} else {
				int iterations_int = Integer.parseInt(iterations);
				if (iterations_int > 1000000){
					checkLock = false;
					respWriter.println("An over capacity iterations specified!");
				} else if (iterations_int < 1){
					checkLock = false;
					respWriter.println("Iterations should not be less than 1 or a negative number!");
				} else {
					jobTicket = jobTicket + iterations + SP;
				}
			}
		}
		
		if (isAscii.isEmpty() || isAscii == null){
			respWriter.println("File format not specified");
			checkLock = false;
		} else {
			switch(isAscii){
			case "ascii":
				jobTicket = jobTicket + "0" + SP;
				break;
			case "binary":
				jobTicket = jobTicket + "1" + SP;
				break;
			default:
				checkLock = false;
				respWriter.println("Bad file format specified!");
				break;
			}
		}
		
		if (trans.isEmpty() || trans == null){
			respWriter.println("Trans service not specified");
			checkLock = false;
		} else {
			switch (trans){
			case "tranba":
				if (testTarget != "file"){
					respWriter.println("Random Source is not a file!");
					checkLock = false;
				} else {
					jobTicket = jobTicket + "1";
				}
				break;
			case "notran":
				jobTicket = jobTicket + "0";
				break;
			default:
				checkLock = false;
				respWriter.println("Bad trans Service specified");
				break;
			}
		}
		
		if (checkLock){
			jobWriter.write(jobTicket.getBytes());
			jobWriter.flush();
			jobWriter.close();
			respWriter.println("JobID: " + thisJobID);
		}
	}
	
	private boolean checkInteger(String text){
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	private void writePart(Part filePart) throws IOException {
		InputStream in = filePart.getInputStream();
		OutputStream out = new FileOutputStream(upload_directory + filePart.getSubmittedFileName());
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1){
			out.write(buffer, 0, length);
		}
		out.flush();
		in.close();
		out.close();
	}
	
	private boolean checkFileName(String fileName){
		String regex = "^[a-zA-Z0-9.]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
}
