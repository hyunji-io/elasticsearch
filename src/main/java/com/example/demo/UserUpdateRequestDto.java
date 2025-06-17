package com.example.demo;

public class UserUpdateRequestDto {
	private String name;
	private Long age;
	private Boolean isActive;

	public UserUpdateRequestDto(String name, Long age, Boolean isActive) {
		this.name = name;
		this.age = age;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
