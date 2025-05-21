package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다") // 값이 비어있으면 오류 남
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
