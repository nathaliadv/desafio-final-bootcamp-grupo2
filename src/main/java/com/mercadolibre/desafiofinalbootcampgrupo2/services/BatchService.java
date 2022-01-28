package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BatchDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchService {

    private BatchDAO batchDAO;

    public BatchService(BatchDAO batchDAO) {
        this.batchDAO = batchDAO;
    }

    public List<Batch> saveListBatches(List<Batch> batches){
        return batchDAO.saveAll(batches);
    }
}