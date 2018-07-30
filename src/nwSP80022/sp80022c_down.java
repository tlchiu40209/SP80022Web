package nwSP80022;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sp80022c-down.do")

public class sp80022c_down extends HttpServlet{
	private final String home_directory = "/home/sp80022/sts2/";
	private final String download_directory = "/home/sp80022/sts2/download/";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		OutputStream outStream = resp.getOutputStream();
		//PrintWriter respWriter = resp.getWriter();
		
		String jobID = req.getParameter("jobID");
		if (jobID.isEmpty() || jobID == null){
			String msg = "No jobID specified!";
			outStream.write(msg.getBytes());
			//respWriter.println("No JobID specified!");
		} else {
			if (!checkInteger(jobID)){
				String msg = "jobID shouldn't contains non-numerical characters";
				outStream.write(msg.getBytes());
				//respWriter.println("jobID shouldn't contains non-numerical characters");
			} else if (jobID.length() != 13) {
				String msg = "Bad jobID";
				outStream.write(msg.getBytes());
				//respWriter.println("Bad jobID");
			} else {
				String filePath = download_directory + jobID + ".tar.gz";
				File file = new File(filePath);
				if (file.exists()){
					FileInputStream inStream = new FileInputStream(file);
					resp.setContentType("application/octet-stream");
					resp.setContentLength((int)file.length());
					String headerKey = "Content-Disposition";
					String headerValue = "attachment;filename=" + file.getName();
					//String headerValue = String.format("attachment;filename=\"%s\"", file.getName());
					resp.setHeader(headerKey, headerValue);
					
					
					
					byte[] buffer = new byte[4096];
					int bytesRead = -1;
					while ((bytesRead = inStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
						outStream.flush();
					}
					
					inStream.close();
					outStream.close();
					file.delete();
					
				} else {
					String msg = "Your job is either downloaded or still under process, please try again later.";
					outStream.write(msg.getBytes());
					//respWriter.println("Your job is either downloaded or still under process, please try again later.");
				}
			}
		}
		
		
		
	}
	
	private boolean checkInteger(String text){
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	
	

}
