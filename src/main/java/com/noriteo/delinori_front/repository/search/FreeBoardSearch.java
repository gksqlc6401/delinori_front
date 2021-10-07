package com.noriteo.delinori_front.repository.search;

import com.noriteo.delinori_front.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreeBoardSearch {

    Page<FreeBoard> search1(char[] typeArr, String keyword, Pageable pageable);

}
