package tips.smallapps.api.productgroup.data;

import java.util.HashMap;
import java.util.Map;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.exceptions.ProductGroupNameIsNotUniqueException;
import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;

public class FakeProductGroupDataService implements ProductGroupDataService {

  private Map<Long, ProductGroup> database = new HashMap<>();

  @Override
  public void save(ProductGroup entity) {
    boolean hasData = database.values().stream().anyMatch(it -> it.getName().equals(entity.getName()));
    if (hasData) {
      throw new ProductGroupNameIsNotUniqueException(entity.getName(), new NullPointerException());
    }
    database.put(entity.getId(), entity);
  }

  @Override
  public ProductGroup findByName(String name) {
    return database.values().stream().filter(it -> it.getName().equals(name)).findFirst().get();
  }

  @Override
  public void update(ProductGroup entity) {
    database.put(entity.getId(), entity);

  }

  @Override
  public ProductGroup findById(Long id) {
    return database.get(id);
  }

}
