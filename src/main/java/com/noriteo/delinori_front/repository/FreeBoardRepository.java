package com.noriteo.delinori_front.repository;

import com.noriteo.delinori_front.entity.FreeBoard;
import com.noriteo.delinori_front.repository.search.FreeBoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>, FreeBoardSearch {
}
