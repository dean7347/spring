package jpabook.jpashop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders= new ArrayList<>();



}
