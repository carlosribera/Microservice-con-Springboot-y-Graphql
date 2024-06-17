package com.softand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.softand.demo.common.exception.WorkOrderNotFoundException;
import com.softand.demo.models.WorkOrder;
import com.softand.demo.repositories.WorkOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorkOrderService {

    @Autowired
    WorkOrderRepository workOrderRepository;

    public WorkOrder getWorkOrderById(String id) {
        return workOrderRepository.findById(id)
                .orElseThrow(() -> new WorkOrderNotFoundException("WorkOrder not found with ID: " + id));
    }

    public List<WorkOrder> getAllWorkOrders() {
        List<WorkOrder> workOrderList = workOrderRepository.findAll();
        if (CollectionUtils.isEmpty(workOrderList)) {
            log.info("WorkOrder not found");
            return null;
        }
        return workOrderList;
    }

    public WorkOrder createWorkOrder(WorkOrder workOrder) {
        return workOrderRepository.save(workOrder);
    }

    public WorkOrder updateWorkOrder(String id, WorkOrder workOrderEntity) {
        WorkOrder workOrder = getWorkOrderById(id);
        workOrder.setDescription(workOrderEntity.getDescription());

        return workOrderRepository.save(workOrder);
    }

    public String deleteById(String id) {
        WorkOrder workOrder = getWorkOrderById(id);
        workOrderRepository.deleteById(workOrder.getId());
        return "WorkOrder by id: " + id + " deleted.";
    }
}
