package com.main.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "subject", indexes = @Index(columnList = "id"))
public class SubjectEntity {
    //region Property
    @Id
    private Long id;
    private String name;

    @Column
    private UUID createBy;

    @Column
    private Date createAt;

    @Column
    private UUID updateBy;

    @Column
    private Date updateAt;

    //endregion
    //region Constructor

    public SubjectEntity(Long id) {
        this.id = id;
    }

    public SubjectEntity() {
    }

    //endregion
    //region Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //endregion
}
