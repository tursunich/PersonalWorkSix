package kz.education.kstu.personalworksix.api;

import java.util.List;

import kz.education.kstu.personalworksix.model.Book;

public interface BooksApiCallback {
    void onSuccess(List<Book> books);
    void onError();
}
