package com.example.e15gestitb;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BooksViewModel extends ViewModel {
    BooksList booksList;
    List<Book> listOfModels = new ArrayList<Book>();

    public BooksViewModel() {
        if (listOfModels.isEmpty()) {
            String[] modules = MainActivity.status;
            for (int i = 1; i <= 2; i++) {
                Book book = new Book("Book " + i, "", modules[new Random().nextInt(modules.length)], 0);

                listOfModels.add(book);

            }

        }
    }

    public List<Book> getListOfModels() {
        return listOfModels;
    }

    public void setListOfModels(List<Book> listOfModels) {
        this.listOfModels = listOfModels;
    }
}
