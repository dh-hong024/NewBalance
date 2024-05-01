package practice.newbalance.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import practice.newbalance.domain.member.Member;
import practice.newbalance.service.MemberService;

import java.util.Optional;


@SpringBootTest
@Transactional
@ActiveProfiles("local")
class MemberRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void inquiryFindId(){
        Member member = new Member();
        member.setPhoneNumber("010-1234-1234");
        member.setName("홍길동1");
        member.setUserId("dh2");
        em.persist(member);

        Member member2 = new Member();
        member2.setPhoneNumber("010-1234-1233");
        member2.setName("홍길동");
        member2.setUserId("dh24");

        em.persist(member);
        em.persist(member2);

        em.flush();
        em.clear();

        String findUserId = memberRepository.findInquiryIdByNameAndPhoneNumber("홍길동1", "010-1234-1234");
        String findUserId2 = memberRepository.findInquiryIdByNameAndPhoneNumber("홍길동", "010-1234-1233");

        Assertions.assertThat(findUserId).isEqualTo("dh2");
        Assertions.assertThat(findUserId2).isEqualTo("dh24");

    }

    @Test
    @Rollback(false)
    public void inquiryFindPw(){
        Member member = new Member();
        member.setPhoneNumber("010-1234-1234");
        member.setName("홍길동1");
        member.setPassword("testPw");
        member.setUserId("dh2");
        em.persist(member);
        em.flush();
        em.clear();


        memberService.inquiryResetPw("dh2", "홍길동1", "010-1234-1234");
        Optional<Member> findMember = memberRepository.findByUserId(
                "dh2",
                "홍길동1",
                "010-1234-1234"
        );

        Assertions.assertThat(findMember.get().getPassword())
                .isNotEqualTo("testPw");


    }
}