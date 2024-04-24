package practice.newbalance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.newbalance.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
