package com.noriteo.delinori_front.service;

import com.noriteo.delinori_front.dto.FreeBoardDTO;
import com.noriteo.delinori_front.dto.PageRequestDTO;
import com.noriteo.delinori_front.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FreeBoardService {

    Long register (FreeBoardDTO freeBoardDTO);

    PageResponseDTO<FreeBoardDTO> getList(PageRequestDTO pageRequestDTO);

    FreeBoardDTO read(Long bno);

    void modify(FreeBoardDTO freeBoardDTO);

    void remove(Long bno);

}
