package com.bank.ajaxbankingmavenlocationregion.controller;


import com.bank.ajaxbankingmavenlocationregion.model.dto.TransferHistoryDTO;
import com.bank.ajaxbankingmavenlocationregion.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping
    public ModelAndView showListTransferPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("transfer/transferInfo");

        List<TransferHistoryDTO> transfers = transferService.findAllHistories();
        BigDecimal totalFeesAmount = new BigDecimal ( 0L );
        for (TransferHistoryDTO transfer : transfers) {
            totalFeesAmount = totalFeesAmount.add ( transfer.getFeesAmount () );
        }
        modelAndView.addObject("transfers", transfers);
        modelAndView.addObject ( "totalFeesAmount", String.valueOf ( totalFeesAmount ) );

        return modelAndView;
    }
}
