package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {

    /**
     * Adds new invoice
     * @param invoiceDTO Invoice to add
     * @return Added invoice with id
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Fetches invoices based on a filter
     * @param invoiceFilter Filtration parameters
     * @return Fetched invoices based on a filter
     */
    List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter);


    /**
     * Fetches an invoice with passed ID
     * @param id ID of an invoice
     * @return Fetched invoice
     */
    InvoiceDTO getInvoice(long id);

    /**
     * Fetches all invoices for a seller with passed identification number
     * @param identificationNumber Identification number of a person
     * @return List of sales of a person with passed identification number
     */
    List<InvoiceDTO> getInvoicesBySeller(String identificationNumber);

    /**
     * Fetches all invoices for a buyer with passed identification number
     * @param identificationNumber Identification number of a person
     * @return List of sales of a person with passed identification number
     */
    List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber);

    /**
     * Updates an invoice
     * @param id ID of an invoice
     * @param invoiceDTO Edited data to save
     * @return Updated invoice
     */
    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);

    /**
     * Removes an invoice
     * @param id ID of invoice to remove
     */
    void removeInvoice(long id);

    /**
     * Gets invoice statistics
     * @return Invoice statistics data
     */
    InvoiceStatisticsDTO getInvoiceStatistics();
}
