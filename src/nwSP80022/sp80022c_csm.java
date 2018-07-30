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
@WebServlet("/sp80022c-csm.do")


public class sp80022c_csm extends HttpServlet {
	
	private final String home_directory = "/home/sp80022/sts2/";
	private final String upload_directory = "/home/sp80022/sts2/upload/";
	private final String jobTicket_directory = "/home/sp80022/sts2/jobTicket/";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		PrintWriter respWriter =  resp.getWriter();
		
		/** jobTicket Parts elements **/
		Part rndFile_part;
		/** End of jobTicket Parts elements **/
		
		/** jobTicket Params **/
		String bitcounts = "1000000";
		String testTargets = "0";
		String rndFile = null;
		String iterations = "10";
		String isAscii = req.getParameter("isAscii");
		String trans = req.getParameter("transRequest");
		/** End of jobTicket Params **/
		 
		/** Utilities **/
		String SP = " ";
		Date today = new Date();
		String thisJobID = Long.toString(today.getTime());
		FileOutputStream jobWriter = new FileOutputStream(jobTicket_directory + thisJobID + ".log");
		/** End of Utilities **/
		
		/** Default mode only request few parts **/
		rndFile_part = req.getPart("rndFile");
		/** Preset information for default mode **/
		
		/** Checking fileName for invalid params **/
		String rndFileName = rndFile_part.getSubmittedFileName();
		if (rndFileName.isEmpty() || rndFileName == null){
			respWriter.println("No rndFile!");
		} else {
			if (checkFileName(rndFileName)){
				writePart(rndFile_part);
				rndFile = upload_directory + rndFileName;
				String jobTicket = bitcounts + SP + testTargets + SP + rndFile  + SP + "0" + SP +iterations;
				if (isAscii.equals("binary")){
					jobTicket = jobTicket + SP + "1";
				} else {
					jobTicket = jobTicket + SP + "0";
				}
				
				if (trans.equals("tranba")) {
					jobTicket = jobTicket + SP + "1";
				} else {
					jobTicket = jobTicket + SP + "0";
				}
				jobWriter.write(jobTicket.getBytes());
				jobWriter.flush();
				jobWriter.close();
				respWriter.println("JobID: " + thisJobID);
				
			} else {
				respWriter.println("The filename of your file should only contains alphanumeric characters ");
			}
		}
	}
	
	private boolean checkFileName(String fileName){
		String regex = "^[a-zA-Z0-9.]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
	private void writePart(Part filePart) throws IOException {
		InputStream in = filePart.getInputStream();
		OutputStream out = new FileOutputStream(upload_directory + filePart.getSubmittedFileName());
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = in.read(buffer)) != -1){
			out.write(buffer, 0, length);
		}
		out.flush();
		in.close();
		out.close();
	}
	
	

}
