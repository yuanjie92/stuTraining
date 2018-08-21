package com.training.model.form;

import com.training.model.AddressModel;
import org.springframework.web.multipart.MultipartFile;

public class StudentForm {

	private Integer id;
	private String name;
	private Integer age;
	private String clazz;
	private String birthday;
	private String createDate;
	private MultipartFile files;
	private String areaCode;
	private AddressForm addressForm;
	private String headImg;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public void setFiles(MultipartFile files) {
		this.files = files;
	}

	public MultipartFile getFiles() {
		return files;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public AddressForm getAddressForm() {
		return addressForm;
	}

	public void setAddressForm(AddressForm addressForm) {
		this.addressForm = addressForm;
	}
}
