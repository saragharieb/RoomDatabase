package com.example.roomdatabase.roomDatabase;

import androidx.room.TypeConverter;

import java.util.Date;

public class dateConverter {
    @TypeConverter
    public static Date toDate(Long time) {
        if (time != null)
            return new Date(time);
        return null;
    }

    @TypeConverter
    public static Long toLong(Date date) {
        if (date != null)
            return date.getTime();
        return null;
    }
}
