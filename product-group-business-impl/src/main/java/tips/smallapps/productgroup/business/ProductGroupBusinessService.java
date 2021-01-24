package tips.smallapps.productgroup.business;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;

public class ProductGroupBusinessService {

  private ProductGroupDataService productGroupDataService;

  public ProductGroupBusinessService(ProductGroupDataService productGroupDataService) {
    super();
    this.productGroupDataService = productGroupDataService;
  }

  public void createNewProductGroup(ProductGroup productGroup) {
    ProductGroup queried = productGroupDataService.findByName(productGroup.getName());
    if (null != queried) {
      throw new ProductGroupAlreadyExistException(productGroup);
    }
    productGroupDataService.save(productGroup);
  }

  public void updateProductGroup(ProductGroup productGroup) {
    ProductGroup queried = productGroupDataService.findById(productGroup.getId());
    if (null == queried) {
      throw new ProductGroupDoesNotExistException(productGroup.getId());
    }
    productGroupDataService.update(productGroup);
  }

  public ProductGroup findByName(String name) {
    ProductGroup queried = productGroupDataService.findByName(name);
    if (null == queried) {
      throw new ProductGroupDoesNotExistException(name);
    }
    return queried;
  }

}
