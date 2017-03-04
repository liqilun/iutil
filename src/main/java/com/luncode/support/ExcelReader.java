package com.luncode.support;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelReader {
	ResultCode importExcel(Workbook workbook);
}
