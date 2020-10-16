package com.alex.webapp.storage;

import com.alex.webapp.exception.ExistStorageException;
import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage{
    protected Map<String, Resume> storage = new HashMap<String, Resume>();

    public void update(Resume resume) {
        Resume r = storage.get(resume.getUuid());
        if (r != null) {
            storage.put(resume.getUuid(), resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    protected Resume getResumeFromStorage(int index) {
        return null;
    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    public void save(Resume resume) {
        if (!storage.containsKey(resume.getUuid())) {
            storage.put(resume.getUuid(), resume);
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void delete(String uuid) {
        if (storage.remove(uuid) == null) {
            throw new NotExistStorageException(uuid);
        }
        size--;
    }

    @Override
    public void clear() {
        storage.clear();
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.values().toArray();
    }
}
