package com.example.demojdk8.times;

import org.apache.commons.lang3.time.FastDateFormat;

import javax.xml.crypto.Data;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 17:56
 */
public class TimeTest {
	public  Data aa;
	public static final FastDateFormat ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
	public static final FastDateFormat ISO_DATETIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
	public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
	public static final FastDateFormat ISO_DATE_TIME_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-ddZZ");
	public static final FastDateFormat ISO_TIME_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ss");
	public static final FastDateFormat ISO_TIME_TIME_ZONE_FORMAT = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
	public static final FastDateFormat ISO_TIME_NO_T_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
	public static final FastDateFormat ISO_TIME_NO_T_TIME_ZONE_FORMAT = FastDateFormat.getInstance("HH:mm:ssZZ");
	public static void main(String[] args) {
		Instant now1 = Instant.now();
		System.out.println(now1);


		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);


		String format = dt.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(format);


		LocalDateTime dateTime = LocalDateTime.parse("2019-01-10T17:58:05");
		System.out.println(dateTime);
		LocalTime of = LocalTime.of(17, 27);
		System.out.println(of);
		LocalDate localDate = dt.toLocalDate();
		System.out.println(localDate);
		DayOfWeek day = dt.getDayOfWeek();
		System.out.println(day);
		LocalDateTime localDateTime = dt.withDayOfMonth(2);
		System.out.println(localDateTime);
		LocalDateTime localDateTime1 = dt.truncatedTo(ChronoUnit.HOURS);
		System.out.println("_"+localDateTime1);

		Period period = Period.ofWeeks(3);
		Period of1 = Period.of(1, 1, 2);

		System.out.println(of1.getDays());
		LocalDateTime plus = dt.plus(period);
		long days = Duration.between(plus, dt).toDays();
		System.out.println(days);
		OffsetTime now = OffsetTime.now();
		OffsetTime offsetTime = now.withHour(2);
		System.out.println(offsetTime);



	}


}
