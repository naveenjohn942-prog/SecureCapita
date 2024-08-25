package io.getarrays.securecapita.service;


import io.getarrays.securecapita.model.Customer;
import io.getarrays.securecapita.model.Invoice;

public interface InvoiceService {
    Iterable<Invoice> getAllInvoice();
}
