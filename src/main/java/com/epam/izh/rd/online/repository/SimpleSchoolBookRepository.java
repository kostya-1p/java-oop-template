package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks;

    public SimpleSchoolBookRepository() {
        schoolBooks = new SchoolBook[0];
    }

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] booksByName = new SchoolBook[0];

        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                booksByName = Arrays.copyOf(booksByName, booksByName.length + 1);
                booksByName[booksByName.length - 1] = book;
            }
        }
        return booksByName;
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] newBooks = new SchoolBook[0];

        for (SchoolBook book : schoolBooks) {
            if (!book.getName().equals(name)) {
                newBooks = Arrays.copyOf(newBooks, newBooks.length + 1);
                newBooks[newBooks.length - 1] = book;
            }
        }
        if (newBooks.length != schoolBooks.length) {
            schoolBooks = newBooks;
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
