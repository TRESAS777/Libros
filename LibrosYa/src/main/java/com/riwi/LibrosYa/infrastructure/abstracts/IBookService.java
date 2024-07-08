package com.riwi.LibrosYa.infrastructure.abstracts;

import com.riwi.LibrosYa.api.dto.request.BookRequest;
import com.riwi.LibrosYa.api.dto.response.BookResponse;

public interface IBookService extends BaseService<BookRequest,BookResponse,Long> {
    
}
