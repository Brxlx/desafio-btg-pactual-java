package tech.brunosantos.btg.order.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

@Service
public class GetTotalOnOrdersByCustomerIdService {
  private final MongoTemplate mongoTemplate;

  public GetTotalOnOrdersByCustomerIdService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public BigDecimal execute(Long customerId) {
    var aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("customerId").is(customerId)),
        Aggregation.group().sum("total").as("total"));

    var response = mongoTemplate.aggregate(aggregation, "orders", Document.class);

    @SuppressWarnings("null")
    var formattedResponse = new DecimalFormat("#.##")
        .format(new BigDecimal(response.getUniqueMappedResult().get("total").toString()));

    return new BigDecimal(formattedResponse);
  }
}
