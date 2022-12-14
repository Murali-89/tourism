package com.cts.tourism.tourismmanagement.controller;

import com.cts.tourism.tourismmanagement.entity.BranchEntity;
import com.cts.tourism.tourismmanagement.entity.UserEntity;
import com.cts.tourism.tourismmanagement.exception.BranchServiceExcpetion;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/tourism/api/v1/branch")
public class TourismController {
    @Autowired
    TouristServiceImpl touristService;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    Environment env;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/status/check")
    public String getStatus() {
        return "working on port" + env.getProperty("local.server.port");
    }

    @PostMapping(path = "/add-places",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<BranchEntity> addBranch(@Valid @RequestBody Branch branch) {
        BranchEntity branchEntity = new BranchEntity();
        validation(branch);
        BeanUtils.copyProperties(branch, branchEntity);
        log.info("before adding branch");
        BranchEntity branchEntity1 = touristService.addBranch(branchEntity);
        log.info("After adding branch");

        return ResponseEntity.status(HttpStatus.CREATED).body(branchEntity1);
    }

    @PutMapping(path = "/update-tarrif/{companyId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<BranchEntity> update(@Valid @RequestBody Branch branch, @PathVariable int companyId) {

        log.info("Before updating branch");
        BranchEntity branchEntity1 = touristService.updateBranch(branch, companyId);
        log.info("After updating branch");
        return ResponseEntity.status(HttpStatus.CREATED).body(branchEntity1);
    }

    private void validation(Branch branch) {
        if (!branch.getWebsite().startsWith("www")) {
            throw new BranchServiceExcpetion("website must start with www");
        }
        if (branch.getContact() != null) {
            String regex = "[0-9]+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(branch.getContact());
            if (!m.matches())
                throw new BranchServiceExcpetion("contact number should be only number");
            if (branch.getContact().length() != 10)
                throw new BranchServiceExcpetion("mobile number should be of 10 digit");
        }

    }

    ///tourism /api/v1/admin/{criteria}/{criteriaValue}  for search
    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<BranchEntity> getBranch(@PathVariable long id) {

        BranchEntity branchEntity = touristService.getBranchById(id);
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchEntity, branch);
        return ResponseEntity.status(HttpStatus.OK).body(branchEntity);
    }
    @GetMapping(path = "/place/{place}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<BranchEntity> getBranchByPlace(@PathVariable String place) {

        BranchEntity branchEntity = touristService.getBranchByPlace(place);
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchEntity, branch);
        return ResponseEntity.status(HttpStatus.OK).body(branchEntity);
    }

    @DeleteMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<String> deleteBranchById(@PathVariable long id) {

        String result = touristService.deleteBranchById(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping(path = "/user",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public void createUser(@RequestBody User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userService.save(userEntity);

    }

}
