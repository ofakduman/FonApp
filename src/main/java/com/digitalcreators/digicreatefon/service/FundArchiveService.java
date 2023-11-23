package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundArchiveDto;
import com.digitalcreators.digicreatefon.model.FundArchive;

import java.util.List;

public interface FundArchiveService {

    public FundArchive saveFundArchive(FundArchive fundArchive);

    public List<FundArchive> getAllFundArchives();

    public FundArchive updateFundArchive(Long id, FundArchiveDto fundArchiveDto);

    public void deleteById(Long id);

}
