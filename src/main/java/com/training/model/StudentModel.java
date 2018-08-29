package com.training.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_student")
public class StudentModel implements Comparable{

    private Integer id;
    private String name;
    private Integer age;
    private String clazz;
    private Date birthday;
    private Date createDate;
    private Date modifyDate;
    private String areaCode;

    private String headImg;

    private AddressModel address;

    public static final String NAME = "name";
    public static final String CLAZZ = "clazz";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof StudentModel)){
            //throw new Exception("不同对象不能进行比较");
            return -100;
        }
        StudentModel temp = (StudentModel)o;
        if(this.getId() > temp.getId()){
            return 1;
        }
        if(this.getId() < temp.getId()){
            return -1;
        }
        return 0;
    }
}
