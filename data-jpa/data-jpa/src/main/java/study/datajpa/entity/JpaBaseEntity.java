package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


    //퍼시스트 전 이벤트 발생
    @PrePersist
    public void prePersist()
    {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate=now;
        this.updateDate=now;
    }

    @PreUpdate
    public void PreUpdate()
    {
        updateDate = LocalDateTime.now();
    }

}
