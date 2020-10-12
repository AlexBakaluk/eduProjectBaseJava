package com.alex.webapp.storage;

import com.alex.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if(getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Can't found resume with id: " + resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            for (int i = 0; i < size; i++) {
                if(storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size-1];
                    storage[size-1] = null;
                    size--;
                    System.out.println("Resume: " + uuid + " successful deleted");
                }
            }
        } else {
            System.out.println("Can't found resume with id: " + uuid);
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Storage cleared!");
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
