package com.mydubbo.user.entity;

/** * * 用户维护 */
public class User implements java.io.Serializable {
	/***/
	private Integer id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private Integer age;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}