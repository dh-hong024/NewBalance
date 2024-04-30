package practice.newbalance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.member.Member;
import practice.newbalance.dto.member.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select m from Member m where m.userId = :userId and m.password = :password")
    Member findUser(@Param("userId") String userId, @Param("password") String password);

    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
