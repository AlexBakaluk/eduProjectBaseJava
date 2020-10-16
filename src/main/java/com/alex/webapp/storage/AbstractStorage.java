package com.alex.webapp.storage;

import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;

import java.util.Collection;
import java.util.HashMap;

public abstract class AbstractStorage implements Storage{
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResumeFromStorage(index);
    }

    protected abstract Resume getResumeFromStorage(int index);

    protected abstract int getIndex(String uuid);
}
