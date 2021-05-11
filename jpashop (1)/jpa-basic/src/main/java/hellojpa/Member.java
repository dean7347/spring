package hellojpa;


import javax.persistence.*;
import java.util.*;

//JPA 관리임을 알려주기위해
@Entity

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;


    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Address homeAddress;
    @ElementCollection
    @CollectionTable(name ="FAVORITE_FOOD"
    ,joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name="FOOD_NAME")//예외적으로 된다
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS",joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();



//    @Column(name="TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}