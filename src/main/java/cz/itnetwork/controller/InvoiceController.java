package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping({"/invoices", "/invoices/"})
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping({"/invoices", "/invoices/"})
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getInvoices(invoiceFilter);
    }

    @GetMapping({"/invoices/{id}", "/invoices/{id}/"})
    public InvoiceDTO getInvoice(@PathVariable long id) {
        return invoiceService.getInvoice(id);
    }

    @GetMapping({"/identification/{identificationNumber}/sales", "/identification/{identificationNumber}/sales/"})
    public List<InvoiceDTO> getInvoicesBySeller(@PathVariable String identificationNumber) {
        return invoiceService.getInvoicesBySeller(identificationNumber);
    }

    @GetMapping({"/identification/{identificationNumber}/purchases", "/identification/{identificationNumber}/purchases/"})
    public List<InvoiceDTO> getInvoicesByBuyer(@PathVariable String identificationNumber) {
        return invoiceService.getInvoicesByBuyer(identificationNumber);
    }

    @PutMapping({"/invoices/{id}", "/invoices/{id}/"})
    public InvoiceDTO updateInvoice(@PathVariable long id, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @DeleteMapping({"/invoices/{id}", "/invoices/{id}/"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long id){
        invoiceService.removeInvoice(id);
    }

    @GetMapping({"/invoices/statistics", "/invoices/statistics/"})
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        return invoiceService.getInvoiceStatistics();
    }

}
