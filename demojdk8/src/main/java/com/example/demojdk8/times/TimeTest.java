package com.example.demojdk8.times;

import javax.xml.crypto.Data;
import java.sql.SQLOutput;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author jinBiaoHu
 * @date 2019-01-12 17:56
 */
public class TimeTest {
	public  Data aa;
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
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
