package cz.itnetwork.service;

import cz.itnetwork.dto.BatchOperationsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoiceEntity = invoiceMapper.toEntity(invoiceDTO);
        invoiceEntity.setSeller(personService.fetchPersonById(invoiceEntity.getSeller().getId()));
        invoiceEntity.setBuyer(personService.fetchPersonById(invoiceEntity.getBuyer().getId()));

        invoiceEntity = invoiceRepository.save(invoiceEntity);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        Page<InvoiceEntity> invoicesPage = invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()));
        List<InvoiceEntity> invoiceEntities = invoicesPage.getContent();
        return invoiceEntities.stream()
                .map(e -> invoiceMapper.toDTO(e))
                .toList();
    }

    @Override
    public InvoiceDTO getInvoice(long id) {
        InvoiceEntity invoiceEntity = fetchInvoiceById(id);
        return invoiceMapper.toDTO(invoiceEntity);
    }

    @Override
    public List<InvoiceDTO> getInvoicesBySeller(String identificationNumber) {
        List<InvoiceEntity> invoiceEntity = invoiceRepository.findBySeller_IdentificationNumber(identificationNumber);
        return invoiceEntity.stream()
                .map(e -> invoiceMapper.toDTO(e))
                .toList();

    }

    @Override
    public List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber) {
        List<InvoiceEntity> invoiceEntity = invoiceRepository.findByBuyer_IdentificationNumber(identificationNumber);
        return invoiceEntity.stream()
                .map(e -> invoiceMapper.toDTO(e))
                .toList();
    }

    @Override
    public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(id);
        InvoiceEntity fetchedInvoice = fetchInvoiceById(id);
        fetchedInvoice.setSeller(personService.fetchPersonById(invoiceDTO.getSeller().getId()));
        fetchedInvoice.setBuyer(personService.fetchPersonById(invoiceDTO.getBuyer().getId()));
        invoiceMapper.updateInvoiceEntity(invoiceDTO, fetchedInvoice);
        return invoiceMapper.toDTO(invoiceRepository.save(fetchedInvoice));
    }

    @Override
    public void removeInvoice(long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public void removeSelectedInvoices(BatchOperationsDTO batchOperationsDTO) {
        List<Integer>
    }

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
       return invoiceRepository.getInvoiceStatistics();
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

}
