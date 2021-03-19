package com.SpiceJetTest;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile 
{
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public ReadExcelFile(String excelFilePath)
	{
		try
		{
			File src = new File(excelFilePath);
			FileInputStream file = new FileInputStream(src);
			workbook = new XSSFWorkbook(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Object getData(int sheetnumber, int row, int column)
	{
		sheet = workbook.getSheetAt(sheetnumber);
		String data = sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
	}
	
	
	public int getRowCount(int sheetIndex)
	{
		int row = workbook.getSheetAt(sheetIndex).getLastRowNum();
		row = row+1;
		return row;
	}
	
}
