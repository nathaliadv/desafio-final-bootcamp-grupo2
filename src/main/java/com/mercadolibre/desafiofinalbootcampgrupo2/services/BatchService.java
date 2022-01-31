package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BatchDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import org.springframework.stereotype.Service;

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
}