package com.training.model.Data;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class StudentData {
	private Integer id;
	private String name;
	private Integer age;
	private String clazz;
	private Date birthday;
	private Date createDate;
	private Date modifyDate;
	private MultipartFile files;

	private AddressData addressData;

	private String headImg;

	public AddressData getAddressData() {
		return addressData;
	}

	public void setAddressData(AddressData addressData) {
		this.addressData = addressData;
	}

	public MultipartFile getFiles() {
		return files;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	@Override
	public String toString() {
		return "StudentData{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", clazz='" + clazz + '\'' +
				", birthday=" + birthday +
				", createDate=" + createDate +
				", modifyDate=" + modifyDate +
				", files=" + files +
				", addressData=" + addressData +
				", headImg='" + headImg + '\'' +
				'}';
	}
}
