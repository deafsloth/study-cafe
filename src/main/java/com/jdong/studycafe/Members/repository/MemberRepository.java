package com.jdong.studycafe.Members.repository;

import com.jdong.studycafe.Members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

}

