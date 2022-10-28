/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.administration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplitWorkbook {

    public static void main(String[] args) throws IOException {

        for(int stepNo=1; stepNo <= 13; stepNo++) {
            copyToSeparateFile(stepNo);
        }

    }

    private static void copyToSeparateFile(int stepNo) throws IOException {
        String sheetName = "step" + stepNo;

        String sourcePath = "/Users/juhan/spaces/github/intermediate-drools-training/section04_all_steps.xls";
        String targetPath = "/Users/juhan/spaces/github/intermediate-drools-training/src/main/resources/io/github/aasaru/drools/intermediate/section03/" + sheetName
             + "/" + sheetName + ".xls";

        //Open file
        FileInputStream inputStream = new FileInputStream(sourcePath);
        Workbook workBook = new HSSFWorkbook(inputStream);

        // validate
        if (workBook.getSheetIndex(sheetName) == -1) {
            throw new IllegalStateException("No sheet with name " + sheetName);
        }

        // move sheet to the first sheet
        workBook.setSheetOrder(sheetName, 0);
        workBook.setActiveSheet(0);
        workBook.getSheetAt(0).setSelected(true);

        // only keep previous steps

        int position = 1;
        for ( ; stepNo - position > 0; position++) {
            String sheetToMove = "step" + (stepNo-position);
            if (workBook.getSheetIndex(sheetToMove) == -1) {
                throw new IllegalStateException("No sheet to move with name " + sheetToMove);
            }
            workBook.setSheetOrder(sheetToMove, position);
            workBook.getSheetAt(position).setSelected(false);

            // remove yellow backround from
            for (int rowIdx = 0; rowIdx < 25; rowIdx++) {
                for (int colIdx = 0; colIdx < 9; colIdx++) {
                    try {
                        changeCellBackgroundColorToWhite(workBook.getSheetAt(position).getRow(rowIdx).getCell(colIdx));
                    }
                    catch (NullPointerException npe) {
                        //System.out.println("npe was on step " + (position+1) + " row " + (rowIdx+1) + " col " + (colIdx+1));
                    }
                }
            }



        }

        // delete other worksheets (future steps)
        while ( workBook.getNumberOfSheets() > position) {
            workBook.removeSheetAt(position);
        }

     //   setSameWidthsForWorksheets(workBook);


        //Save the new file
        FileOutputStream outFile = new FileOutputStream(targetPath);
        workBook.write(outFile);
        outFile.close();
    }


    public static void changeCellBackgroundColorToWhite(Cell cell) {
        if (cell == null) {
            return;
        }

        CellStyle cellStyle = cell.getCellStyle();

        if(cellStyle == null) {
            return;
        }

        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
    }

}
