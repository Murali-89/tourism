package com.cts.tourism.tourismmanagement.service;

import com.cts.tourism.tourismmanagement.entity.BranchEntity;
import com.cts.tourism.tourismmanagement.exception.BranchServiceExcpetion;
import com.cts.tourism.tourismmanagement.model.Branch;
import com.cts.tourism.tourismmanagement.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TouristServiceImpl {
    @Autowired
    BranchRepository branchRepository;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public BranchEntity addBranch(BranchEntity branch) {

        BranchEntity branchEntity = branchRepository.save(branch);
        log.info("Branch saved");
        return branchEntity;

    }

    public BranchEntity updateBranch(Branch branch, long id) {

        Optional<BranchEntity> branchEntity = branchRepository.findById(id);
        if(branchEntity ==null)
            throw new BranchServiceExcpetion("Branch is not found for given company id");
        BranchEntity branchEntity1 = null;
        if(branchEntity.isPresent()){
            branchEntity1 = branchEntity.get();
            branchEntity1.setTariff(branch.getTariff());
            branchRepository.save(branchEntity1);
        }

        log.info("Branch updated");
        return branchEntity1;

    }

    public BranchEntity getBranchById(long id) {

        Optional<BranchEntity> branch = branchRepository.findById(id);
        if (branch.isPresent()) {
            return branch.get();
        }
        throw new BranchServiceExcpetion("Branch is not found");

    }

    public BranchEntity getBranchByPlace(String place) {

        Optional<BranchEntity> branch = branchRepository.findByPlace(place);
        if (branch.isPresent()) {
            return branch.get();
        }
        throw new BranchServiceExcpetion("place is not found");

    }

    public String deleteBranchById(long id) {

        branchRepository.deleteById(id);

        return "deleted successfully";
    }
}
