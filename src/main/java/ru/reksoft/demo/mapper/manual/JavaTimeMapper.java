package ru.reksoft.demo.mapper.manual;

import org.postgresql.util.PGInterval;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class JavaTimeMapper {

    @Deprecated
    public Time toTime(LocalTime localTime) {
        return Time.valueOf(localTime);
    }

    @Deprecated
    public LocalTime toLocalTime(Time time) {
        return time.toLocalTime();
    }

    @Deprecated
    public PGInterval toInterval(LocalTime localTime) {
        return new PGInterval(0, 0, 0, localTime.getHour(), localTime.getMinute(), localTime.getSecond());
    }

    @Deprecated
    public LocalTime toLocalTime(PGInterval pgInterval) {
        return LocalTime.of(pgInterval.getHours(), pgInterval.getMinutes(), (int) pgInterval.getSeconds());
    }

    public Timestamp toTimestamp(LocalTime localTime) {
        return Timestamp.valueOf(LocalDateTime.of(LocalDate.of(1,1,1), localTime));
    }

    public LocalTime toLocalTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toLocalTime();
    }

    public LocalDate toLocalDate(Timestamp timestamp) {
        return timestamp.toLocalDateTime().toLocalDate();
    }

    public Timestamp toTimestamp(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    public Timestamp toTimestamp(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }
}
