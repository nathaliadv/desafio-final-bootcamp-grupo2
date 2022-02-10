package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.BatchDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDueDateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class BatchService implements EntityService<Batch> {

    private BatchDAO batchDAO;

    public BatchService(BatchDAO batchDAO) {
        this.batchDAO = batchDAO;
    }

    @Override
    public Batch findById(Long id) {
        return batchDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Batch not exists in the Database"));
    }

    public void deleteAllBatchByInboundOrder(InboundOrder inboundOrder) {
        batchDAO.deleteAllByInboundOrder(inboundOrder);
    }

    public List<BatchDueDateDTO> findByDueDate(Long sectionId, Long warehouseId, Long numberDays) {
        List<BatchDueDateDTO> products = batchDAO.findByDueDate(sectionId, warehouseId, convertNumberDaysToDate(numberDays));
        if(products.isEmpty()){
            throw new RepositoryException("Not products to expire in " + numberDays + " days");
        }
        return products;
    }

    public List<BatchDueDateDTO> findByDueDateByCat(Long productTypeId, Long numberDays, String orderBy) {
        List<BatchDueDateDTO> products = batchDAO.findByDueDateByCat(productTypeId, convertNumberDaysToDate(numberDays), Sort.Direction.fromOptionalString(orderBy));
        if(products.isEmpty()){
            throw new RepositoryException("Not products to expire in " + numberDays + " days");
        }
        validateOrderBy(orderBy, products);
        return products;
    }

    public LocalDate convertNumberDaysToDate(Long numberDays){
        return LocalDate.now().plusDays(numberDays);
    }

    public void getBatchDueDateAsc(List<BatchDueDateDTO> products){
        products.sort(Comparator.comparing(BatchDueDateDTO::getDueDate));
    }

    public void getBatchDueDateDesc(List<BatchDueDateDTO> products){
        products.sort(Comparator.comparing(BatchDueDateDTO::getDueDate).reversed());
    }

    public void validateOrderBy(String orderBy, List<BatchDueDateDTO> products){
         switch(orderBy.toUpperCase()){
             case "ASC":
                 getBatchDueDateAsc(products);
                 break;

             case "DESC":
                 getBatchDueDateDesc(products);
                 break;

             default: throw new RepositoryException("Invalid value to orderBy");
         }
    }
}