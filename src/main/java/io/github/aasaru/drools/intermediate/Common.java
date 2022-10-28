/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Common {

  public static boolean disposeSession = true;

  public static int promptForStep(int section, String[] args, int minStep, int maxStep) {
    String stepStr = "";
    boolean isRulesAsked = false;

    while (true) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
        if (args != null && args.length > 0 && args[0].equals("RULES")) {

          if (section == 3) {
            System.out.printf("Section %02d. Convert *.xsl to *.drl. Enter step (%d...%d): ", section, minStep, maxStep);
          }
          else {
            System.out.printf("Section %02d. Get actual rules. Enter step (%d...%d): ", section, minStep, maxStep);
          }
          stepStr = br.readLine();
        }
        else if (args != null && args.length > 0) {
          stepStr = args[0];

          return Integer.parseInt(stepStr);
        }
        else {
          System.out.printf("Section %02d. Enter step (%d...%d): ", section, minStep, maxStep);
          stepStr = br.readLine();
        }


        int step = Integer.parseInt(stepStr);

        if (step < minStep || step > maxStep) {
          System.out.println("Step number out of range. Insert a number between " + minStep + " and " + maxStep);
        }
        else {
          return step;
        }
      }
      catch (NumberFormatException e) {
        System.out.println("Invalid number: " + stepStr);
      }
      catch (IOException e) {
        System.out.println("Invalid step input: " + stepStr);
      }
    }

  }


  public static boolean promptForYesNoQuestion(String question) {
    String enteredStr = "";

    while (true) {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
        System.out.print(String.format("%s Enter 'yes' or 'no': ", question));
        enteredStr = br.readLine().trim();

        if ("yes".equalsIgnoreCase(enteredStr) || "y".equalsIgnoreCase(enteredStr)) {
          return true;
        }

        if ("no".equalsIgnoreCase(enteredStr) || "n".equalsIgnoreCase(enteredStr)) {
          return false;
        }

        System.out.println("Enter either 'yes' or 'no'");
      }
      catch (IOException e) {
        System.out.println("Invalid input: " + enteredStr);
      }
    }

  }

}
