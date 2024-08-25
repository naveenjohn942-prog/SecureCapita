package io.getarrays.securecapita.service.implementation;

import io.getarrays.securecapita.model.Invoice;
import io.getarrays.securecapita.repository.InvoiceRepository;
import io.getarrays.securecapita.service.InvoiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    @Override
    public Iterable<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }
}
