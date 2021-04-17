package jpabook.jpashop.domain;


import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
//값타입은 변경되면 안된다 생성할때만 값이 셋팅되어야 한다
//JPA스팩상  엔티티나 임베디드타입은 자바 기본생성자를 public 또는 protected로 설정한다 protected가 안전하다
 @Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address()
    {

    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
