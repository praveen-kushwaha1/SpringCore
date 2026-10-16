
package com.spring._04_stereotype_annot;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*
 * @Component registers this class as a Spring-managed bean.
 * @Scope("prototype") requests a new instance for each getBean() call.
 */
@Component("employee")
@Scope("prototype")
public class Emp {

	// Injects the integer value 1.
	@Value("1")
	private int id;

	// Injects the String value "Becoder".
	@Value("Becoder")
	private String name;

	// SpEL retrieves the Spring bean whose name is "ad".
	@Value("#{ad}")
	private List<String> address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id
				+ ", name=" + name
				+ ", address=" + address + "]";
	}
}
