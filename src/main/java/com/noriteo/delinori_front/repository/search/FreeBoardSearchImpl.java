package com.noriteo.delinori_front.repository.search;

import com.noriteo.delinori_front.entity.FreeBoard;
import com.noriteo.delinori_front.entity.QFreeBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class FreeBoardSearchImpl extends QuerydslRepositorySupport implements FreeBoardSearch {

    public FreeBoardSearchImpl() {
        super(FreeBoard.class);
    }



    @Override
    public Page<FreeBoard> search1(char[] typeArr, String keyword, Pageable pageable) {
        log.info("------------search1");

        QFreeBoard freeBoard = QFreeBoard.freeBoard;

        JPQLQuery<FreeBoard> jpqlQuery = from(freeBoard);

        //검색조건이 있다면
        if(typeArr != null && typeArr.length > 0){

            BooleanBuilder condition = new BooleanBuilder();

            for(char type: typeArr){
                if(type == 'T'){
                    condition.or(freeBoard.title.contains(keyword));
                }else if(type =='C'){
                    condition.or(freeBoard.content.contains(keyword));
                }else if(type == 'W'){
                    condition.or(freeBoard.writer.contains(keyword));
                }
            }
            jpqlQuery.where(condition);
        }

        jpqlQuery.where(freeBoard.bno.gt(0L)); //bno > 0

        JPQLQuery<FreeBoard> pagingQuery =
                this.getQuerydsl().applyPagination(pageable, jpqlQuery);

        List<FreeBoard> freeboardList = pagingQuery.fetch();
        long totalCount = pagingQuery.fetchCount();

        return new PageImpl<>(freeboardList, pageable, totalCount);

    }

}
