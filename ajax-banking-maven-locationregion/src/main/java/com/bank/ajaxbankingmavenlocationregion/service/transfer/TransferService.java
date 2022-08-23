package com.bank.ajaxbankingmavenlocationregion.service.transfer;

import com.bank.ajaxbankingmavenlocationregion.model.Transfer;
import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferHistoryDTO;
import com.bank.ajaxbankingmavenlocationregion.service.IGeneralService;

import java.util.List;

public interface TransferService extends IGeneralService<Transfer> {
    List<TransferHistoryDTO> findAllHistories();
    String doTransfer(Transfer transfer);
}
