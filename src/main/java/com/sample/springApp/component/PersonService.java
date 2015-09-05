package com.sample.springApp.component;

import org.springframework.stereotype.Component;

import com.sample.springApp.Person;

@Component
public class PersonService implements IPersonService {
	@Override
	public Person getPersonDetail(Integer id){
		Person p = new Person();
		p.setId(id);
		p.setLocation("California");
		p.setName("Spring");
		return p;
	}
}
