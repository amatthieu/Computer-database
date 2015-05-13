package com.excilys.malbert.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.excilys.malbert.validator.Date.Pattern;
import com.excilys.malbert.validator.DateValidator;

public final class Utils {
    private Utils() {
    }

    public static LocalDateTime timestampToLocaldatetime(Timestamp time) {
	if (time == null) {
	    return null;
	} else {
	    return time.toLocalDateTime();
	}
    }

    public static Timestamp localdatetimeToTimestamp(LocalDateTime time) {
	return time == null ? null : Timestamp.valueOf(time);
    }

    public static LocalDateTime stringToLocaldatetime(String date,
	    Pattern pattern) {
	if (date == null) {
	    return null;
	}
	if (date.trim().isEmpty()) {
	    return null;
	}
	if (!(new DateValidator().isValid(date, pattern))) {
	    return null;
	}
	return LocalDateTime.of(
		LocalDate.parse(date,
			DateTimeFormatter.ofPattern(pattern.toString())),
		LocalTime.of(0, 0));
    }

    // Change here for en or fr format
    public static String localdatetimeToString(LocalDateTime date,
	    Pattern pattern) {
	if (date == null) {
	    return null;
	} else {
	    return date.format(DateTimeFormatter.ofPattern(pattern.toString()));
	}
    }

    public static long stringToLong(String str) {
	if (str != null && str.matches("\\d+")) {
	    return Long.parseLong(str);
	} else {
	    return 0;
	}
    }

    public static int stringToInt(String str) {
	if (str != null && str.matches("\\d+")) {
	    return Integer.parseInt(str);
	} else {
	    return 0;
	}
    }
}
