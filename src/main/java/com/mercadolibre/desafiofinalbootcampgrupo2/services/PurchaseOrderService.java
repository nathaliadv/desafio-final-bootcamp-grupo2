package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BuyerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.PurchaseItemDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.PurchaseOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TotalDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.lang.String.format;

@Service
public class PurchaseOrderService {

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @Autowired
    private BuyerDAO buyerDAO;

    @Autowired
    private PurchaseItemDTO purchaseItemDTO;

    public TotalDTO getTotalPurchaseOrder(PurchaseOrderDTO purchase) {
        BigDecimal total = BigDecimal.ZERO;

        for (ProductDTO productDTO : purchase.getProducts()) {
            total = getTotalPrice(productDTO).add(total);
        }

        return new TotalDTO(total);
    }

    public PurchaseOrder savePurchaseOrder(PurchaseOrderDTO purchaseDTO) {
        Buyer buyer = buyerDAO.findById(purchaseDTO.getBuyerId())
                .orElseThrow(() -> new RepositoryException("Buyer not exists in the Database"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .purchaseStatus(new PurchaseStatus(2L, "PENDING"))
                .buyer(buyer)
                .date(LocalDate.now())
                .build();

        PurchaseOrder purchase = purchaseOrderDAO.save(purchaseOrder);
        savePurchaseItem(purchaseDTO, purchase);
        return purchase;
    }

    private void savePurchaseItem(PurchaseOrderDTO purchaseDTO, PurchaseOrder PurchaseOrder) {
        for (ProductDTO product : purchaseDTO.getProducts()) {
            Advertising advertising = advertisingDAO.findById(product.getAdvertisingId())
                    .orElseThrow(() -> new RepositoryException("Product not exists in the Database"));

            PurchaseItens purchaseItens = PurchaseItens
                    .builder()
                    .purchaseOrder(PurchaseOrder)
                    .quantity(product.getQuantity())
                    .advertising(advertising)
                    .build();

            purchaseItemDTO.save(purchaseItens);
        }
    }

    private BigDecimal getTotalPrice(ProductDTO product) {
        Advertising advertising = advertisingDAO.findById(product.getAdvertisingId())
                .orElseThrow(() -> new RepositoryException("Product not exists in the Database"));

        Integer quantityAvailable = getQuantityAvailable(advertising);
        Integer productQuantity = product.getQuantity();

        if (product.getQuantity() > quantityAvailable) {
            throw new RepositoryException
                    (format("Quantity %s not available in stock, quantity %s in stock", productQuantity, quantityAvailable));
        }

        return advertising.getPrice().multiply(new BigDecimal(product.getQuantity()));
    }

    private Integer getQuantityAvailable(Advertising ad) {
        return ad.getBatchs().stream()
                .map(Batch::getCurrentQuantity)
                .reduce(0, Integer::sum);
    }
}
