package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.PageResult;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Person;
import com.prestu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/person")
public class personController {

    @Autowired
    PersonService personService;


    @RequestMapping("/login")
    public Object login(Person person) {

        System.out.println(person.toString());
        Person person1 = personService.searchByName(person.getUsername());
        if (person1==null) {
            return null;
        }
        System.out.println(person1.toString());
        if (person1.getPassword().equals(person.getPassword())) {
            return person;
        }
        else {
           return  null;
        }
    }
    @RequestMapping("/search")
    public PageResult search(@RequestBody(required=false) Map searchMap) {
        Page<Person> page= personService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.COMMUNITY_SEARCH_SUCCESS,page.getResult(), (long) page.getTotal());
    }
    @RequestMapping("/find")
    public Result find() {
        List<Person> all = personService.findAll();
        return new Result(false,200,"chonggong",all);
    }

    @PostMapping("/add")
    public void add(Person person) {
        Boolean add = personService.add(person);
//        return new Result(add, StatusCode.OK, MessageConstant.COMMUNITY_ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Person person = personService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_FIND_BY_ID_SUCCESS, person);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Person person) {
        Boolean add = personService.update(person);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_UPDATE_SUCCESS);
    }


    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag =personService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.COMMUNITY_DELETE_SUCCESS);
    }
}
