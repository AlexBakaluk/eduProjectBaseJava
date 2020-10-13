package com.alex.webapp.storage;

import com.alex.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        return Arrays.binarySearch(storage, 0, size, new Resume(uuid));
    }

    @Override
    public void save(Resume resume) {
        if (size == 0) {
            storage[0] = resume;
            size++;
        } else {
            for (int i = 0; i < size + 1; i++) {
                if (storage[i] == null) {
                    storage[i] = resume;
                } else if (resume.compareTo(storage[i]) <= 0) {
                    turnAllElementsRight(storage, i, size);
                    storage[i] = resume;
                    size++;
                }
            }
        }
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Such element didn't exist");
        } else {
            storage[index] = null;
            if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
            size--;
        }
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    private void turnAllElementsRight(Resume[] storage, int beginIndex, int endIndex) {
        if (endIndex == storage.length - 1) {
            throw new IllegalArgumentException();
        } else {
            if (endIndex + 1 - beginIndex >= 0)
                System.arraycopy(storage, beginIndex - 1, storage, beginIndex, endIndex + 1 - beginIndex);
        }
    }
}
