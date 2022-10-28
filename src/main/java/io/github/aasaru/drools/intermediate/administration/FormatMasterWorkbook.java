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
            else if (sheetIndex < 11) {

                int idx = 1; // B column

                if (sheetIndex == 8) { // step9 priority col
                    currentSheet.setColumnWidth(idx++, 10*256);
                }

                currentSheet.setColumnWidth(idx++, 18*256);

                currentSheet.setColumnWidth(idx++, 20*256);
                currentSheet.setColumnWidth(idx++, 13*256);
                currentSheet.setColumnWidth(idx++, 16*256);
                currentSheet.setColumnWidth(idx++, 22*256);
                currentSheet.setColumnWidth(idx++, 20*256);
                currentSheet.setColumnWidth(idx++, 20*256);
            }
            else { // starting 12
                currentSheet.setColumnWidth(1, 20*256);

                currentSheet.setColumnWidth(2, 20*256);
                currentSheet.setColumnWidth(3, 20*256);
                currentSheet.setColumnWidth(4, 24*256);
                currentSheet.setColumnWidth(5, 15*256);
                currentSheet.setColumnWidth(6, 12*256);
                //workBook.getSheetAt(sheetIndex).setColumnWidth(7, 20*256);
            }

            // set notes active
            currentSheet.getRow(2).getCell(2).setAsActiveCell();

        }

        // comments on step4 and step5
        workBook.getSheetAt(3).getRow(10).setHeightInPoints(23);
        workBook.getSheetAt(3).getRow(13).setHeightInPoints(23);
        workBook.getSheetAt(4).getRow(10).setHeightInPoints(23);
        workBook.getSheetAt(4).getRow(13).setHeightInPoints(23);

        // step5 width of C and D
        workBook.getSheetAt(4).setColumnWidth(2, 31*256);
        workBook.getSheetAt(4).setColumnWidth(3, 15*256);

        workBook.getSheetAt(7).setColumnWidth(5, 26*256); // step 8 not clause
        workBook.getSheetAt(7).setColumnWidth(5, 26*256); // step 8 not clause

        workBook.getSheetAt(5).setColumnWidth(2, 13*256); // step 6 "Urgent"
        workBook.getSheetAt(5).setColumnWidth(3, 23*256); // step 6 "Link exists"

        workBook.getSheetAt(10).setColumnWidth(4, 23*256); // JOIN on step 11
        workBook.getSheetAt(10).setColumnWidth(5, 22*256); // ACTION on step 11
        workBook.getSheetAt(10).setColumnWidth(6, 19*256); // activation-group on step 11

        workBook.getSheetAt(12).setColumnWidth(5, 25*256); // Notes on step 13

    }

}
