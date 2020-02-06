package org.spring.cloud.demo.registration.common.util;

import lombok.extern.slf4j.Slf4j;
import org.spring.cloud.demo.registration.common.exception.ApplicationException;
import org.spring.cloud.demo.registration.common.exception.CommonErrorCode;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Provide conversions between java.util.Date and java.time.LocalDate
 *
 */
@Slf4j
public final class DateUtil {

    public static final String DDMMMYY = "dd-MMM-yy";

    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static final long DAY_MILLISECONDS = 86400000;

    public static final DateTimeFormatter DDMMMYYYY_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    protected static final Map<String, DateTimeFormatter> DATE_FORMATS = new LinkedHashMap<>();

    public static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd['T'HH:mm:ss]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .optionalStart()
                    .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                    .optionalStart()
                    .appendLiteral('Z')
                    .optionalEnd()
                    .optionalEnd()
                    .parseLenient()
                    .toFormatter();

    static {
        DATE_FORMATS.put("^\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{2}:\\d{2}.*", DEFAULT_DATE_TIME_FORMATTER);
        DATE_FORMATS.put("^\\d{8}$", getFormatter("yyyyMMdd"));
        DATE_FORMATS.put("^\\d{1,2}-\\d{1,2}-\\d{4}$", getFormatter("d-M-yyyy"));
        DATE_FORMATS.put("^\\d{1,2}/\\d{1,2}/\\d{4}$", getFormatter("d/M/yyyy"));
        DATE_FORMATS.put("^\\d{1,2}-\\d{1,2}-\\d{2}$", getFormatter("d-M-yy"));
        DATE_FORMATS.put("^\\d{1,2}/\\d{1,2}/\\d{2}$", getFormatter("d/M/yy"));
        DATE_FORMATS.put("^\\d{4}-\\d{1,2}-\\d{1,2}$", getFormatter("yyyy-M-d"));
        DATE_FORMATS.put("^\\d{4}/\\d{1,2}/\\d{1,2}$", getFormatter("yyyy/M/d"));
        DATE_FORMATS.put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", getFormatter("yyyy/M/d HH:mm:ss"));
        DATE_FORMATS.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", getFormatter("yyyy-M-d HH:mm:ss"));
        DATE_FORMATS.put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}\\.\\d{1}$", getFormatter("yyyy-M-d HH:mm:ss.S"));
        DATE_FORMATS.put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", getFormatter("d-M-yyyy HH:mm:ss"));
        DATE_FORMATS.put("^\\d{1,2}-[A-Za-z]{3}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", getFormatter("d-MMM-yyyy HH:mm:ss"));

        DATE_FORMATS.put("^\\d{1,2}-[A-Za-z]{3}-\\d{4}$", getFormatter("d-MMM-yyyy"));
        DATE_FORMATS.put("^\\d{1,2}-[A-Za-z]{3}-\\d{2}$", getFormatter("d-MMM-yy"));
        DATE_FORMATS.put("^\\d{1,2}/[A-Za-z]{3}/\\d{4}$", getFormatter("d/MMM/yyyy"));
        DATE_FORMATS.put("^\\d{1,2}/[A-Za-z]{3}/\\d{2}$", getFormatter("d/MMM/yy"));
    }

    public static DateTimeFormatter getFormatter(String format) {
        return new DateTimeFormatterBuilder().parseCaseInsensitive()
                .appendPattern(format).toFormatter();
    }

    /**
     * Convert date string into java.time.LocalDate. Handles null and empty on
     * input, handles input pattern validity
     */
    public static LocalDate toLocalDate(String date) {
        DateTimeFormatter formatter = determineDateFormat(date);
        if (formatter == null) {
            throw new ApplicationException(CommonErrorCode.DATE_FORMAT_NOT_SUPPORTED, date);
        }
        return toLocalDate(date, formatter);
    }

    public static LocalDate toLocalDate(String date, String format) {
        return toLocalDate(date, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate toLocalDate(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException exception) {
            throw new ApplicationException(CommonErrorCode.DATE_PARSING_ERROR, exception, exception.getMessage());
        }
    }

    public static LocalDateTime toLocalDateTime(String date, String format) {
        return toLocalDateTime(date, DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime toLocalDateTime(String date) {
        DateTimeFormatter formatter = determineDateFormat(date);
        if (formatter == null) {
            throw new ApplicationException(CommonErrorCode.DATE_FORMAT_NOT_SUPPORTED, date);
        }
        return toLocalDateTime(date, formatter);
    }

    public static LocalDateTime toLocalDateTime(String date, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException exception) {
            throw new ApplicationException(CommonErrorCode.DATE_PARSING_ERROR, exception, exception.getMessage());
        }
    }

    /**
     * Determine date format from date string. Handles null and empty on input,
     * reference to existing date format regex dict
     */
    public static DateTimeFormatter determineDateFormat(String date) {
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        for (Map.Entry<String, DateTimeFormatter> e : DATE_FORMATS.entrySet()) {
            if (date.matches(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }


    /**
     * Determine date format from date string. Handles null and empty on input,
     * reference to existing date format regex dict
     */
    public static String determineDateFormatString(String date) {
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        for (Map.Entry<String, DateTimeFormatter> e : DATE_FORMATS.entrySet()) {
            if (date.matches(e.getKey())) {
                return e.getKey();
            }
        }
        return null;
    }

    public static LocalDate ofEpoch(long epoch) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault()).toLocalDate();
    }

    public static long toEpoch(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long toEpoch(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Date dateIn) {
        return Instant.ofEpochMilli(dateIn.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date dateIn) {
        return Instant.ofEpochMilli(dateIn.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(long epoch) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneId.systemDefault());
    }

    public static Date toDate(LocalDate date) {
        return Date.from(date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date toDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * ExcelDateParse: Parses a Java Date from an Excel Date Format
     * {param} int(date) input: the excel date int to convert
     */
    public static Date parseExcelDate(double excelDate) {
        Calendar gc = parseExcelDate(excelDate, null);
        return gc.getTime();
    }

    /**
     * ExcelTimeParse: Parses a Java Date Time String from an Excel Formatted Time
     * {param} String(date) input: the excel time String to convert
     */
    public static Date parseExcelDate(String excelDate) {
        double date = Double.parseDouble(excelDate);
        return parseExcelDate(date);
    }

    public static Calendar parseExcelDate(double date, TimeZone timeZone) {
        int wholeDays = (int)Math.floor(date);
        int millisecondsInDay = (int)((date - wholeDays) * DAY_MILLISECONDS + 0.5);
        Calendar calendar;
        if (timeZone != null) {
            calendar = Calendar.getInstance(timeZone);
        } else {
            calendar = Calendar.getInstance(); // using default time-zone
        }
        setCalendar(calendar, wholeDays, millisecondsInDay);
        return calendar;
    }

    public static LocalDateTime getSystemDateTime() {
        return ZonedDateTime.now(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate getSystemDate() {
        return ZonedDateTime.now(ZoneId.systemDefault()).toLocalDate();
    }

    public static long getSystemEpoch() {
        return toEpoch(getSystemDateTime());
    }

    private static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay) {
        int startYear = 1900;
        int dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it isn't
        if (wholeDays < 61) {
            // Date is prior to 3/1/1900, so adjust because Excel thinks 2/29/1900 exists
            // If Excel date == 2/29/1900, will become 3/1/1900 in Java representation
            dayAdjust = 0;
        }
        calendar.set(startYear,0, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, millisecondsInDay);
        if (calendar.get(Calendar.MILLISECOND) == 0) {
            calendar.clear(Calendar.MILLISECOND);
        }
    }
}
