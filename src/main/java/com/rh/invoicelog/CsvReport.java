package com.rh.invoicelog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.logging.Logger;
import java.util.List;
import com.rh.invoice.domain.ErrorLog;
/**
 * @author bluo
 *
 */

public class CsvReport implements ReportStrategy{

	/**
	 * errorLogFile: error file attached name
	 * badFile: bad file from SQL loader
	 * errorLogs: error entries from database
	 */
	private static final String csvSeparator = ",";
	private ErrorFileAPI errorLogFile;
	private ErrorFileAPI badFile;
	private List<ErrorLog> errorLogs;
	
	public CsvReport(ErrorLogFile errorLogFile, InvoiceBadFile badFile) {
        this.errorLogFile = errorLogFile;
        this.badFile = badFile;
        this.errorLogs =new ArrayList<ErrorLog>();
	}	
	public ErrorFileAPI getErrorLogFile() {
		return errorLogFile;
	}


	private void setErrorEntries(List<ErrorLog> recErrorLogs) {
		this.errorLogs = recErrorLogs;
	}
	
	@Override
	public void createReport(List<ErrorLog> recErrorLogs) {
		//create buffered writer for header and lines
		setErrorEntries(recErrorLogs);
		try {
			BufferedWriter bWriter = new BufferedWriter(
								new FileWriter( this.errorLogFile.getReportFile()));
			addHeader(bWriter);
			addLines(bWriter);
			if (badFile.getReportFile().exists()) {
				addBadFile(bWriter);
			}
			bWriter.flush();
			bWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addHeader(BufferedWriter bWriter) throws IOException {
		StringBuffer headerLine = new StringBuffer();
//		headerLine.append("Process Name");
//		headerLine.append(csvSeparator);
		headerLine.append("Invoice Number");
		headerLine.append(csvSeparator);
		headerLine.append("Error Column");
		headerLine.append(csvSeparator);
		headerLine.append("Error Value");
		headerLine.append(csvSeparator);
		headerLine.append("Description");
		headerLine.append(csvSeparator);
		headerLine.append("File Name");
		bWriter.write(headerLine.toString());
		bWriter.newLine();
		bWriter.flush();
	}
	
	public void addLines(BufferedWriter bWriter) throws IOException {
		for (ErrorLog errorLog : this.errorLogs) {
			StringBuffer reportLines = new StringBuffer();
			reportLines.append(errorLog.getInvoiceNumber());
			reportLines.append(csvSeparator);
			reportLines.append(errorLog.getErrorColumn());
			reportLines.append(csvSeparator);
			reportLines.append(errorLog.getErrorValue());
			reportLines.append(csvSeparator);
			reportLines.append(errorLog.getDescription());
			reportLines.append(csvSeparator);
			reportLines.append(errorLog.getFileName());
			bWriter.write(reportLines.toString());
			bWriter.newLine();
		}		
	}
	
	public void addBadFile(BufferedWriter bWriter) throws IOException {
		//read the bad file and append at the end of this file
		BufferedReader bReader = null;		
		try {
			 bReader = new BufferedReader(
							new FileReader(this.badFile.getReportFile()));
			 bWriter.write(System.getProperty("line.separator"));
			 bWriter.write("below are data format error-- '20/20/2015' as date etc.");
			 bWriter.write(System.getProperty("line.separator"));
			 int c;
			 while ((c = bReader.read()) != -1) {
				bWriter.write(c);				
			}	
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("error when creating content");
		} finally{
			if (bReader!=null){
				bReader.close();
			}
		}
	}	
}
