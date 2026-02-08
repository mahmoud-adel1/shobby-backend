package com.shobby.purchase.controller;

import com.shobby.purchase.dto.PurchaseCommand;
import com.shobby.purchase.dto.PurchaseRequest;
import com.shobby.purchase.dto.PurchaseResponse;
import com.shobby.purchase.dto.PurchaseResult;
import com.shobby.purchase.mapper.PurchaseMapper;
import com.shobby.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseResponse create(PurchaseRequest purchaseRequest) {
        PurchaseCommand purchaseCommand = PurchaseMapper.toCommand(purchaseRequest);
        PurchaseResult purchaseResult = purchaseService.create(purchaseCommand);

    }
}
