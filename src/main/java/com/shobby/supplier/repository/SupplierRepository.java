package com.shobby.supplier.repository;

import com.shobby.supplier.entity.Supplier;
import com.shobby.supplier.service.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Page<Supplier> findAllByEnabledTrue(Pageable pageable);
    Optional<Supplier> findByIdAndEnabledTrue(Long id);
}
