package practice.newbalance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.newbalance.domain.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m.userId from Member m where m.name = :name and m.phoneNumber = :phoneNumber")
    public String findInquiryIdByNameAndPhoneNumber(
            @Param("name") String name,
            @Param("phoneNumber") String phoneNumber
    );

    @Query("select m from Member m where m.userId = :userId and m.name = :name and m.phoneNumber = :phoneNumber")
    public Optional<Member> findByUserId(
            @Param("userId") String userId,
            @Param("name") String name,
            @Param("phoneNumber") String phoneNumber
    );
}
