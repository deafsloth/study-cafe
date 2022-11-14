package com.jdong.studycafe.members.repository;

import com.jdong.studycafe.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

}

