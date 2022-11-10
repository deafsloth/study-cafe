package com.jdong.studycafe.cafes.service.search;

import com.jdong.studycafe.cafes.domain.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CafeSearch {
    Page<Cafe> search1(Pageable pageable);

    Page<Cafe> searchAll(String[] types, String keyword, Pageable pageable);
}
