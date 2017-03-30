package project5;
import java.lang.Object;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Betweenness2 {
	static String PittFolder="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/";
	static String PittEdge="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv";
	static String PittEdge1="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges1.csv";
	static ArrayList<String> al;
	static long MaxCol=1600;
	static long MaxRow=1000000;
	static int colNum=0;
	static int rowNum=0;
	static int FileX=0;
	static int FileY=0;
	static int FileP=0;
	static ArrayList<Workbook> fileMatrix=new ArrayList<Workbook>();
	static long DistanceRow;
	static long[] node;
	static TreeSet<Long> set=new TreeSet();
	static TreeSet<Long> existSet=new TreeSet();
	static int[] LookforP(String filename,long x,long y) throws IOException
	{
 		XSSFWorkbook workbook;
		XSSFSheet worksheet;
		XSSFRow XLSXrow1;
 		int j=0;
 		int index[]=new int[2];
 		index[0]=-1;
 		index[1]=-1;
 		for(int i=0;i<FileP+1;i++)
 		{
 			String excelFileName=filename;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+i;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+j;
			excelFileName=excelFileName+".xlsx";
			workbook = new XSSFWorkbook(excelFileName);
			worksheet = workbook.getSheet("Sheet1");
// 			if(i==2)
// 				System.out.println(x);
 	 		int indexX=0;
 			while((XLSXrow1 = worksheet.getRow(indexX))!=null)
 			{
		    	long node1=(long)XLSXrow1.getCell((short) 0).getNumericCellValue();
		    	long node2=(long)XLSXrow1.getCell((short) 1).getNumericCellValue();
		    	long dist=(long)XLSXrow1.getCell((short) 2).getNumericCellValue();
		    	
 				if(node1==x&&node2==y){
 			    	workbook.close();
 	 				index[1]=indexX;
 	 				index[0]=i;
 			    	return index;
 				}
 				indexX=indexX+1;
 			}
	    	workbook.close();
 		}
	    return index;
	}
	static void UpdateP(String filename,long node1,long node2,long node3) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
 		int[] indexP=LookforP(filename,node1,node2);
 		long loc;
 		int fileIndex;
 		if(indexP[0]<0){
 			loc=GetLength(filename+FileP+".xlsx");
 			fileIndex=FileP;
 		}else{
 			fileIndex=indexP[0];
 			loc=indexP[1];
 		}
		InputStream inp = new FileInputStream(filename+"P"+fileIndex+".xlsx");
 		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow((int)loc);
		if(row==null)
			row=sheet.createRow((int)loc);
		Cell cell = row.getCell(0);   
		if (cell == null)
		    cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(node1);

		cell = row.getCell(1);   
		if (cell == null)
		    cell = row.createCell(1);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(node2);
		
		cell = row.getCell(2);   
		if (cell == null)
		    cell = row.createCell(2);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(node3);
	
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(filename+"P"+fileIndex+".xlsx");
		wb.write(fileOut);
		fileOut.close();
 			
 		
	}
	static int findInterv(String filename,long node1,long node2,int level) throws IOException
	{
		XSSFWorkbook workbook;
		int betCen=0;
		XSSFSheet worksheet;
		XSSFRow XLSXrow1;
		XSSFCell XLSXCell;
 		int j=0;
 		int index[]=new int[2];
 		index[0]=-1;
 		index[1]=-1;
 		for(int i=0;i<FileP+1;i++)
 		{
 			String excelFileName=filename;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+i;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+j;
			excelFileName=excelFileName+".xlsx";
			workbook = new XSSFWorkbook(excelFileName);
			worksheet = workbook.getSheet("Sheet1");
// 			if(i==2)
// 				System.out.println(x);
 	 		int indexX=0;
 	 		int indexY=0;
 			while((XLSXrow1 = worksheet.getRow(indexX))!=null)
 			{
// 				while((XLSXCell=XLSXrow1.getCell((short)indexY))!=null)
// 				{
// 					
// 				}
 				
 				if((long)XLSXrow1.getCell((short) 0).getNumericCellValue()==node1&&(long)XLSXrow1.getCell((short) 2).getNumericCellValue()==node2){
 					workbook.close();
 					level=findInterv(filename,node1,(long)XLSXrow1.getCell((short) 1).getNumericCellValue(),level+1);
 				}
 				indexX=indexX+1;
 				
		    	
			}
	    	workbook.close();
		}
 		return level;
	}
	static int calBet(String filename,long node1) throws IOException
	{
		XSSFWorkbook workbook;
		int betCen=0;
		XSSFSheet worksheet;
		XSSFRow XLSXrow1;
		XSSFCell XLSXCell;
 		int j=0;
 		int index[]=new int[2];
 		index[0]=-1;
 		index[1]=-1;
 		for(int i=0;i<FileP+1;i++)
 		{
 			String excelFileName=filename;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+i;
			excelFileName=excelFileName+"_";
			excelFileName=excelFileName+j;
			excelFileName=excelFileName+".xlsx";
			workbook = new XSSFWorkbook(excelFileName);
			worksheet = workbook.getSheet("Sheet1");
// 			if(i==2)
// 				System.out.println(x);
 	 		int indexX=0;
 	 		int indexY=0;
 			while((XLSXrow1 = worksheet.getRow(indexX))!=null)
 			{
// 				while((XLSXCell=XLSXrow1.getCell((short)indexY))!=null)
// 				{
// 					
// 				}
 				
 				if((long)XLSXrow1.getCell((short) 2).getNumericCellValue()==node1)
 					betCen=findInterv(filename,(long)XLSXrow1.getCell((short) 0).getNumericCellValue(),(long)XLSXrow1.getCell((short) 1).getNumericCellValue(),0);
 				indexX=indexX+1;
 			}
	    	workbook.close();
 		}
 		return betCen;
	}
	static void Floyd_2D() throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		for(int k = 0;k<node.length;k++){
			for(int i = 0; i<node.length;i++){
				for(int j = 0;j<node.length;j++){
					double distik[]=findDistance(PittFolder+"distance",node[i],node[k]);
					double distkj[]=findDistance(PittFolder+"distance",node[k],node[j]);
					double distij[]=findDistance(PittFolder+"distance",node[i],node[j]);
					System.out.println("distik: "+distik[2]+", distkj: "+distik[2]+", distij: "+distij[2]);
					k=k;
					if((distik[2]+distkj[2])<distij[2]){
						UpdateP(PittFolder+"distance",node[i],node[j],node[k]);
						double newDist= findDistance(PittFolder+"distance",node[i],node[k])[2]+findDistance(PittFolder+"distance",node[k],node[j])[2];
						UpdateDistance(PittFolder+"distance",node[i],node[j],newDist); 
					}
				
				}
			
			}
		
		}
	}
	static void UpdateDistance(String filename,long node1,long node2,double dist) throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		double[] indexDist=findDistance(filename,node1,node2);
 		long loc;
 		int fileIndex;
 		if(indexDist[0]<0){
 			loc=GetLength(filename+FileX+".xlsx");
 			fileIndex=FileX;
 		}else{
 			fileIndex=(int)indexDist[0];
 			loc=(int)indexDist[1];
 		}
		InputStream inp = new FileInputStream(filename+"P"+fileIndex+".xlsx");
 		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow((int)loc);
		if(row==null)
			row=sheet.createRow((int)loc);
		Cell cell = row.getCell(0);   
		if (cell == null)
		    cell = row.createCell(0);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(node1);

		cell = row.getCell(1);   
		if (cell == null)
		    cell = row.createCell(1);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(node2);
		
		cell = row.getCell(2);   
		if (cell == null)
		    cell = row.createCell(2);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(dist);
	
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(filename+"P"+fileIndex+".xlsx");
		wb.write(fileOut);
		fileOut.close();
 			
	}
 	public static int BinarySearch (long[] a, int low, int high, long searchValue)
	{
		int mid;
		if (high <= low)
		return -1;
			mid = (low + high) /2; 
		if (a [mid] > searchValue)
		{
			return BinarySearch (a, low, mid, searchValue);
		}
		else if (a [mid] < searchValue)
		{
			return BinarySearch (a, mid + 1, high, searchValue);
		}
		else 
		{
			return mid;
		}
	}
	public static long[] InputNode()
	{
        // -define .csv file in app
        String fileNameDefined = "/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/nodes.csv";
        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);
        try{
            // -read from filePooped with Scanner class
            Scanner inputStream = new Scanner(file);
            // hashNext() loops line-by-line
            boolean first=true;
            while(inputStream.hasNext()){
                //read single line, put in string
                String data = inputStream.next();
                String str[]=data.split(",");
//                System.out.println(str[0]);
                if(first){
                	first=false;
                }
                else
                	set.add(Long.parseLong(str[0]));
            }
            // after loop, close scanner
            inputStream.close();


        }catch (FileNotFoundException e){

            e.printStackTrace();
        }
        

//		long x=0;
//		int j=0;
        node=new long[set.size()];
        int i=0;
        for(long value:set)
        {
        	node[i]=value;
        	i=i+1;
        }
//        for(int i=0;i<set.size();i++)
//        {
//        	set.
//        	x=set.get((Long)i);
////        	node[i]=nodeal.get(i);
//			j=i-1;
//			while(j>=0&&node[j]>x)
//			{
//				node[j+1]=node[j];
//				j=j-1;
//			}
//			node[j+1]=x;
//        }
		return node;
	}
	public static void CopyDistance(String filename) throws IOException
	{
//		boolean first=true;
// 		String csvFilename="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv";
//	    CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
	    String[] CSVrow = null;
		long node1;
    	long node2;
    	int access1;
    	int access2;
    	double dist;
    	XSSFWorkbook wb=new XSSFWorkbook();
    	XSSFSheet sheet = wb.createSheet("Sheet1") ;
		
		
		String excelFileName=filename+"_0_0.xlsx";
		DistanceRow=GetLength("/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv");
		int[] index=GetIndex(DistanceRow,3);
 		String csvFilename="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv";
	    CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
	    CSVrow = csvReader.readNext();//get rid of heading
	    int rowcount=0;
    	int j=0;
    	int i=0;
	    while((CSVrow = csvReader.readNext()) != null)
	    {
			node1=Long.parseLong(CSVrow[1]);
	    	node2=Long.parseLong(CSVrow[2]);
	    	dist=Double.parseDouble(CSVrow[3]);
	    	access1=Integer.parseInt(CSVrow[4]);
	    	access2=Integer.parseInt(CSVrow[5]);
	    	if(access1==0&&access2==0){
	    	}else{
				XSSFRow XLSXrow = sheet.createRow((int)rowcount);
				XSSFCell cell = XLSXrow.createCell(0);
				cell.setCellValue(node1);
				cell = XLSXrow.createCell(1);
				cell.setCellValue(node2);
				cell = XLSXrow.createCell(2);
				cell.setCellValue(dist);
				cell = XLSXrow.createCell(3);
				cell.setCellValue(access1);
				cell = XLSXrow.createCell(4);
				cell.setCellValue(access2);
				System.out.println("wirte "+rowcount+" line");
				rowcount=rowcount+1;
	    	}

	    	if(rowcount==MaxRow){
				FileOutputStream fileOut = new FileOutputStream(excelFileName);
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
				System.out.println("get one done");
	    		i=i+1;
				FileX=FileX+1;
	    		excelFileName=filename+"_"+i+"_"+j+".xlsx";
				wb = new XSSFWorkbook();
				sheet = wb.createSheet("Sheet1") ;
	    		rowcount=0;
	    	}
	    }

		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	    csvReader.close();
	}
	public static int[] GetIndex(long x,long y)
	{
		int filex;
		int filey;
		int row;
		int col;
		filex=(int)(x/MaxRow);
		filey=(int)(y/MaxCol);
		row=(int)(x % MaxRow);
		col=(int)(y % MaxCol);
		
		int[] file=new int[4];
		file[0]=filex;
		file[1]=filey;
		file[2]=row;
		file[3]=col;
		return file;
	}
	
	public static void WriteMatrix(long[] node)throws IOException 
	{
		String excelFileName="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/distance";

	    
 	    
 		BufferedReader br = null;
 		String line = "";
		br = new BufferedReader(new FileReader(PittEdge));
		br.readLine();
		int count=0;
		while ((line = br.readLine()) != null) {
			System.out.println("writting line "+count);
			count=count+1;
			String[] nodeArr = line.split(",");
			int x=0;
			int y=0;
			x=BinarySearch(node,0,node.length-1,Long.parseLong(nodeArr[1]));
			y=BinarySearch(node,0,node.length-1,Long.parseLong(nodeArr[2]));
			long getx=x;
			long gety=y;
			if(x>=0&&y>=0){
				int index[]=GetIndex(x,y);
				if(Integer.parseInt(nodeArr[4])>0){
					index=GetIndex(x,y);
					x= index[0];
					y =index[1];
					String sheetName = "Sheet1";//name of sheet
	
					Sheet sheet=fileMatrix.get(x*colNum+y).getSheetAt(0);
					if(sheet==null)
						sheet= fileMatrix.get(x*colNum+y).createSheet(sheetName) ;
					Row row= sheet.getRow(index[2]);
					if(row==null)
						row = sheet.createRow(index[2]);
					if(index[3]<0)
						System.out.println(getx+"  "+gety);
					Cell cell= row.getCell(index[3]);
					if(cell==null)
						cell = row.createCell(index[3]);
					cell.setCellValue(Double.parseDouble(nodeArr[3]));
					
				}
				if(Integer.parseInt(nodeArr[5])>0){
					index=GetIndex(y,x);
					x= index[0];
					y =index[1];
					String sheetName = "Sheet1";//name of sheet
					Sheet sheet=fileMatrix.get(x*colNum+y).getSheet(sheetName);
					if(sheet==null)
						sheet= fileMatrix.get(x*colNum+y).createSheet(sheetName) ;
					Row row= sheet.getRow(index[2]);
					if(row==null)
						row = sheet.createRow(index[2]);
					Cell cell= row.getCell(index[3]);
					if(cell==null)
						cell = row.createCell(index[3]);
					cell.setCellValue(nodeArr[3]);
				}
			}
			
			

		}
		for(int i=0;i<rowNum+1;i++){
			for(int j=0;j<colNum+1;j++){
				FileOutputStream fileOut = new FileOutputStream(excelFileName+"_"+i+"_"+j+".xlsx");
				fileMatrix.get(i*colNum+j).write(fileOut);
				fileOut.flush();
				fileOut.close();
			}
		}
 	    
		
	}
 	public static long GetLength(String filename) throws IOException
 	{
 		InputStream is = new BufferedInputStream(new FileInputStream(filename));
 	    byte[] c = new byte[1024];
 	    long count = 0;
 	    int readChars = 0;
 	    boolean empty = true;
 	    while ((readChars = is.read(c)) != -1) {
 	        empty = false;
 	        for (int i = 0; i < readChars; ++i) {
 	            if (c[i] == '\n') {
 	                ++count;
 	            }
 	        }
 	    }
 	    is.close();
	    return count;
 	}
 	public static double[] findDistance(String filename,long x,long y) throws IOException
 	{
	     
//		XSSFCell cellA1 = row1.getCell((short) 0);
//		String a1Val = cellA1.getStringCellValue();
// 		XSSFWorkbook workbook;
//		XSSFSheet worksheet;
//		XSSFRow XLSXrow1;
// 		int j=0;
// 		double index[]=new double[3];
// 		index[0]=-1;
// 		index[1]=-1;
// 		index[2]=999999;
// 		for(int i=0;i<FileX+1;i++)
// 		{
// 			String excelFileName=filename+"_"+i+"_"+j+".xlsx";
//			workbook = new XSSFWorkbook(excelFileName);
//			worksheet = workbook.getSheet("Sheet1");
// 			if(i==2)
// 				System.out.println(x);
// 	 		int indexX=0;
// 			while((XLSXrow1 = worksheet.getRow(indexX))!=null)
// 			{
// 				
//		    	long node1=(long)XLSXrow1.getCell((short) 0).getNumericCellValue();
//		    	long node2=(long)XLSXrow1.getCell((short) 1).getNumericCellValue();
//		    	double dist=XLSXrow1.getCell((short) 2).getNumericCellValue();
//		    	int access1=(int)XLSXrow1.getCell((short) 3).getNumericCellValue();
//		    	int access2=(int)XLSXrow1.getCell((short) 4).getNumericCellValue();
// 				
// 				if((node1==x&&node2==y&&access1>0)||((node1==y&&node2==x&&access2>0))){
// 			    	workbook.close();
// 			    	index[0]=i;
// 			    	index[1]=indexX;
// 			    	index[2]=dist;
// 			    	return index;
// 				}
// 				indexX=indexX+1;
// 			}
//	    	workbook.close();
// 		}
 		
 		
 		
 		
 		double index[]=new double[3];
 		index[0]=-1;
 		index[1]=-1;
 		index[2]=999999;
 		int i=0;
 		int j=0;
 		String fileNameDefined = filename+"_"+i+"_"+j+".xlsx";
        // -File class needed to turn stringName to actual file
        File file = new File(fileNameDefined);

            // -read from filePooped with Scanner class
        Scanner inputStream = new Scanner(file);
        // hashNext() loops line-by-line
        while(inputStream.hasNext()){
            //read single line, put in string
            String data = inputStream.next();
            String[] datas=data.split(",");

        // after loop, close scanner
        inputStream.close();
        }
 		
 		
 		
 		
 		
 		
 		
 		
 		
	    return index;
    }

 	static void update() throws EncryptedDocumentException, InvalidFormatException, IOException
 	{
 		InputStream inp = new FileInputStream(PittFolder+"test.xlsx");
		// notice how the Workbook must be constructed from the existing file
		Workbook wb = WorkbookFactory.create(inp);
		// Navigating in POI always follows the same logic:
		// 1. grab a sheet
		// 2. grab a row from that sheet
		// 3. grab a cell from that row
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(2);
		if(row==null)
			row=sheet.createRow(2);
		Cell cell = row.getCell(3);
		// a condition like the one that follows will be needed to know in what column
		// you have to write your data:   
		if (cell == null)
		    cell = row.createCell(3);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue("a test");
	
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(PittFolder+"test.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}
 	static void findNotIn(String filename) throws IOException
 	{
 		String[] CSVrow = null;
		
		
		DistanceRow=GetLength("/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv");
 		String csvFilename="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/edges.csv";
	    CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
	    CSVrow = csvReader.readNext();//get rid of heading
	    while((CSVrow = csvReader.readNext()) != null)
	    {
	    	if(Integer.parseInt(CSVrow[4])==0&&Integer.parseInt(CSVrow[5])==0){}
	    	else{
				existSet.add(Long.parseLong(CSVrow[1]));
				existSet.add(Long.parseLong(CSVrow[2]));
	    	}
	    }

	    csvReader.close();
 	}
 	
	public static void main(String[] args)throws IOException, EncryptedDocumentException, InvalidFormatException {
//		FileX=0;
//		System.out.println(Arrays.toString(findDistance(PittFolder+"distance",105130769,104872526)));
		node = InputNode();
		colNum=GetIndex(node.length,node.length)[1];
		rowNum=GetIndex(node.length,node.length)[0];

		String excelFileName="/Users/FeanorCui/Documents/Algorithm Design/project5/pitt_map/distance";

		for(int i=0;i<rowNum+1;i++){
			for(int j=0;j<colNum+1;j++){
				File yourFile = new File(excelFileName+"_"+i+"_"+j+".xlsx");
				if(!yourFile.exists()) {
				    yourFile.createNewFile();
				} 
				else{
				}
				Workbook wb=new XSSFWorkbook(excelFileName);
				FileOutputStream fileOut = new FileOutputStream(excelFileName+"_"+i+"_"+j+".xlsx");
				wb.write(fileOut);
				fileOut.flush();
				fileOut.close();
				InputStream inp = new FileInputStream(excelFileName+"_"+i+"_"+j+".xlsx");
				fileMatrix.add(WorkbookFactory.create(inp));
//				FileOutputStream oFile = new FileOutputStream(yourFile, false); 
			}
		}
		WriteMatrix(node);
		
		
		
	}

}
