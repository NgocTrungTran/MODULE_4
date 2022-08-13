package com.bank.bankingmaven.service;

import com.bank.bankingmaven.model.Transfer;
import com.bank.bankingmaven.model.dto.TransferHistoryDTO;

import java.util.List;

public interface TransferService extends IGeneralService<Transfer>{
    List<TransferHistoryDTO> findAllHistories();
    String doTransfer(Transfer transfer);
}
