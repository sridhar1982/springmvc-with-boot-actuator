package com.sample.springApp.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sample.springApp.Person;

@Component
public class PersonService implements IPersonService {
	@Override
	public Person getPersonDetail(Integer id){
	    System.out.println("called");
		Person p = new Person();
		p.setId(id);
		p.setLocation("California");
		p.setName("Spring");
		return p;
	}

	@Scheduled(initialDelay=1000, fixedRate=5000)
    public void healthStatusTimer(){
        System.out.println("calculating statuses");
    }
}
