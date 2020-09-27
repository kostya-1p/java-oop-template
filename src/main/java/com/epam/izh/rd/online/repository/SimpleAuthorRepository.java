package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors;

    public SimpleAuthorRepository() {
        authors = new Author[0];
    }

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null)
            return false;
        authors = Arrays.copyOf(authors, authors.length + 1);
        authors[authors.length - 1] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equals(name) && author.getLastName().equals(lastname))
                return author;
        }

        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null)
            return false;

        Author[] authorsTemp = new Author[authors.length - 1];
        for (int i = 0, j = 0; j < authors.length; j++) {
            if (authors[j].getName().equals(author.getName()) && authors[i].getLastName().equals(author.getLastName()))
                continue;
            authorsTemp[i++] = authors[j];
        }
        authors = authorsTemp;
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
