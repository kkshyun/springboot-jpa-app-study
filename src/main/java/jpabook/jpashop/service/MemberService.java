package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// @AllArgsConstructor
@RequiredArgsConstructor // Lombok - final 있는 필드만 가지고 생성자 만들어줌
// @Transactional // lazy loading 가능해짐
// public 메소드는 클래스에 붙은 Transactional 어노테이션에 다 적용됨
public class MemberService {

    private final MemberRepository memberRepository; // final 넣으면 생성자 주입 안 할 걸 알 수 있음

    // 생성자 인젝션
//    private MemberRepository memberRepository;
//    @Autowired // 생략 가능
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // @Autowired // 스프링에 들어있는 리포지토리를 인젝션해줌
    // private MemberRepository memberRepository;

    // setter 인젝션
//    private MemberRepository memberRepository;
//    @Autowired
//    private void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    // @Transactional(readOnly = true) // 읽기에는 readOnly 쓰면 좋고 성능이 최적화되고 쓰기에는 readOnly 붙이면 데이터 변경이 안됨
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
    
}
