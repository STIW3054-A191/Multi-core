import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

public class BarChartExtractor {

//    public static void main(String[] args) throws IOException {
//            barColumnChart();
//    }

    public static void barColumnChart() throws FileNotFoundException, IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            String sheetName = "CKJM BAR CHART";//"CountryColumnChart";
            XSSFSheet sheet = wb.createSheet(sheetName);

            // Create row and put some cells in it. Rows and cells are 0 based.
            Row row = sheet.createRow((short) 0);
            Cell cell;
            for(int i=0;i<10;i++) {
                cell = row.createCell((short) i);
                cell.setCellValue("CKJM "+i);


            }
            int total =1;
            for(int i=1;i<20;i++) {
                row = sheet.createRow((short) i);

                cell = row.createCell((short) i);
                cell.setCellValue(17098242);

                cell = row.createCell((short) 1);
                cell.setCellValue(9984670);

                cell = row.createCell((short) 2);
                cell.setCellValue(9826675);

                cell = row.createCell((short) 3);
                cell.setCellValue(9596961);

                cell = row.createCell((short) 4);
                cell.setCellValue(8514877);

                cell = row.createCell((short) 5);
                cell.setCellValue(7741220);

                cell = row.createCell((short) 6);
                cell.setCellValue(3287263);
                cell = row.createCell((short) 7);
                cell.setCellValue(4343443);
            total++;
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 4, 7, 20);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("Area-wise Top Seven Countries");
            chart.setTitleOverlay(false);

            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("Country");
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("CKJM");
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

                XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                        new CellRangeAddress(1, 1, 0, 7));

            XDDFDataSource<String> countries = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                    new CellRangeAddress(0, 0, 0, 7));


            XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            XDDFChartData.Series series1 = data.addSeries(countries, values);
            series1.setTitle("CKJM", null);
            data.setVaryColors(true);
            chart.plot(data);

            // in order to transform a bar chart into a column chart, you just need to change the bar direction
            XDDFBarChartData bar = (XDDFBarChartData) data;
            bar.setBarDirection(BarDirection.COL);
            //bar.setBarDirection(BarDirection.COL);

            // Write output to an excel file
            String filename = "Ckjm Bar Chart.xlsx";//"column-chart-top-seven-countries.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
            }
        }
    }
}
