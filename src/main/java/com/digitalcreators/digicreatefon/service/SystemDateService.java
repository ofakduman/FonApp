package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.SystemDate;
import com.digitalcreators.digicreatefon.repository.SystemDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SystemDateService {
    private final SystemDateRepository systemDateRepository;

    public SystemDate saveSystemDate(SystemDate systemDate){
        return systemDateRepository.save(systemDate);
    }



    public List<SystemDate> gelAllSystemDates(){
        return systemDateRepository.findAll();
    }

    public void deleteById(Long id){
        systemDateRepository.deleteById(id);
    }

    public SystemDate getLatestSystemDate() {
        List<SystemDate> systemDates = gelAllSystemDates();
        return systemDates.get(systemDates.size() - 1);
    }

    public SystemDate getDate(Long id){return systemDateRepository.getOne(id);}

    public void updateLatestSystemDate(SystemDateDto systemDateDto) {
        Optional<SystemDate> optionalLatestSystemDate = systemDateRepository.findTopByOrderByIdDesc();

        if (optionalLatestSystemDate.isPresent()) {
            SystemDate systemDateToUpdate = optionalLatestSystemDate.get();
            systemDateToUpdate.setSystem_date(systemDateDto.getSystem_date());
            systemDateRepository.save(systemDateToUpdate);
        } else {
            throw new RuntimeException("No SystemDate found to update.");
        }
    }


}
