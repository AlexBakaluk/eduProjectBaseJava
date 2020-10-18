package com.alex.webapp.storage;

import com.alex.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    Resume get(String uuid);

    void save(Resume resume);

    void update(Resume resume);

    void delete(String uuid);

    int size();

    void clear();

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();
}
