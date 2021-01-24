package tips.smallapps.impl.productgroup.data.postgresql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.exceptions.ProductGroupNameIsNotUniqueException;
import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;
import tips.smallapps.impl.productgroup.data.postgresql.ProductGroupDataServiceImpl;

public class ProductGroupDataServiceImplIntegrationTest extends BaseTest {

  @Test
  public void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroup saved = ProductGroup.builder().name("Demo").build();
    service.save(saved);
    ProductGroup queried = service.findByName(saved.getName());
    Assertions.assertEquals(saved.getName(), queried.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroup saved = ProductGroup.builder().name("Demo2").build();
    service.save(saved);
    Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      service.save(saved);
    });
    Assertions.assertEquals("Product group with " + saved.getName() + " already defined", exception.getMessage());
  }

  @Test
  void testWhenSameProductGroupUpdatedThanNewNameShouldBeReturned() {
    ProductGroupDataService service = new ProductGroupDataServiceImpl(context);
    ProductGroup saved = ProductGroup.builder().name("Demo2").build();
    service.save(saved);
    saved = service.findByName(saved.getName());
    ProductGroup toBeUpdated =
        ProductGroup.builder().id(saved.getId()).name("Demo3").version(saved.getVersion()).build();
    service.update(toBeUpdated);
    ProductGroup updated = service.findById(toBeUpdated.getId());
    Assertions.assertEquals(toBeUpdated.getName(), updated.getName());
    Assertions.assertEquals(toBeUpdated.getVersion() + 1, updated.getVersion());
  }

}
