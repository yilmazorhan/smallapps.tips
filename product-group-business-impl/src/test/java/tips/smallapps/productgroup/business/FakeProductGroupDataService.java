package tips.smallapps.productgroup.business;

import java.util.HashMap;
import java.util.Map;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;

public class FakeProductGroupDataService implements ProductGroupDataService {

  private Map<String, ProductGroup> groupsByName = new HashMap<>();

  @Override
  public void save(ProductGroup entity) {
    groupsByName.put(entity.getName(), entity);
  }

  @Override
  public ProductGroup findByName(String name) {
    return groupsByName.get(name);
  }

  public void addToStore(ProductGroup sample) {
    groupsByName.put(sample.getName(), sample);

  }

  public void clear() {
    groupsByName.clear();
  }

  @Override
  public void update(ProductGroup entity) {
    ProductGroup pg =
        groupsByName.values().stream().filter(it -> it.getId().equals(entity.getId())).findFirst().orElse(null);
    if (null != pg) {
      groupsByName.remove(entity.getName());
      groupsByName.put(entity.getName(), entity);
    }
  }

  @Override
  public ProductGroup findById(Long id) {
    return groupsByName.values().stream().filter(it -> it.getId().equals(id)).findFirst().orElse(null);
  }

}
