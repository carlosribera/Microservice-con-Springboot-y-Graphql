package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softand.demo.models.SaleDetail;
import com.softand.demo.repositories.SaleDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SaleDetailService {

    @Autowired
    SaleDetailRepository saleDetailRepository;

    public SaleDetail getSaleDetailById(String id) throws NotFoundException {
        return saleDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public List<SaleDetail> getAllSaleDetails() {
        List<SaleDetail> saleDetailList = saleDetailRepository.findAll();
        if (CollectionUtils.isEmpty(saleDetailList)) {
            log.info("Sale detail esta vacia");
            return null;
        }
        return saleDetailList;
    }

    public SaleDetail createSaleDetail(SaleDetail saleDetail) {
        return saleDetailRepository.save(saleDetail);
    }

    public SaleDetail updateSaleDetail(String id, SaleDetail saleDetailEntity) throws NotFoundException {
        SaleDetail saleDetail = getSaleDetailById(id);

        if (saleDetailEntity.getSaleId() != null) {

            saleDetail.setSaleId(saleDetailEntity.getSaleId());
            saleDetail.setProductId(saleDetailEntity.getProductId());
            saleDetail.setQuantity(saleDetailEntity.getQuantity());
            saleDetail.setProductPrice(saleDetailEntity.getProductPrice());
            saleDetail.setWorkOrderId(saleDetailEntity.getWorkOrderId());
            return saleDetailRepository.save(saleDetail);
        }
        return null;
    }

    public String deleteById(String id) throws NotFoundException {
        SaleDetail saleDetail = getSaleDetailById(id);

        if (saleDetail != null) {
        saleDetailRepository.deleteById(saleDetail.getId());
        return "SaleDetail by id: " + id + " deleted.";
        }
        return null;
    }
}
