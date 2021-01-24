package tr.com.minicrm.web.productgroup.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;
import tips.smallapps.productgroup.business.ProductGroupBusinessService;

@Configuration
public class ProductGroupBusinessServiceConfiguration {

  @Bean
  ProductGroupBusinessService productGroupBusinessService(ProductGroupDataService productGroupDataService) {
    return new ProductGroupBusinessService(productGroupDataService);
  }

}
