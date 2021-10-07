package com.noriteo.delinori_front.service;

import com.noriteo.delinori_front.dto.FreeBoardDTO;
import com.noriteo.delinori_front.dto.PageRequestDTO;
import com.noriteo.delinori_front.dto.PageResponseDTO;
import com.noriteo.delinori_front.entity.FreeBoard;
import com.noriteo.delinori_front.repository.FreeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService{

    private final ModelMapper modelMapper;
    private final FreeBoardRepository freeBoardRepository;

    @Override
    public Long register(FreeBoardDTO freeBoardDTO) {

        FreeBoard freeBoard = modelMapper.map(freeBoardDTO, FreeBoard.class);

        freeBoardRepository.save(freeBoard);

        return freeBoard.getBno();
    }

    @Override
    public PageResponseDTO<FreeBoardDTO> getList(PageRequestDTO pageRequestDTO) {
        char[] typeArr = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1,
                pageRequestDTO.getSize(),
                Sort.by("bno").descending());

        Page<FreeBoard> result = freeBoardRepository.search1(typeArr,keyword,pageable);

        List<FreeBoardDTO> dtoList = result.get().map(
                        freeBoard -> modelMapper.map(freeBoard,FreeBoardDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return new PageResponseDTO<>(pageRequestDTO,(int)totalCount,dtoList);
    }

    @Override
    public FreeBoardDTO read(Long bno) {
        Optional<FreeBoard> result = freeBoardRepository.findById(bno);

        if (result.isEmpty()){
            throw new RuntimeException("NOT FOUND");
        }

        return modelMapper.map(result.get(),FreeBoardDTO.class);
    }

    @Override
    public void modify(FreeBoardDTO freeBoardDTO) {

        Optional<FreeBoard> result = freeBoardRepository.findById(freeBoardDTO.getBno());

        if(result.isEmpty()) {
            throw new RuntimeException("NOT FOUND");
        }

        FreeBoard freeBoard = result.get();

        freeBoard.change(freeBoard.getTitle(), freeBoard.getContent());

        freeBoardRepository.save(freeBoard);

    }

    @Override
    public void remove(Long bno) {
        freeBoardRepository.deleteById(bno);
    }
}
