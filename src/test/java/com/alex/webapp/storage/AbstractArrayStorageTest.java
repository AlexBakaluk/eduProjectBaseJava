package com.alex.webapp.storage;

import com.alex.webapp.exception.StorageException;
import com.alex.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Random"));
            }
        } catch (StorageException e) {
           Assert.fail();
        }
        storage.save(new Resume("Random"));
    }
}