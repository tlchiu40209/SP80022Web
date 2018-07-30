package nwSP80022;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location="/home/sp80022/sts3/upload")
@WebServlet("/sp80022m-adv.do")

public class sp80022m_adv extends HttpServlet{
	
	private final String home_directory = "/home/sp80022/sts3/";
	private final String upload_directory = "/home/sp80022/sts3/upload/";
	private final String download_directory = "/home/sp80022/sts3/download/";
	private final String work_directory = "/home/sp80022/sts3/working/";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		OutputStream respOut = resp.getOutputStream();
		
		/** jobTicket Params **/
		String bitCounts = req.getParameter("bitCounts");
		String iterations = req.getParameter("iterations");
		Part rndFile_part = req.getPart("rndFile");
		String rndFileName = rndFile_part.getSubmittedFileName();
		String isAscii = req.getParameter("isAscii");
		String trans = req.getParameter("transRequest");
		
		String blockFreq = req.getParameter("blockFreq");
		String novLapping = req.getParameter("novLapping");
		String ovLapping = req.getParameter("ovLapping");
		String approxEnt = req.getParameter("approxEnt");
		String srTest = req.getParameter("srTest");
		String linComplex = req.getParameter("linComplex");
		String uniBins = req.getParameter("uniBins");
		String uniCut = req.getParameter("uniCut");
		String alphaConf = req.getParameter("alphaConf");
		
		boolean checkLock = true;
		
		if (bitCounts.isEmpty() || bitCounts == null) {
			respOut.write("bitCounts not spcified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(bitCounts)){
				respOut.write("BitCounts contains non-numerical characters".getBytes());
				checkLock = false;
			} else {
				int bitCounts_int = Integer.parseInt(bitCounts);
				if (bitCounts_int < 1){
					checkLock = false;
					respOut.write("BitCounts should not be less than 1 or a negative number".getBytes());
				}
			}
		}
		
		if (iterations.isEmpty() || iterations == null){
			respOut.write("Iterations not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(iterations)){
				respOut.write("Iterations contains non-numerical characters".getBytes());
			} else {
				int iteration_int = Integer.parseInt(iterations);
				if (iteration_int < 1){
					checkLock = false;
					respOut.write("Iterations should not be less than 1 or a negative number".getBytes());
				}
			}
		}
		
		if (rndFileName.isEmpty() || rndFileName == null) {
			respOut.write("rndFile not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkFileName(rndFileName)){
				respOut.write("The filename of your file should only contains alphanumeric character!".getBytes());
				checkLock = false;
			} else {
				writePart(rndFile_part);
			}
		}
		
		if (blockFreq.isEmpty() || blockFreq == null){
			respOut.write("blockFreq not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(blockFreq)){
				respOut.write("blockFreq contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int blockFreq_int = Integer.parseInt(blockFreq);
				if (blockFreq_int < 1){
					respOut.write("blockFreq shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (novLapping.isEmpty() || novLapping == null){
			respOut.write("novLapping not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(novLapping)){
				respOut.write("novLapping contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int novLapping_int = Integer.parseInt(novLapping);
				if (novLapping_int < 1){
					respOut.write("novLapping shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (ovLapping.isEmpty() || ovLapping == null){
			respOut.write("ovLapping not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(ovLapping)){
				respOut.write("ovLapping contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int ovLapping_int = Integer.parseInt(ovLapping);
				if (ovLapping_int < 1){
					respOut.write("ovLapping shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (approxEnt.isEmpty() || approxEnt == null){
			respOut.write("approxEnt not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(approxEnt)){
				respOut.write("approxEnt contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int approxEnt_int = Integer.parseInt(approxEnt);
				if (approxEnt_int < 1){
					respOut.write("approxEnt shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (srTest.isEmpty() || srTest == null){
			respOut.write("srTest not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(srTest)){
				respOut.write("srTest contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int srTest_int = Integer.parseInt(srTest);
				if (srTest_int < 1){
					respOut.write("srTest shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (linComplex.isEmpty() || linComplex == null){
			respOut.write("linComplex not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkInteger(linComplex)){
				respOut.write("linComplex contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				int linComplex_int = Integer.parseInt(linComplex);
				if (linComplex_int < 1){
					respOut.write("linComplex shouldn't less than 1 or a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		//uniBins uses different method to check
		if (uniBins.isEmpty() || uniBins == null){
			respOut.write("uniBins not specified".getBytes());
			checkLock = false;
		} else {
			switch(uniBins){
			case "sqrt":
				break;
			case "10":
				break;
			default:
				respOut.write("Bad uniBins configuration".getBytes());
				checkLock = false;
				break;
			}
		}
		
		
		if (uniCut.isEmpty() || uniCut == null){
			respOut.write("uniCut not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkFloat(uniCut)){
				respOut.write("uniCut contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				float uniCut_float = Float.parseFloat(uniCut);
				if (uniCut_float < 0){
					respOut.write("uniCut shouldn't be a negative number".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (alphaConf.isEmpty() || alphaConf == null){
			respOut.write("alphaConf not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkFloat(alphaConf)){
				respOut.write("alphaConf contains non-numerical charaters!".getBytes());
				checkLock = false;
			} else {
				float alphaConf_float = Float.parseFloat(alphaConf);
				if (alphaConf_float > 1 || alphaConf_float <= 0){
					respOut.write("Invalid alphaConf value".getBytes());
					checkLock = false;
				}
			}
		}
		
		if (isAscii.isEmpty() || isAscii == null) {
			respOut.write("File type is not specified!".getBytes());
			checkLock = false;
		} else {
			switch (isAscii){
			case "ascii":
				break;
			case "binary":
				break;
			default:
				respOut.write("Bad File Type is specified!".getBytes());
				break;
			}
		}
		
		if (trans.isEmpty() || trans == null) {
			respOut.write("Trans service not specified".getBytes());
			checkLock = false;
		} else {
			switch (trans){
			case "tranba":
				if (rndFileName.isEmpty() || rndFileName == null){
					respOut.write("You did not specified a file!".getBytes());
					checkLock = false;
				} else {
					File oriFile = new File((upload_directory + rndFileName));
					File aciFile = new File((upload_directory + rndFileName + ".aci"));
					FileInputStream oriFis = new FileInputStream(oriFile);
					FileOutputStream aciFos = new FileOutputStream(aciFile);
					byte[] oriByte = new byte[1];
					for (int i =0; i < oriFile.length(); i++){
						oriFis.read(oriByte);
						aciFos.write(byteToBits(oriByte[0]).getBytes());
						aciFos.write("\n".getBytes());
					}
					aciFos.flush();
					oriFis.close();
					aciFos.close();
					oriFile.delete();
					rndFileName = rndFileName + ".aci";
				}
				break;
			case "notran":
				break;
			default:
				respOut.write("Bad trans Service specified!".getBytes());
				checkLock = false;
				break;
			}
		}
		
		/*
		if (checkLock == true){
			respOut.write("True".getBytes());
		} else {
			respOut.write("False".getBytes());
		}
		*/
		
		if (checkLock){
			byte[] voidSpace = new byte[4096];
			String[] mkdirCmd = new String[]{"/bin/mkdir", work_directory + rndFileName};
			Process mkdir = Runtime.getRuntime().exec(mkdirCmd);
			try {
				mkdir.waitFor();
				InputStream mkdirIn = mkdir.getInputStream();
				InputStream mkdirEn = mkdir.getErrorStream();
				while (mkdirIn.available() != 0) {
					mkdirIn.read(voidSpace);
				}
				while (mkdirEn.available() != 0) {
					mkdirEn.read(voidSpace);
				}
				mkdir.destroy();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			String[] sysCmd = null;
			if (isAscii.equals("ascii")){
				String params = null;
				if (uniBins.equals("sqrt")){
					params = "1=" + bitCounts + ",2=" + novLapping + ",3=" + ovLapping + ",4=" + approxEnt + ",5=" + srTest + ",6=" + linComplex + ",10=" + uniCut + ",11=" + alphaConf;
					//respOut.write(params.getBytes());
				} else if (uniBins.equals("10")){
					params = "1=" + bitCounts + ",2=" + novLapping + ",3=" + ovLapping + ",4=" + approxEnt + ",5=" + srTest + ",6=" + linComplex + ",8=10" + ",10=" + uniCut + ",11=" + alphaConf;
					//respOut.write(params.getBytes());
				}
				sysCmd = new String[]{home_directory + "sts", "-s", "-S", bitCounts, "-i", iterations, "-P" , params ,"-t", "0", "-w", work_directory + rndFileName, "-F", "a", upload_directory + rndFileName};
				
			} else if (isAscii.equals("binary")){
				String params = null;
				if (uniBins.equals("sqrt")){
					params = "1=" + bitCounts + ",2=" + novLapping + ",3=" + ovLapping + ",4=" + approxEnt + ",5=" + srTest + ",6=" + linComplex + ",10=" + uniCut + ",11=" + alphaConf;
					//respOut.write(params.getBytes());
				} else if (uniBins.equals("10")){
					params = "1=" + bitCounts + ",2=" + novLapping + ",3=" + ovLapping + ",4=" + approxEnt + ",5=" + srTest + ",6=" + linComplex + ",8=10" + ",10=" + uniCut + ",11=" + alphaConf;
					//respOut.write(params.getBytes());
				}
				
				sysCmd = new String[]{home_directory + "sts", "-s", "-S", bitCounts, "-i", iterations, "-P" , params ,"-t", "0", "-w", work_directory + rndFileName, "-F", "r", upload_directory + rndFileName};
			}
			//respOut.write(uniBins.getBytes());
			
			/*
			for (int i = 0; i < sysCmd.length; i++){
				respOut.write((sysCmd[i]).getBytes());
				respOut.write(" ".getBytes());
			}*/
			
			Process sys = Runtime.getRuntime().exec(sysCmd);
			try {
				sys.waitFor();
				InputStream sysIn = sys.getInputStream();
				InputStream sysEn = sys.getErrorStream();
				while (sysIn.available() != 0){
					sysIn.read(voidSpace);
				}
				while (sysEn.available() != 0){
					sysEn.read(voidSpace);
				}
				sys.destroy();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			/*
			
			for (int i = 0; i < sysCmd.length; i++){
				respOut.write((sysCmd[i]).getBytes());
				respOut.write(" ".getBytes());
			}
			*/
			
			String zipCmd[] = new String[]{"/usr/bin/tar", "-zcvf", download_directory + rndFileName + ".tar.gz", "-C", work_directory, rndFileName};
			Process zip = Runtime.getRuntime().exec(zipCmd);
			try {
				zip.waitFor();
				InputStream zipIn = zip.getInputStream();
				InputStream zipEn = zip.getErrorStream();
				while (zipIn.available() != 0){
					zipIn.read(voidSpace);
				}
				while (zipEn.available() != 0){
					zipEn.read(voidSpace);
				}
				zip.destroy();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			/** Clean up **/
			File testFile = new File(upload_directory + rndFileName);
			testFile.delete();
			String[] removeWork = new String[]{"/bin/rm", "-r", "-f", work_directory + rndFileName};
			Process remove = Runtime.getRuntime().exec(removeWork);
			try {
				remove.waitFor();
				InputStream removeIn = remove.getInputStream();
				InputStream removeEn = remove.getErrorStream();
				while (removeIn.available() != 0){
					removeIn.read(voidSpace);
				}
				while (removeEn.available() != 0){
					removeEn.read(voidSpace);
				}
				remove.destroy();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			
			String filePath = download_directory + rndFileName + ".tar.gz";
			File downloadFile = new File(filePath);
			FileInputStream inStream = new FileInputStream(downloadFile);
			resp.setContentType("application/octet-stream");
			resp.setContentLength((int)downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = "attachment;filename=" + downloadFile.getName();
			resp.setHeader(headerKey, headerValue);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inStream.read(buffer)) != -1) {
				respOut.write(buffer, 0, bytesRead);
				respOut.flush();
			}
			inStream.close();
			respOut.close();
			downloadFile.delete();
			checkLock = false;
		}
	}
	
	private void writePart(Part filePart) throws IOException {
		InputStream in = filePart.getInputStream();
		OutputStream out = new FileOutputStream(upload_directory + filePart.getSubmittedFileName());
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		out.flush();
		in.close();
		out.close();
	}
	
	private boolean checkInteger(String text){
		String regex = "^[0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	private boolean checkFloat(String text){
		String regex = "^[0-9.]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	private boolean checkFileName(String fileName) {
		String regex = "^[a-zA-Z0-9.]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
	private String byteToBits(byte this_byte) {
		String bitString = String.format("%8s", Integer.toBinaryString(this_byte & 0xFF)).replace(' ', '0');
		return bitString;
	}
}
