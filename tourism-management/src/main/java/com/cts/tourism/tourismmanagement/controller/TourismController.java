package com.cts.tourism.tourismmanagement.controller;

import com.cts.tourism.tourismmanagement.entity.BranchEntity;
import com.cts.tourism.tourismmanagement.entity.UserEntity;
import com.cts.tourism.tourismmanagement.model.Branch;
import com.cts.tourism.tourismmanagement.model.User;
import com.cts.tourism.tourismmanagement.service.TouristServiceImpl;
import com.cts.tourism.tourismmanagement.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class TourismController {
    @Autowired
    TouristServiceImpl touristService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    Environment env;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/status/check")
    public String getStatus(){
        return "working on port" + env.getProperty("local.server.port") ;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Branch> addBranch(@Valid @RequestBody Branch branch){
        BranchEntity branchEntity = new BranchEntity();
        BeanUtils.copyProperties(branch,branchEntity);
        log.info("before adding branch");
        touristService.addBranch(branchEntity);
        log.info("After adding branch");

        return ResponseEntity.status(HttpStatus.CREATED).body(branch);
    }

    @PostMapping(path = "/user",
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public void createUser(@RequestBody User user){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userService.save(userEntity);

    }

}
