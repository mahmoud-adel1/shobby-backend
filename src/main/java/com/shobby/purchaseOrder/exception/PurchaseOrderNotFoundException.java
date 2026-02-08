package com.shobby.purchaseOrder.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {
  public PurchaseOrderNotFoundException(String message) {
    super(message);
  }
}
