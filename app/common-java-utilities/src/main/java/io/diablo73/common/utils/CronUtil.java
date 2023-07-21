package io.diablo73.common.utils;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.expression.FieldExpressionFactory;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CronUtil {

	private static final String TIME_ZONE = ZoneId.SHORT_IDS.get("IST");

	public static String generateCronExpression(int DoM, int Month, int DoW, int Hour, int Minute, int Second) {
		CronBuilder cronBuilder = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));

		if (DoM != 0) {
			cronBuilder.withDoM(FieldExpressionFactory.on(DoM));
			cronBuilder.withDoW(FieldExpressionFactory.questionMark());
		} else if (DoW != 0) {
			cronBuilder.withDoW(FieldExpressionFactory.on(DoW));
			cronBuilder.withDoM(FieldExpressionFactory.questionMark());
		} else {
			cronBuilder.withDoM(FieldExpressionFactory.always());
			cronBuilder.withDoW(FieldExpressionFactory.questionMark());
		}
		if (Month == 0) {
			cronBuilder.withMonth(FieldExpressionFactory.always());
		} else {
			cronBuilder.withMonth(FieldExpressionFactory.on(Month));
		}
		if (Hour == 0) {
			cronBuilder.withHour(FieldExpressionFactory.always());
		} else {
			cronBuilder.withHour(FieldExpressionFactory.on(Hour));
		}
		if (Minute == 0) {
			cronBuilder.withMinute(FieldExpressionFactory.always());
		} else {
			cronBuilder.withMinute(FieldExpressionFactory.on(Minute));
		}
		if (Second == 0) {
			cronBuilder.withSecond(FieldExpressionFactory.always());
		} else {
			cronBuilder.withSecond(FieldExpressionFactory.on(Second));
		}

		return cronBuilder.instance().asString();
	}

	private static ExecutionTime generateExecutionTime(String cron) {
		CronParser cronParser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
		return ExecutionTime.forCron(cronParser.parse(cron));
	}

	public static Date generateNextExecutionDateFromCron(String cron) {
		Optional<ZonedDateTime> nextExecution = generateExecutionTime(cron).nextExecution(ZonedDateTime.now());
		return Date.from(nextExecution.get().toInstant());
	}

	public static long generateSecondsToNextExecutionDateFromCron(String cron) {
		Optional<Duration> nextExecution = generateExecutionTime(cron).timeToNextExecution(ZonedDateTime.now());
		return nextExecution.get().getSeconds();
	}

	public static Date generateLastExecutionDateFromCron(String cron) {
		Optional<ZonedDateTime> nextExecution = generateExecutionTime(cron).lastExecution(ZonedDateTime.now());
		return Date.from(nextExecution.get().toInstant());
	}

	public static long generateSecondsToLastExecutionDateFromCron(String cron) {
		Optional<Duration> nextExecution = generateExecutionTime(cron).timeFromLastExecution(ZonedDateTime.now());
		return nextExecution.get().getSeconds();
	}

	public static long countExecutionsBetween2DatesFromCron(String cron, Date startDate, Date endDate) {
		return generateExecutionTime(cron).countExecutions(
				ZonedDateTime.ofInstant(startDate.toInstant(), ZoneId.of(TIME_ZONE)),
				ZonedDateTime.ofInstant(endDate.toInstant(), ZoneId.of(TIME_ZONE)));
	}

	public static List<Date> generateExecutionDatesBetween2DatesFromCron(String cron, Date startDate, Date endDate) {
		List<ZonedDateTime> executionDates = generateExecutionTime(cron).getExecutionDates(
				ZonedDateTime.ofInstant(startDate.toInstant(), ZoneId.of(TIME_ZONE)),
				ZonedDateTime.ofInstant(endDate.toInstant(), ZoneId.of(TIME_ZONE)));
		return executionDates.stream().map(date -> Date.from(date.toInstant())).collect(Collectors.toList());
	}
}
