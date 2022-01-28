package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.SectionDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {

    @Autowired
    private SectionDAO sectionDAO;

    public void calVolumeCheckin(Batch batch, Section section){

        section.calVolumeCheckin(batch);
        sectionDAO.save(section);
    }

    public void calVolumeCheckout(Batch batch, Section section){

        section.calVolumeCheckout(batch);
        sectionDAO.save(section);
    }
}
