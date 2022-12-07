package com.cts.tourism.tourismmanagement.service;

import com.cts.tourism.tourismmanagement.entity.BranchEntity;
import com.cts.tourism.tourismmanagement.exception.BranchServiceExcpetion;
import com.cts.tourism.tourismmanagement.model.Branch;
import com.cts.tourism.tourismmanagement.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristServiceImpl {
    @Autowired
    BranchRepository branchRepository;

    public void addBranch(BranchEntity branch){
        if(branch.getBranchName().equals("murali")){
            throw new BranchServiceExcpetion("branch is not available");
        }
        branchRepository.save(branch);
        System.out.println("brnach saved");

    }
}
