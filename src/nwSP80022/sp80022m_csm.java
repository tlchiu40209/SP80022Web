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
@WebServlet("/sp80022m-csm.do")

public class sp80022m_csm extends HttpServlet {
	
	private final String home_directory = "/home/sp80022/sts3/";
	private final String upload_directory = "/home/sp80022/sts3/upload/";
	private final String download_directory = "/home/sp80022/sts3/download/";
	private final String work_directory = "/home/sp80022/sts3/working/";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		OutputStream respOut = resp.getOutputStream();
		
		/** jobTicket Param **/
		Part rndFile_part = req.getPart("rndFile");
		String rndFileName = rndFile_part.getSubmittedFileName();
		String isAscii = req.getParameter("isAscii");
		String trans = req.getParameter("transRequest");
		boolean checkLock = true;
		if (rndFileName.isEmpty() || rndFileName == null){
			respOut.write("rndFile not specified!".getBytes());
			checkLock = false;
		} else {
			if (!checkFileName(rndFileName)){
				respOut.write("The filename of your file should only contains alphanumeric characters!".getBytes());
				checkLock = false;
			} else {
				writePart(rndFile_part);
			}
		}

		if (isAscii.isEmpty() || isAscii == null) {
			respOut.write("File Type is not specified!".getBytes());
			checkLock = false;
		} else {
			switch(isAscii){
			case "ascii":
				break;
			case "binary":
				break;
			default:
				respOut.write("Bad File Type is specified!".getBytes());
				checkLock = false;
				break;
			}
		}

		if (trans.isEmpty() || trans == null) {
			respOut.write("Trans service not specified".getBytes());
			checkLock = false;
		} else {
			switch (trans) {
				case "tranba":
					if ( rndFileName.isEmpty() || rndFileName == null){
						respOut.write("You did not specified a file!".getBytes());
						checkLock = false;
					} else {
						File oriFile = new File((upload_directory + rndFileName));
						File aciFile = new File((upload_directory + rndFileName + ".aci"));
						FileInputStream oriFis = new FileInputStream(oriFile);
						FileOutputStream aciFos = new FileOutputStream(aciFile);
						byte[] oriByte = new byte[1];
						for (int i = 0; i < oriFile.length(); i++){
							oriFis.read(oriByte);
							aciFos.write((byteToBits(oriByte[0])).getBytes());
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
		if (checkLock){
			byte[] voidSpace = new byte[4096];
			String[] mkdirCmd = new String[]{"/bin/mkdir", work_directory + rndFileName};
			Process mkdir = Runtime.getRuntime().exec(mkdirCmd);
			try {
				mkdir.waitFor();
				InputStream mkdirIn = mkdir.getInputStream();
				InputStream mkdirEn = mkdir.getErrorStream();
				while (mkdirIn.available() != 0){
					mkdirIn.read(voidSpace);
				}
				while (mkdirEn.available() != 0){
					mkdirEn.read(voidSpace);
				}
				mkdir.destroy();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			String[] sysCmd = null;
			switch (isAscii){
			case "ascii":
				sysCmd = new String[]{home_directory + "sts", "-s", "-t", "0", "-w", work_directory + rndFileName, "-F", "a", upload_directory + rndFileName};
				break;
			case "binary":
				sysCmd = new String[]{home_directory + "sts", "-s", "-t", "0", "-w", work_directory + rndFileName, "-F", "r", upload_directory + rndFileName};
				break;
			}
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
			
			String[] zipCmd = new String[]{"/usr/bin/tar", "-zcvf", download_directory + rndFileName + ".tar.gz", "-C", work_directory, rndFileName};
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
			/** Clean up**/
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
			while((bytesRead = inStream.read(buffer)) != -1){
				respOut.write(buffer, 0, bytesRead);
				respOut.flush();
			}
			inStream.close();
			respOut.close();
			downloadFile.delete();
			checkLock=false;
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

	private boolean checkFileName(String fileName){
		String regex = "^[a-zA-Z0-9.]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
	
	private String byteToBits (byte this_byte) {
		String bitString = String.format("%8s", Integer.toBinaryString(this_byte & 0xFF)).replace(' ', '0');
		return bitString;
	}
	
	

}
