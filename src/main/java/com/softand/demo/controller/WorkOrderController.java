package com.softand.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import com.softand.demo.models.WorkOrder;
import com.softand.demo.models.WorkOrderInput;
import com.softand.demo.service.WorkOrderService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@PreAuthorize("denyAll()")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @QueryMapping
    @PreAuthorize("hasAuthority('READ')")
    public WorkOrder getWorkOrderById(@Argument String id) {
        log.info("Query workOrder in GraphQL Server by id {}", id);
        return workOrderService.getWorkOrderById(id);
    }

    @QueryMapping
    @PreAuthorize("permitAll()")
    public List<WorkOrder> getAllWorkOrders() {
        return this.workOrderService.getAllWorkOrders();
    }

    @MutationMapping
    @PreAuthorize("permitAll()")
    public WorkOrder createWorkOrder(@Argument WorkOrderInput workOrderInput) {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setDescription(workOrderInput.getDescription());

        return this.workOrderService.createWorkOrder(workOrder);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public WorkOrder updateWorkOrder(@Argument String id, @Argument WorkOrderInput workOrderInput) {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setDescription(workOrderInput.getDescription());

        return this.workOrderService.updateWorkOrder(id, workOrder);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteWorkOrderById(@Argument String id) {
        this.workOrderService.deleteById(id);
        return "WorkOrder with id: " + id + " deleted successfully.";
    }
}
