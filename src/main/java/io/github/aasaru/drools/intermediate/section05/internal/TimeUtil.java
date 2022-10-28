/*
 *  Intermediate Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Intermediate Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package io.github.aasaru.drools.intermediate.section05.internal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoField.MILLI_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

public class TimeUtil {

    private static LocalDateTime START_TIME;

    public static void resetClock() {
        START_TIME = LocalDateTime.now();
    }


    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(MILLI_OF_SECOND, 1, 1, true)
            .toFormatter();

    public static String getCurrentTime() {
        if (START_TIME == null) {
            throw new IllegalStateException("You must first call TimeUtil.resetClock()");
        }

        long diff = ChronoUnit.MILLIS.between(START_TIME, LocalDateTime.now());

        return String.format("%02d", diff/100) + ": ";
    }
}
