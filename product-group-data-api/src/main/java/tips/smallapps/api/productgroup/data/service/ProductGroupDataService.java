package tips.smallapps.api.productgroup.data.service;

import tips.smallapps.api.productgroup.data.ProductGroup;

public interface ProductGroupDataService {

  void save(ProductGroup entity);

  ProductGroup findByName(String name);

  ProductGroup findById(Long id);

  void update(ProductGroup entity);

}
