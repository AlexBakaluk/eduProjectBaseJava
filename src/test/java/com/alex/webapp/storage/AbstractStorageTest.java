package com.alex.webapp.storage;

import com.alex.webapp.exception.ExistStorageException;
import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    public static final String UUID_1 = "uuid1";
    public static final Resume RESUME_1 = new Resume(UUID_1, "NAME_1");
    public static final String UUID_2 = "uuid2";
    public static final Resume RESUME_2 = new Resume(UUID_2, "NAME_2");
    public static final String UUID_3 = "uuid3";
    public static final Resume RESUME_3 = new Resume(UUID_3, "NAME_3");
    public static final String UUID_4 = "uuid4";
    public static final Resume RESUME_4 = new Resume(UUID_4, "NAME_4");

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @org.junit.Test
    public void size() {
        assertSize(3);
    }

    @org.junit.Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @org.junit.Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @org.junit.Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "NewName");
        storage.update(newResume);
        Assert.assertSame(newResume, storage.get(UUID_1));
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @org.junit.Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }
}
