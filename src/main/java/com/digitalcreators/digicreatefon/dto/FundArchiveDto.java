package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class FundArchiveDto {

    private String fundCode;
    private Date date;
    private float price;
}
