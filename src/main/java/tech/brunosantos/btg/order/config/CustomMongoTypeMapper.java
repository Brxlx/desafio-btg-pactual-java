package tech.brunosantos.btg.order.config;

import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;

public class CustomMongoTypeMapper extends DefaultMongoTypeMapper {

  public CustomMongoTypeMapper() {
    super(null);
  }
}
