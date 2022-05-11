package FlyPal_login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven1 
{

	public static void main(String[] args) throws IOException
	{
        //Path
		FileInputStream file = new FileInputStream("D://Amar_TC//Requisition_Data.xlsx/"); 
		
		//To read data from workbook
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		//Providing Sheet name to read.
		XSSFSheet sheet = workbook.getSheet("Sheet2");
		
		//Returns the Row count.
		int rowcount = sheet.getLastRowNum();
		
		//Returns the column/cell count.
		int colcount = sheet.getRow(0).getLastCellNum();
		
		//For loop 
		for (int i=0;i<rowcount;i++)
		{
			XSSFRow currentrow = sheet.getRow(1);
			
			//For loop (To read all the cells values from each row.
			for (int k=0;k<colcount;k++)
			{
				//To read the value from the cell.
				String value = currentrow.getCell(k).toString(); 
				
				System.out.print(" "  +value);
			}
			System.out.println();
			
		}
		
	}

}
