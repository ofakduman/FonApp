package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import com.digitalcreators.digicreatefon.dto.FundStockDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.*;
import com.digitalcreators.digicreatefon.model.FundTransaction;
import com.digitalcreators.digicreatefon.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundTransactionServiceImpl implements FundTransactionService{

    private final FundTransactionRepository fundTransactionRepository;
    private final FundTransactionArchiveRepository fundTransactionArchiveRepository;


    @Autowired
    private FundStockService fundStockService; //böyle yapıyoruz implları oluşturunca. Interfacei yazıyoz buraya. Aynı beandeki gibi. Burada da servicei kullanıyorduk eskiden aynı devam bunu yaptıktan sonra.


    private final FundStockRepository fundStockRepository;
    private final FundRepository fundRepository;
    private final CustomerBalanceRepository customerBalanceRepository;


    @Autowired
    CustomerBalanceService customerBalanceService;

    private final FundPriceRepository fundPriceRepository;



    public FundTransaction createTransaction(FundTransactionDto fundTransactionDto){
        FundPrice fundPrice=fundPriceRepository.findByFundCode(fundTransactionDto.getFundcode());//eklendi*******
        FundStock fs=fundStockRepository.findByFundCodeAndCustomerId(fundTransactionDto.getFundcode(),fundTransactionDto.getCustomerId());
        if(fs==null){ //adam yeni bir fon alıyorsa
            CustomerBalance customerBalance=customerBalanceRepository.findByCustomerId(fundTransactionDto.getCustomerId());

            if(fundTransactionDto.getRoute()==1){ //alış durumunda route=1
                if(customerBalance.getBalance() < fundPrice.getCurrentPrice()){ //fundPrice geldi fundTransactionDto yerine
                    throw new RuntimeException("Bakiyeniz yetersiz.");
                }
                customerBalance.setBalance(customerBalance.getBalance() - (fundPrice.getCurrentPrice()*fundTransactionDto.getAmount()));  //fundPrice geldi fundTransactionDto yerine
                customerBalanceService.saveCustomerBalance(customerBalance);


                FundStockDto fundStockDto =new FundStockDto(fundTransactionDto);
                FundStock fundStock=new FundStock(fundStockDto);
                fundStock.setTotalPrice(fundPrice.getCurrentPrice() * fundTransactionDto.getAmount()); //eklendi*******
                fundStockService.saveFundStock(fundStock);
            }

            else{
                throw new RuntimeException("Sahip olmadığınız bir fonu satamazsınız.");
            }


        }
        else{ //adam zaten sahip olduğu bir fonla işlem yapıyorsa.
            CustomerBalance customerBalance=customerBalanceRepository.findByCustomerId(fundTransactionDto.getCustomerId());


            if(fundTransactionDto.getRoute()==1){ //sahip olduğu fona ekleme yapıyor
                if(customerBalance.getBalance() < fundPrice.getCurrentPrice()){
                    throw new RuntimeException("Bakiyeniz yetersiz.");
                }
                customerBalance.setBalance(customerBalance.getBalance() - (fundPrice.getCurrentPrice() * fundTransactionDto.getAmount()));
                customerBalanceService.saveCustomerBalance(customerBalance);

                fs.setAmount(fs.getAmount() + fundTransactionDto.getAmount());
                fs.setTotalPrice(fs.getTotalPrice() + (fundPrice.getCurrentPrice() * fundTransactionDto.getAmount()));
                fundStockService.saveFundStock(fs);

            }
            else{ //sahip olduğu fondan satış yapıyor
                if(fs.getAmount()<fundTransactionDto.getAmount()){
                    throw new RuntimeException("Satış için yeterli fonunuz bulunmuyor.");
                }
                customerBalance.setBalance(customerBalance.getBalance() + (fundPrice.getCurrentPrice() * fundTransactionDto.getAmount())); //bakiye arttı
                customerBalanceService.saveCustomerBalance(customerBalance);

                fs.setAmount(fs.getAmount() - fundTransactionDto.getAmount());
                fs.setTotalPrice(fs.getTotalPrice() - (fundPrice.getCurrentPrice() * fundTransactionDto.getAmount()));  //bu total price neydi ya galiba cüzdandaki fonun değeri düşüyo
                fundStockService.saveFundStock(fs);

            }

        }

        FundTransaction fundTransaction=new FundTransaction(fundTransactionDto);
        fundTransaction.setTransactionPrice(fundPrice.getCurrentPrice());
        return fundTransactionRepository.save(fundTransaction);

    }

    public void rollBackTransaction(FundTransaction fundTransaction){
        FundStock fs=fundStockRepository.findByFundCodeAndCustomerId(fundTransaction.getFundcode(),fundTransaction.getCustomerId());
        CustomerBalance cb = customerBalanceRepository.findByCustomerId(fundTransaction.getCustomerId());

        if(fundTransaction.getRoute()==1){ //alım iptali
            fs.setAmount(fs.getAmount() - fundTransaction.getAmount());
            fs.setTotalPrice(fs.getTotalPrice() - fundTransaction.getTransactionPrice());
            fundStockService.saveFundStock(fs);

            cb.setBalance(cb.getBalance() + fundTransaction.getTransactionPrice());
            customerBalanceService.saveCustomerBalance(cb);
        }
        else{ //satış iptali

            if(cb.getBalance() < fundTransaction.getTransactionPrice()){
                throw new RuntimeException("Bakiyeniz yetersiz.");
            }
            fs.setAmount(fs.getAmount() + fundTransaction.getAmount());
            fs.setTotalPrice(fs.getTotalPrice() + fundTransaction.getTransactionPrice());
            fundStockService.saveFundStock(fs);

            cb.setBalance(cb.getBalance() - fundTransaction.getTransactionPrice());
            customerBalanceService.saveCustomerBalance(cb);

        }

    }

    public FundTransactionArchive addFundTransactionToArchive(FundTransaction fundTransaction, SystemDateDto systemDate){
        FundTransactionArchive fundTransactionArchive = new FundTransactionArchive(fundTransaction);
        fundTransactionArchive.setDate(systemDate.getSystem_date());
        return fundTransactionArchiveRepository.save(fundTransactionArchive);
    }

    public FundTransaction updateFundTransaction(Long id, FundTransactionDto fundTransactionDto){
        FundTransaction existingFundTransaction= fundTransactionRepository.findById(id).get();
        FundPrice fundPrice=fundPriceRepository.findByFundCode(fundTransactionDto.getFundcode());//eklendi*******
        existingFundTransaction.setFundcode(fundTransactionDto.getFundcode());
        existingFundTransaction.setCustomerId(fundTransactionDto.getCustomerId());
        existingFundTransaction.setAmount(fundTransactionDto.getAmount());
        existingFundTransaction.setRoute(fundTransactionDto.getRoute());
        existingFundTransaction.setTransactionPrice(fundPrice.getCurrentPrice());


        return fundTransactionRepository.save(existingFundTransaction);
    }

    public List<FundTransaction> getAllFundTransactions(){
        return fundTransactionRepository.findAll();
    }

    public void deleteById(Long id){
        fundTransactionRepository.deleteById(id);
    }

    @Transactional
    public void nextDay(SystemDateDto systemDateDto) {
        for (FundTransaction fundTransaction : getAllFundTransactions()) {
            if (fundTransaction != null) {
                addFundTransactionToArchive(fundTransaction, systemDateDto);
            }
        }
        fundTransactionRepository.deleteAll();
    }

    @Override
    public List<FundTransactionArchive> getFilteredFundArchiveData(Date date, Date date1) {

        return fundTransactionArchiveRepository.findTransactionsBetweenDates(date, date1);
    }


}
