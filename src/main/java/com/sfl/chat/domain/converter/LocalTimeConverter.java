package com.sfl.chat.domain.converter;

import java.time.LocalTime;
import java.sql.Time;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * jpa converter for LocalTime. 
 *
 * @author Sevak Gharibian
 *
 */
@Converter(autoApply = true)
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return (localTime == null) ? null : Time.valueOf(localTime);
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null) ? null : time.toLocalTime();
    }
}
