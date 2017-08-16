package com.dskimina.repositories;

import com.dskimina.data.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Daniel on 12.08.2017.
 */
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    List<Invoice> findByName(String lastName);
    Invoice getByIdentifier(String identifier);
}
