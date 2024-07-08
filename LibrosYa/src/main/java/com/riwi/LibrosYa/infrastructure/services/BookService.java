package com.riwi.LibrosYa.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.LibrosYa.api.dto.request.BookRequest;
import com.riwi.LibrosYa.api.dto.response.BookResponse;
import com.riwi.LibrosYa.domain.model.Book;
import com.riwi.LibrosYa.domain.repositories.BookRepository;
import com.riwi.LibrosYa.infrastructure.abstracts.IBookService;
import com.riwi.LibrosYa.infrastructure.mappers.BookMapper;
import com.riwi.LibrosYa.infrastructure.persistence.BookEntity;
import com.riwi.LibrosYa.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService implements IBookService {
    
    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookMapper bookMapper;
    
    
    @Override
    public Page<BookResponse> getAll(int size, int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, size);

        return this.bookRepository.findAll(pageable).map(book -> bookMapper.bookToBookResponse(bookMapper.bookEntityToBook(book)));
    }

    @Override
    public BookResponse getById(Long id) {
        Book book = bookMapper.bookEntityToBook(findBookEntity(id));
        BookResponse bookResponse = bookMapper.bookToBookResponse(book);

        return bookResponse;
    }

    private BookEntity findBookEntity(Long id){

        return this.bookRepository.findById(id).orElseThrow(()-> new IdNotFoundException("books"));
    }

    @Override
    public BookResponse create(BookRequest request) {
        Book book = bookMapper.bookRequestToBook(request);

        return bookMapper.bookToBookResponse(bookMapper.bookEntityToBook(this.bookRepository.save(bookMapper.bookToBookEntity(book))));
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {
        Book book = bookMapper.bookEntityToBook(findBookEntity(id));
        book = bookMapper.bookRequestToBook(request);

        return bookMapper.bookToBookResponse(bookMapper.bookEntityToBook(this.bookRepository.save(bookMapper.bookToBookEntity(book))));
    }

    @Override
    public void delete(Long id) {
        BookEntity bookEntity = findBookEntity(id);
        
        this.bookRepository.delete(bookEntity);
    }
    
}
