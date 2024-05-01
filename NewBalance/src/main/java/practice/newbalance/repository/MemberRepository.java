package practice.newbalance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    @Query(value = "select m from Member m where m.userId = :userId and m.password = :password")
//    Optional<Member> findUser(@Param("userId") String userId, @Param("password") String password);

    @Query(value = "select m from Member m where m.userId = :userId")
    Optional<Member> findUser(@Param("userId") String userId);


    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
