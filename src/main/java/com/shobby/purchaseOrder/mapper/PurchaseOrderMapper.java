package com.shobby.purchase.mapper;

import com.shobby.purchase.dto.PurchaseCommand;
import com.shobby.purchase.dto.PurchaseRequest;
import com.shobby.purchase.dto.PurchaseResult;
import com.shobby.purchase.entity.Purchase;

public class PurchaseMapper {

    public static PurchaseCommand toCommand(PurchaseRequest purchaseRequest) {
        return PurchaseCommand
                .builder()
                .orderNumber(purchaseRequest.getOrderNumber())
                .orderDate(purchaseRequest.getOrderDate())
                .deliveryDate(purchaseRequest.getDeliveryDate())
                .totalPrice(purchaseRequest.getTotalPrice())
                .supplierId(purchaseRequest.getSupplierId())
                .build();
    }

    public static Purchase toEntity(PurchaseCommand purchaseCommand) {
        return Purchase
                .builder()
                .orderNumber(purchaseCommand.getOrderNumber())
                .orderDate(purchaseCommand.getOrderDate())
                .deliveryDate(purchaseCommand.getDeliveryDate())
                .totalPrice(purchaseCommand.getTotalPrice())
                .build();
    }


    public static PurchaseResult toResult(Purchase purchase) {

    }
}
