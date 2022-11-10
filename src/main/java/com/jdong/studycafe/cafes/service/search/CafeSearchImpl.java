package com.jdong.studycafe.cafes.service.search;

import com.jdong.studycafe.cafes.domain.Cafe;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CafeSearchImpl extends QuerydslRepositorySupport implements CafeSearch {
    public CafeSearchImpl() {
        super(Cafe.class);
    }

    @Override
    public Page<Cafe> search1(Pageable pageable) {

//        QCafe cafe = QCafe.cafe;
//        JPQLQuery<Cafe> query = from(cafe); // select ...  from cafe
//
//        BooleanBuilder booleanBuilder = new BooleanBuilder();       // where (
//        booleanBuilder.or(cafe.description.contains("AAA"));        // cafe.description like 'AAA'
//        booleanBuilder.or(cafe.name.contains("AAA"));               // or cafe.name liek 'AAA'
//        query.where(booleanBuilder);                                // )
//        query.where(cafe.id.gt(0L));                          // and cafe.id > 0
//
//        this.getQuerydsl().applyPagination(pageable, query); //paging
//
//        List<Cafe> list = query.fetch();
//        long cnt = query.fetchCount();
        return null;
    }

    @Override
    public Page<Cafe> searchAll(String[] types, String keyword, Pageable pageable) {
//        QCafe cafe = QCafe.cafe;
//        JPQLQuery<Cafe> query = from(cafe);
//
//        if ((types != null && types.length > 0) && keyword != null) {
//            BooleanBuilder booleanBuilder = new BooleanBuilder();
//            for (String type : types) {
//                switch (type) {
//                    case "name":
//                        booleanBuilder.or(cafe.name.contains(keyword));
//                        break;
//                    case "description":
//                        booleanBuilder.or(cafe.description.contains(keyword));
//                        break;
//                }
//            }
//            query.where(booleanBuilder);
//        }
//        query.where(cafe.id.gt(0L));
//        this.getQuerydsl().applyPagination(pageable, query);
//        List<Cafe> list = query.fetch();
//        long cnt = query.fetchCount();
//
//        return new PageImpl<>(list, pageable, cnt);
        return null;
    }
}
