package io.github.aasaru.drools.intermediate.administration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FormatMasterWorkbook {

    public static void main(String[] args) throws IOException {
        formatMasterWorkbook();
    }

    private static void formatMasterWorkbook() throws IOException {

        String sourcePath = "/Users/juhan/spaces/github/intermediate-drools-training/section04_all_steps.xls";
        String targetPath = "/Users/juhan/spaces/github/intermediate-drools-training/section04_all_steps_new.xls";


        //Open file
        FileInputStream inputStream = new FileInputStream(sourcePath);
        Workbook workBook = new HSSFWorkbook(inputStream);


        setSameWidthsForWorksheets(workBook);


        //Save the new file
        FileOutputStream outFile = new FileOutputStream(targetPath);
        workBook.write(outFile);
        outFile.close();

        // File (or directory) with old name
        File file = new File(sourcePath);
        File file2 = new File(sourcePath + "_backup_" + (new Date().getTime()) + ".xls");
        boolean success = file.renameTo(file2);

        File file3 = new File(targetPath);
        File file4 = new File(sourcePath);
        file3.renameTo(file4);



    }

    public static void setSameWidthsForWorksheets(Workbook workBook) {
        for (int sheetIndex = 0; sheetIndex < workBook.getNumberOfSheets(); sheetIndex++) {

            Sheet currentSheet = workBook.getSheetAt(sheetIndex);
            currentSheet.setColumnWidth(0, 3*256);

            if (sheetIndex < 4) {
                currentSheet.setColumnWidth(1, 12*256);

                currentSheet.setColumnWidth(2, 22*256);
                currentSheet.setColumnWidth(3, 22*256);
                currentSheet.setColumnWidth(4, 16*256);
                currentSheet.setColumnWidth(5, 22*256);
                currentSheet.setColumnWidth(6, 20*256);
                currentSheet.setColumnWidth(7, 20*256);
            }
            else if (sheetIndex < 10) {
                currentSheet.setColumnWidth(1, 18*256);

                currentSheet.setColumnWidth(2, 20*256);
                currentSheet.setColumnWidth(3, 12*256);
                currentSheet.setColumnWidth(4, 16*256);
                currentSheet.setColumnWidth(5, 22*256);
                currentSheet.setColumnWidth(6, 20*256);
                currentSheet.setColumnWidth(7, 20*256);
            }
            else {
                currentSheet.setColumnWidth(1, 20*256);

                currentSheet.setColumnWidth(2, 20*256);
                currentSheet.setColumnWidth(3, 20*256);
                currentSheet.setColumnWidth(4, 23*256);
                currentSheet.setColumnWidth(5, 15*256);
                currentSheet.setColumnWidth(6, 12*256);
                //workBook.getSheetAt(sheetIndex).setColumnWidth(7, 20*256);
            }

            // set notes active
            currentSheet.getRow(2).getCell(2).setAsActiveCell();

        }

        // commens on step4 and step5
        workBook.getSheetAt(3).getRow(10).setHeightInPoints(23);
        workBook.getSheetAt(3).getRow(13).setHeightInPoints(23);

        // comments on step5
        workBook.getSheetAt(4).getRow(10).setHeightInPoints(23);
        workBook.getSheetAt(4).getRow(13).setHeightInPoints(23);


        workBook.getSheetAt(7).setColumnWidth(5, 26*256); // step 8 not clause
        workBook.getSheetAt(7).setColumnWidth(5, 26*256); // step 8 not clause


        workBook.getSheetAt(5).setColumnWidth(3, 25*256); // step 6 "Link exists"
        workBook.getSheetAt(11).setColumnWidth(5, 20*256); // activation-group on step 12

    }

}
