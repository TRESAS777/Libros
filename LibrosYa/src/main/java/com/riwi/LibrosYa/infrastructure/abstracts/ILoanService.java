package com.riwi.LibrosYa.infrastructure.abstracts;

import java.util.List;

import com.riwi.LibrosYa.api.dto.request.LoanRequest;
import com.riwi.LibrosYa.api.dto.response.LoanResponse;

public interface ILoanService extends BaseService<LoanRequest, LoanResponse, Long> {

  public List<LoanResponse> getLoansByUserId(Long id);

  public List<LoanResponse> getLoansByBookId(Long id);

}
