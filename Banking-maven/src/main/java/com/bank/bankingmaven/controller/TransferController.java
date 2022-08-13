package com.bank.bankingmaven.controller;

import com.bank.bankingmaven.model.dto.TransferHistoryDTO;
import com.bank.bankingmaven.service.TransferService;
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
