/**
 * 
 */
package com.ppqa.cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Saurabh Kumar
 *
 */
public class ReadExcel {

	static String getModuleNumber(String moduleName) throws IOException {
		moduleName=moduleName.toLowerCase().replace("_mts", "");

		String moduleNo1 = null;
		if (moduleName.toLowerCase().startsWith("are_")) {
			moduleName = moduleName.replace("are_", "");
			moduleName = "are\\" + moduleName.toLowerCase();
		}
		if (moduleName.toLowerCase().startsWith("fcp_a_")) {
			moduleName = moduleName.replace("fcp_a_", "");

			moduleName = "fcp_a\\" + moduleName.toLowerCase();
		}
		if (moduleName.toLowerCase().startsWith("fcp_b_")) {
			moduleName = moduleName.replace("fcp_b_", "");

			moduleName = "fcp_b\\" + moduleName.toLowerCase();
		} else {
			moduleName = moduleName.toLowerCase();

		}

		File myFile = new File("Input/OPSW_MTS_S3.2.4_PR2443 (1).xlsx");
		FileInputStream fis = new FileInputStream(myFile);
		// Finds the workbook instance for XLSX file
		XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
		// Return first sheet from the XLSX workbook XSSFSheet
		XSSFSheet mySheet = myWorkBook.getSheet("Tests4Review");
		// Get iterator to all the rows in current sheet
		java.util.Iterator<Row> rowIterator = mySheet.iterator();
		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			if (row.getRowNum() < 7) {
				continue;
			}
			Cell moduleNo = row.getCell(5);
			Cell status = row.getCell(6);
			Cell moduleName2 = row.getCell(0);
			
			
			if (moduleName2 != null && moduleNo != null && status != null) {
				
				if (moduleName2.getStringCellValue().contains(moduleName)
						&& status.getStringCellValue().equals("Completed")) {

					moduleNo1 = moduleNo.getStringCellValue();
					break;
				}
			}

		}

		return moduleNo1;
	}

}
