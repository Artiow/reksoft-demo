package ru.reksoft.demo.mapper.manual;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class JavaTimeMapper {

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
