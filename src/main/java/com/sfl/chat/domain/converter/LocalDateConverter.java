package com.sfl.chat.domain.converter;

import java.time.LocalDate;
import java.sql.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * jpa converter for LocalDate. 
 *
 * @author Sevak Gharibian
 *
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        return (date == null) ? null : Date.valueOf(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null) ? null : sqlDate.toLocalDate();
    }
}
