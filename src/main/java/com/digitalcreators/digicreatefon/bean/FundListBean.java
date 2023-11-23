package com.digitalcreators.digicreatefon.bean;

import com.digitalcreators.digicreatefon.dto.FundWithPrice;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.model.FundPrice;
import com.digitalcreators.digicreatefon.service.FundPriceService;
import com.digitalcreators.digicreatefon.service.FundService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Controller(value = "fundListBean")
@ViewScoped
public class FundListBean {

    @Autowired
    private FundService fs;

    @Autowired
    private FundPriceService fps;
    public void setFss(FundService fs) {
        this.fs = fs;
    }

    private String fundCode;
    private String fundName;
    private float fundPrice;
    private String searchText;
    public FundWithPrice selectedFundWithPrice;
    private List<FundWithPrice> fundWithPriceList;
    private List<Fund> funds = new ArrayList<>();



    private List<Fund> fundList;


    @PostConstruct
    public void init(){
        funds = this.fs.getAllFunds();
        fundWithPriceList = fetchFundsWithPrice();
    }

    private List<FundWithPrice> fetchFundsWithPrice(){

        List<FundWithPrice> result = new ArrayList<>();
        for(Fund fund : this.funds) {
            FundPrice fundPrice = this.fps.getFundPriceByFundCode(fund.getFundCode()); // FundPrice bilgisini nasıl getirdiğinize bağlı olarak bu bilgiyi alın.
            result.add(new FundWithPrice(fund, fundPrice));
        }

        return result;
    }

    public void searchFunds(){
        if (!searchText.equals(""))
            this.funds = this.fs.getFundByFundCodeContaining(searchText);
        else
            funds = this.fs.getAllFunds();


        fundWithPriceList = fetchFundsWithPrice();
    }

    public void editFund(FundWithPrice _selectedFundWithPrice) {
        this.selectedFundWithPrice = _selectedFundWithPrice;

        for (int i = 0; i < 1000; i++) {
            System.out.println(_selectedFundWithPrice.getCurrentPrice());
        }
    }

    public void updateFund(){
        for (int i = 0; i <100; i++) {
            System.out.println(selectedFundWithPrice.getCurrentPrice());
        }

        this.fps.updateFundPrice(fps.getFundPriceByFundCode(selectedFundWithPrice.getFundCode()).getId(),
                selectedFundWithPrice.getCurrentPrice());
        searchFunds();
    }

}
