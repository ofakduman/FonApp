package com.digitalcreators.digicreatefon.bean;

import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.model.FundStock;
import com.digitalcreators.digicreatefon.service.CustomerBalanceService;
import com.digitalcreators.digicreatefon.service.CustomerService;
import com.digitalcreators.digicreatefon.service.FundStockService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@Getter
@Setter
@Controller(value = "portfolioBean")
@ViewScoped
public class PortfolioBean {

    @Autowired
    private FundStockService fss;

    public void setFss(FundStockService fss) {
        this.fss = fss;
    }

    @Autowired
    private CustomerService cs;

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    @Autowired
    private CustomerBalanceService cbs;

    public void setCbs(CustomerBalanceService cbs) {
        this.cbs = cbs;
    }



    private String fundCode;
    private String fundName;
    private float fundPrice;
    private Long customerId;
    private Long amount;
    private String customerNameSurname;
    public float customerBalance;

    private List<FundStock> fundStockList;
    private List<Customer> customerList;
    private List<String> fundCodeList;
    private List<Long> amountList;
    private List<Float> fundPriceList;
    public PieChartModel pieModel;

    @PostConstruct
    public void init(){
        createPieModel();

        customerList=fetchCustomers();

    }

    private void createPieModel() {
        pieModel = new PieChartModel();

        // Örnek veriler (gerçekte veritabanından veya başka bir kaynaktan almalısınız)
        pieModel.set("Fon A", 540);
        pieModel.set("Fon B", 325);
        pieModel.set("Fon C", 702);
        pieModel.set("Fon D", 421);
    }

    private List<Customer> fetchCustomers(){
        return this.cs.getAllCustomers();
    }

    public void getFundStocksByCustomerId(){
        this.fundStockList = fss.getFundStocksByCustomerId(this.customerId);
    }

    public void getCustomerBalanceFunc(){
        CustomerBalance cb = cbs.getBalanceByCustomerId(this.customerId);

        this.customerBalance = cb.getBalance();

        getFundStocksByCustomerId(); // fon stoklarını da al

    }

    public void setFundStockInfo(){

        for (FundStock fundStock : fundStockList) {
            fundCodeList.add(fundStock.getFundCode());
        }
    }
}
