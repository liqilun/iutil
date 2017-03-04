package com.luncode.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSImporterUtil {

	public static Workbook importPath(String fileName) {
		return importFile(new File(fileName));
	}

	public static Workbook importFile(File file) {
		try {
			return importHSSF(new BufferedInputStream(new FileInputStream(file)));
		} catch (Exception e) {
			try{
				return importXSSF(new BufferedInputStream(new FileInputStream(file)));
			}catch (Exception e1) {
				throw new IllegalArgumentException(e1);
			}
		}
	}

	public static Workbook importXSSF(InputStream inputStream) {
		try {
			Workbook workbook = new XSSFWorkbook(inputStream);
			return workbook;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static Workbook importHSSF(InputStream inputStream) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			Workbook workbook = new HSSFWorkbook(fs);
			return workbook;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}
}
