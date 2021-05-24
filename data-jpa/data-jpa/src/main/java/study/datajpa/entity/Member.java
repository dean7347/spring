package study.datajpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
//엔티티에 세터 없는게 좋다
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//연관관계필드는 투스트링 x
@ToString(of= {"id","username","age"})
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    //테이블은 관례상 테이블명_아이디 이렇게 쓴다
    @Column(name="member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    //포린키명
    @JoinColumn(name= "team_id")
    private Team team;

    public Member(String username, int age, Team team) {
        this.username=username;
        this.age=age;
        if(team !=null)
        {
            changeTeam(team);

        }

    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team)
    {
        this.team =team;
        team.getMembers().add(this);
    }


//    //엔티티 디폴트 생성자 있어야한다
//    // 프로텍티드까지 열어놔야한다 private x
//    //프록시를 사용할때 사용된다
//    protected Member() {
//    }
    // 어노테이션 대체

    public Member(String username) {
        this.username = username;
    }
}
