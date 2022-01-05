package io.github.aasaru.drools.intermediate.administration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplitWorkbook {

    public static void main(String[] args) throws IOException {

        for(int stepNo=1; stepNo <= 12; stepNo++) {
            copyToSeparateFile("step" + stepNo);
        }

    }

    private static void copyToSeparateFile(String sheetName) throws IOException {
        String sourcePath = "/Users/juhan/spaces/github/intermediate-drools-training/src/main/resources/io/github/aasaru/drools/intermediate/administration/section04_all_steps.xls";
        String targetPath = "/Users/juhan/spaces/github/intermediate-drools-training/src/main/resources/io/github/aasaru/drools/intermediate/section04/" + sheetName
             + "/test_" + sheetName + ".xls";

        //Open file
        FileInputStream inputStream = new FileInputStream(sourcePath);
        Workbook workBook = new HSSFWorkbook(inputStream);

        // validate
        if (workBook.getSheetIndex(sheetName) == -1) {
            throw new IllegalStateException("No sheet with name " + sheetName);
        }

        // move sheet to the first sheet
        workBook.setSheetOrder(sheetName, 0);

        // delete other worksheets
        while ( workBook.getNumberOfSheets() > 1) {
            workBook.removeSheetAt(1);
        }

        //Save the new file
        FileOutputStream outFile = new FileOutputStream(targetPath);
        workBook.write(outFile);
        outFile.close();
    }
}
