package com.noriteo.delinori_front.service;

import com.noriteo.delinori_front.dto.FreeBoardDTO;
import com.noriteo.delinori_front.repository.FreeBoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FreeBoardServiceTests {

    @Autowired
    private FreeBoardService freeBoardService;

    @Test
    public void testRegister() {

        IntStream.rangeClosed(1,200).forEach(i->{
            FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                    .title("Title..."+i)
                    .content("content..."+i)
                    .writer("user"+(i%10))
                    .build();

            Long bno = freeBoardService.register(freeBoardDTO);
            log.info("Bno:" +bno);
        });

    }

}
