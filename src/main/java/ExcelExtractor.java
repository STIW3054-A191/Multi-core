import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExcelExtractor {
    static HSSFWorkbook workbook = new HSSFWorkbook();
    static HSSFSheet sheet =workbook.createSheet("StudentData");
    HSSFRow row1;
    int WMC,DIT,NOC,CBO,RFC,LCOM,Ca,NPM,no;
    static int j = 0;
    String matric;

    public ExcelExtractor(String mat,int WMC1,int DIT1,int NOC1,int CBO1,int RFC1,int LCOM1,int Ca1,int NPM1,int arr){
        WMC =WMC1;
        DIT = DIT1;
        NOC =NOC1;
        CBO = CBO1;
        RFC = RFC1;
        LCOM = LCOM1;
        Ca =Ca1;
        NPM = NPM1;
        j=arr;
        matric = mat;
    }
    public void printthis1(){
        System.out.println("No : "+no + " Matric No : "+matric+" : "+WMC +" | "+ DIT+" | "+NOC+" | "+CBO+" | "+RFC+" | "+LCOM+" | "+Ca+" | "+NPM );
    }

    public void write() throws FileNotFoundException {
        j++;

       row1 = sheet.createRow(1);

        row1.createCell(0).setCellValue("No");
        sheet.autoSizeColumn(0);
        row1.createCell(1).setCellValue("Matric No");
        sheet.autoSizeColumn(1);
        row1.createCell(2).setCellValue("WMC");
        sheet.autoSizeColumn(2);
        row1.createCell(3).setCellValue("DIT");
        sheet.autoSizeColumn(3);
        row1.createCell(4).setCellValue("NOC");
        sheet.autoSizeColumn(4);
        row1.createCell(5).setCellValue("CBO");
        sheet.autoSizeColumn(5);
        row1.createCell(6).setCellValue("RFC");
        sheet.autoSizeColumn(6);
        row1.createCell(7).setCellValue("LCOM");
        sheet.autoSizeColumn(7);
        row1.createCell(8).setCellValue("Ca");
        sheet.autoSizeColumn(8);
        row1.createCell(9).setCellValue("NPM");
        sheet.autoSizeColumn(9);


        try {

            HSSFRow row = sheet.createRow(j);
            row.createCell(0).setCellValue(j-1);
            sheet.autoSizeColumn(0);
            row.createCell(1).setCellValue(matric);
            sheet.autoSizeColumn(1);
            row.createCell(2).setCellValue( WMC);
            sheet.autoSizeColumn(2);
            row.createCell(3).setCellValue(DIT);
            sheet.autoSizeColumn(3);
            row.createCell(4).setCellValue(NOC);
            sheet.autoSizeColumn(4);
            row.createCell(5).setCellValue(CBO);
            sheet.autoSizeColumn(5);
            row.createCell(6).setCellValue(RFC);
            sheet.autoSizeColumn(6);
            row.createCell(7).setCellValue(LCOM);
            sheet.autoSizeColumn(7);
            row.createCell(8).setCellValue(Ca);
            sheet.autoSizeColumn(8);
            row.createCell(9).setCellValue(NPM);
            sheet.autoSizeColumn(9);
//
//          BarChartExtractor chart1= new BarChartExtractor();
//          chart1.chart(sheet);
          //  BarChartExtractor.barColumnChart();

            workbook.write(new FileOutputStream("Ckjmetrics.xls"));
            workbook.close();

        } catch (Exception e) {
        }



    }

}



