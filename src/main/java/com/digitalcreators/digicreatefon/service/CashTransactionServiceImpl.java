package com.digitalcreators.digicreatefon.service;



import com.digitalcreators.digicreatefon.dto.CashTransactionDto;
import com.digitalcreators.digicreatefon.dto.GeneralCashTransactionDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.CashTransaction;
import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.repository.CashTransactionArchiveRepository;
import com.digitalcreators.digicreatefon.repository.CashTransactionRepository;
import com.digitalcreators.digicreatefon.repository.CustomerBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashTransactionServiceImpl implements CashTransactionService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CashTransactionRepository cashTransactionRepository;
    private final CustomerBalanceRepository customerBalanceRepository;
//    private final CustomerBalanceServiceImpl customerBalanceService;

    @Autowired
    CustomerBalanceService customerBalanceService;

    private final CashTransactionArchiveRepository cashTransactionArchiveRepository;

    public CashTransaction saveCashTransaction(CashTransaction cashTransaction){
        return cashTransactionRepository.save(cashTransaction);
    }

    public CashTransaction createCashTransaction(CashTransactionDto cashTransactionDto){
        CustomerBalance customerBalance=customerBalanceService.getBalanceByCustomerId(cashTransactionDto.getCustomerId());

        if(cashTransactionDto.getRoute() == 1){ //para yatırma
            customerBalance.setBalance(customerBalance.getBalance() + cashTransactionDto.getAmount());
            customerBalanceService.saveCustomerBalance(customerBalance);

        }
        else{ //para çekme
            if(customerBalance.getBalance() < cashTransactionDto.getAmount()){
                throw new RuntimeException("Çekmek istediğiniz miktar sahip olduğunuz bakiyeden fazla!");
            }
            else { //Yeterli bakiye varsa parayı çekiyor.
                customerBalance.setBalance(customerBalance.getBalance() - cashTransactionDto.getAmount());
                customerBalanceService.saveCustomerBalance(customerBalance);
            }
        }

        CashTransaction cashTransaction=new CashTransaction(cashTransactionDto);
        return saveCashTransaction(cashTransaction);

    }

    public void rollBackTransaction(CashTransaction cashTransaction){
        CustomerBalance customerBalance = customerBalanceRepository.findByCustomerId(cashTransaction.getCustomerId());

        if(cashTransaction.getRoute() == 1) { //para yatırma iptali
            customerBalance.setBalance(customerBalance.getBalance() - cashTransaction.getAmount());
            customerBalanceService.saveCustomerBalance(customerBalance);
        }
        else { //para çekme iptali
            customerBalance.setBalance(customerBalance.getBalance() + cashTransaction.getAmount());
            customerBalanceService.saveCustomerBalance(customerBalance);
        }
    }

    @Override
    public void rollBackTransaction(GeneralCashTransactionDto generalCashTransactionDto) {
        if (generalCashTransactionDto.getIsToday()){
            CashTransaction cashTransaction = new CashTransaction(generalCashTransactionDto);
            rollBackTransaction(cashTransaction);
            cashTransaction.setCanceled(true);
            cashTransactionRepository.save(cashTransaction);
        }else{
//            CashTransactionArchive cta = new CashTransactionArchive(generalCashTransactionDto);
            Long transaction_id = generalCashTransactionDto.getCashtransactionid();
            CashTransactionArchive cta = cashTransactionArchiveRepository.getCashTransactionArchiveByCashTransactionId(transaction_id);
            CashTransaction cashTransaction = new CashTransaction(generalCashTransactionDto);
            rollBackTransaction(cashTransaction);
            cta.setCanceled(true);
            cashTransactionArchiveRepository.save(cta);

        }
    }

    @Override
    public void rollBackTransaction(long cash_transaction_id) {
        CashTransactionArchive canceled_one = this.cashTransactionArchiveRepository.findById(cash_transaction_id).get();

        CashTransaction cashTransaction = new CashTransaction();
        cashTransaction.setAmount(canceled_one.getAmount());
    }

    public CashTransactionArchive addCashTransactionToArchive(CashTransaction cashTransaction, SystemDateDto systemDate){


//        cashTransactionArchiveRepository.findByCashTransactionId(cashTransaction.getId());
        CashTransactionArchive cashTransactionArchive = new CashTransactionArchive(cashTransaction);



        cashTransactionArchive.setDate(systemDate.getSystem_date());

        return cashTransactionArchiveRepository.save(cashTransactionArchive);
    }

    public List<CashTransaction> getAllCashTransactions(){
        return cashTransactionRepository.findAll();
    }

    public void deleteById(Long id){
        cashTransactionRepository.deleteById(id);
    }

    @Transactional
    public void nextDay(SystemDateDto systemDateDto) {
        List<CashTransaction> allCashTransactions = getAllCashTransactions();
        for (CashTransaction cashTransaction : allCashTransactions) {
            if (cashTransaction != null){
                addCashTransactionToArchive(cashTransaction, systemDateDto);
                entityManager.detach(cashTransaction);  // Detach the entity after processing
            }
        }
        entityManager.flush();
        entityManager.clear();
        cashTransactionRepository.deleteAll();
    }

    public List<CashTransactionArchive> getFilteredData(Date startDate, Date endDate) {
        return cashTransactionArchiveRepository.findTransactionsBetweenDates(startDate, endDate);
    }


}
