package jpabook.jpashop.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Locker {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

}
