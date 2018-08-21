package com.training.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "area")
public class AreaModel {

    private Integer id ;
    private String code;
    private String name;
    private String pCode;
    private Date createTime;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AreaModel{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pCode=" + pCode +
                '}';
    }
}
