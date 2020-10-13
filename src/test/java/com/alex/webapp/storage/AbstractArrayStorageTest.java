package com.alex.webapp.storage;

import com.alex.webapp.exception.NotExistStorageException;
import com.alex.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @org.junit.Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test
    public void get() {
    }

    @org.junit.Test
    public void save() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void clear() {
    }

    @org.junit.Test
    public void getAll() {
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}