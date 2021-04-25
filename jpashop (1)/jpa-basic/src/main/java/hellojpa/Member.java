package hellojpa;


import javax.persistence.*;
import java.util.Date;

//JPA 관리임을 알려주기위해
@Entity
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;
    //Getter, Setter…
}