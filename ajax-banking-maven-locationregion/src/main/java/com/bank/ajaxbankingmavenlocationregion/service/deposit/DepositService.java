package com.bank.ajaxbankingmavenlocationregion.service.deposit;

import com.bank.ajaxbankingmavenlocationregion.model.Customer;
import com.bank.ajaxbankingmavenlocationregion.model.Deposit;
import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
import com.bank.ajaxbankingmavenlocationregion.service.IGeneralService;

public interface DepositService extends IGeneralService<Deposit> {
    Customer doDeposit(Deposit deposit);

}
