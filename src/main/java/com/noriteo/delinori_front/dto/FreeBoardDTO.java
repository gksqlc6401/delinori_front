package com.noriteo.delinori_front.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardDTO {

    private Long bno;

    private String title;

    private String writer;

    private String content;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String show_is;

}
