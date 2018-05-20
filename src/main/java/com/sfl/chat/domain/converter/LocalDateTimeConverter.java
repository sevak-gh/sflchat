package com.sfl.chat.domain.converter;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * jpa converter for LocalDateTime. 
 *
 * @author Sevak Gharibian
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        return (dateTime == null) ? null : Timestamp.valueOf(dateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp sqlTimestamp) {
        return (sqlTimestamp == null) ? null : sqlTimestamp.toLocalDateTime();
    }
}
