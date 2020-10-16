package com.alex.webapp.storage;

import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;

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
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            saveResumeInStorage(index, resume);
        }
    }

    protected abstract void saveResumeInStorage(int index, Resume resume);

    protected abstract Resume getResumeFromStorage(int index);

    protected abstract int getIndex(String uuid);
}
