package com.softand.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.softand.demo.models.WorkOrder;

public interface WorkOrderRepository extends MongoRepository<WorkOrder, String> {
}
