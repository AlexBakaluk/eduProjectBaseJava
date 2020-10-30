package com.alex.webapp.sql;

import com.alex.webapp.exception.ExistStorageException;
import com.alex.webapp.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {}

    public static StorageException convertException(SQLException e) {
        if (e != null) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
