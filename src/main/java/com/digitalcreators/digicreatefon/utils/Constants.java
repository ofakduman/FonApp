package com.digitalcreators.digicreatefon.utils;

public class Constants {

    public static final String BASE_URL = "http://localhost:8080";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String AUTHENTICATE_URL = BASE_URL + "/api/Personnels/authenticate";
    public static final String CUSTOMER_BY_ID_URL = BASE_URL + "/api/Customers/";
    public static final String CUSTOMER_BY_SURNAME_URL = BASE_URL + "/api/Customers/bySurname";
    public static final String CASH_TRANSACTION_ARCHIVE_BY_DATE = BASE_URL + "/api/CashTransactions/byDate";
    public static final String PERSONALS_BY_USERNAME_URL = BASE_URL + "/api/Personnels/byUsername";
    public static final String REGISTER_PERSONNEL_URL = BASE_URL + "/api/Personnels";
    public static final String CUSTOMER_PATCH_URL = BASE_URL + "/api/Customers/";
    public static final String CUSTOMER_UPDATE_URL = BASE_URL + "/api/Customers/";
    public static final String TRANSACTION_URL = BASE_URL + "/api/FundTransactions/newTransaction";
    public static final String GETFUNDS_URL = BASE_URL + "/api/Funds";
    public static final String GETALLCUSTOMERS = BASE_URL + "/api/Customers/all";
    public static final String CUSTOMERBALANCE_URL = BASE_URL + "/api/CustomerBalances";
    public static final String FUNDSTOCK_URL = BASE_URL + "/api/FundStocks";
    public static final String FUNDPRICE_URL = BASE_URL + "/api/FundsPrices";
    public static final String CASHTRANSACTION_URL = BASE_URL + "/api/CashTransactions";
    // Diğer sabit URL'leri veya değerleri burada tanımlayabilirsiniz.

    public static final String REGISTER_CUSTOMER_URL = BASE_URL + "/api/Customers";
    public static final String REGISTER_FUND_URL = BASE_URL + "/api/Funds";
    public static final String IS_USERNAME_AVAILABLE_URL = BASE_URL + "/api/Personnels/isUsernameAvailable";

    public static final String HASH_PASSWORD_URL = BASE_URL + "/api/Personnels/hashPassword";
    public static final String UPDATE_PERSONNEL_URL = BASE_URL + "/api/Personnels/updatePersonnel/";

    public static final String REGISTER_CUSTOMER_URL_DIRECT = BASE_URL + "/api/Customers/direct";


    public static final String CASH_TRANSACTION_ARCHIVE_URL = BASE_URL + "/api/CashTransactions/triggerNextDay";

    public static final String FUND_TRANSACTION_ARCHIVE_URL = BASE_URL + "/api/FundTransactions/triggerNextDay";



}
