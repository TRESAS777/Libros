package com.riwi.LibrosYa.infrastructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.riwi.LibrosYa.api.dto.request.LoanRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;
import com.riwi.LibrosYa.domain.model.Loan;
import com.riwi.LibrosYa.infrastructure.persistence.LoanEntity;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    public LoanResponse loanToLoanResponse(Loan loan);

    public LoanEntity loanToLoanEntity(Loan loan);

    public Loan loanEntityToLoan(LoanEntity loanEntity);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "book",ignore = true)
    @Mapping(target = "loanDate",ignore = true)
    @Mapping(target = "user",ignore = true)
    public Loan loanRequestToLoan(LoanRequest loanRequest);
}
