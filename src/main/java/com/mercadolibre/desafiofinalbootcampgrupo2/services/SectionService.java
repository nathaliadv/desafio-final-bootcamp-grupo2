package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.SectionDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DontMatchesException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.SectionSpaceNotAvailableException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements EntityService<Section> {

    private SectionDAO sectionDAO;

    public SectionService(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @Override
    public Section findById(Long id) {
        return sectionDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Section not exists in the Database, please contact the administrator"));
    }


    public void verifyIfSectionHaveSpaceEnoughToAddBatches(Section section, List<Batch> batchs) {
        for (Batch batch : batchs) {
            if (section.calVolumeCheckin(batch) < 0) {
                throw new SectionSpaceNotAvailableException("Space not available in the section, please contact an administrator");
            }
        }
    }

    protected void verifyIfSectorExistsInWarehouse(Long sectionCode, Long warehouseCode) {
        if (!findById(sectionCode).getWarehouse().getId().equals(warehouseCode))
            throw new RepositoryException("The mentioned Section don't exists in mentioned Warehouse.");
    }

    protected void verifyIfRepresentativeWorksInSection(Long sectionCode, Long representativeCode) {
        if (!findById(sectionCode).getRepresentative().getId().equals(representativeCode)) {
            throw new DontMatchesException("Representative don't matches with mentioned Section.");
        }
    }
}