package com.shobby.purchase.service;

import com.shobby.purchase.dto.PurchaseCommand;
import com.shobby.purchase.dto.PurchaseResult;
import com.shobby.purchase.entity.Purchase;
import com.shobby.purchase.mapper.PurchaseMapper;
import com.shobby.purchase.repository.PurchaseRepository;
import com.shobby.security.config.CustomUserDetails;
import com.shobby.supplier.service.SupplierService;
import com.shobby.user.entity.User;
import com.shobby.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final SupplierService supplierService;

    public PurchaseResult create(PurchaseCommand purchaseCommand) {
        Purchase purchase = PurchaseMapper.toEntity(purchaseCommand);
        CustomUserDetails customUserDetails = (CustomUserDetails) Objects.requireNonNull
                        (SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();
        assert customUserDetails != null;
        Optional<User> user = userService.getUserByUsername(customUserDetails.getUsername());
        user.ifPresent(purchase::setAdmin);
        purchase.setSupplier(supplierService.getSupplierOrThrow(purchaseCommand.getSupplierId()));
        Purchase savedPurchase = purchaseRepository.save(purchase);
        return PurchaseMapper.toResult(savedPurchase);
    }
}
