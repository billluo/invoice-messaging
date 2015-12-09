package com.rh.invoicelog;

import java.io.File;

public class InvoiceBadFile implements ErrorFileAPI{
	private String badFileName;
	public InvoiceBadFile(String badFileName) {
		this.badFileName = badFileName;
	}
	@Override
	public File getReportFile(){
		String workingDirectory = System.getProperty("user.dir");
		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + badFileName;
	
		return new File(absoluteFilePath);	
	}
}
