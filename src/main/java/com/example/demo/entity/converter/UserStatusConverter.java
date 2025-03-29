package com.example.demo.entity.converter;

import com.example.demo.constant.UserStatusKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatusKind, Boolean> {

    //  Converts the value stored in the entity attribute into the 
    //  data representation to be stored in the database
    public Boolean convertToDatabaseColumn(UserStatusKind userStatus) {
        return userStatus.isDisabled();
    }

     /**
     * Converts the data stored in the database column into the 
     * value to be stored in the entity attribute.
     * Note that it is the responsibility of the converter writer to
     * specify the correct <code>dbData</code> type for the corresponding 
     * column for use by the JDBC driver: i.e., persistence providers are 
     * not expected to do such type conversion.
     */

    public UserStatusKind convertToEntityAttribute(Boolean isDisabled) {
        return isDisabled ? UserStatusKind.DISABLED : UserStatusKind.ENABLED;
    }
}
