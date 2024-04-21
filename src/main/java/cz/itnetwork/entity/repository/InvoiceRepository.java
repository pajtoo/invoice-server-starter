package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    List<InvoiceEntity> findBySeller_IdentificationNumber(String identificationNumber);

    List<InvoiceEntity> findByBuyer_IdentificationNumber(String identificationNumber);

    @Query(value =  "SELECT NEW cz.itnetwork.dto.InvoiceStatisticsDTO" +
                    "(SUM(letosni.price),\n" +
                    "SUM(vsechno.price),\n" +
                    "COUNT(*))\n" +
                    "FROM invoice vsechno \n" +
                    "LEFT JOIN invoice letosni \n" +
                    "ON vsechno.id = letosni.id\n" +
                    "AND YEAR(letosni.issued) = YEAR(CURRENT_DATE)")
    InvoiceStatisticsDTO getInvoiceStatistics();

}
