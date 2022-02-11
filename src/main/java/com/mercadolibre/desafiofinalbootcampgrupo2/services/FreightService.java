package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.FreightDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.FreightCostDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.FreightCostResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Freight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreightService {

    @Autowired
    private FreightDAO freightDAO;

    public FreightCostResponseDTO getCost(String initial) {
        Freight freight = freightDAO.findById(1L)
                .orElseThrow(() -> new RepositoryException("Initial state not exists in database, please contact the administrator"));
        return new FreightCostResponseDTO(freightDAO.findByInitial(initial.toUpperCase()).getShippingCost());
    }

    public Freight saveFreight(FreightCostDTO freightDTO) {
        return freightDAO.save(convertFreightDTOToFreight(freightDTO));
    }


    private Freight convertFreightDTOToFreight(FreightCostDTO freightDTO) {
        return Freight.builder()
                .capital(freightDTO.getCapital())
                .region(freightDTO.getRegion())
                .shippingCost(freightDTO.getShippingCost())
                .initial(freightDTO.getInitial())
                .state(freightDTO.getState())
                .build();
    }
}
