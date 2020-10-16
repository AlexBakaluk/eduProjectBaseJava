package com.alex.webapp.storage;

import com.alex.webapp.exception.ExistStorageException;
import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{
    protected List<Resume> storage = new ArrayList<Resume>();

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void save(Resume resume) {
        int index = storage.indexOf(resume);
        if (index < 0) {
            storage.add(resume);
            size++;
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void update(Resume resume) {
        int index = storage.indexOf(resume);
        if (index >= 0) {
            storage.set(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }


    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            Resume resume = storage.get(index);
            storage.remove(resume);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void clear() {
        storage.clear();
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    @Override
    protected Resume getResumeFromStorage(int index) {
        return storage.get(index);
    }
}
