package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    //연관관계 주인이 아닙
    //오더테이블의 맴버필드에의해 매핑된거야
    @OneToMany(mappedBy = "member")
    private List<Order> orders= new ArrayList<>();



}
