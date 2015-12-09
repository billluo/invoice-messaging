package com.rh.invoicelog;

import java.io.File;

public class ErrorLogFile implements ErrorFileAPI{
	private String errorFileStr;
	public ErrorLogFile(String errorFileStr) {
		this.errorFileStr = errorFileStr;
	}
	@Override
	public File getReportFile(){
		String workingDirectory = System.getProperty("user.dir");
		String absoluteFilePath = "";
		absoluteFilePath = workingDirectory + File.separator + errorFileStr;
	
		return new File(absoluteFilePath);	
	}
}
