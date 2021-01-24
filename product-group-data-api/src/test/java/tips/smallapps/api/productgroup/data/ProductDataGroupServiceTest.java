package tips.smallapps.api.productgroup.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.exceptions.ProductGroupNameIsNotUniqueException;

public class ProductDataGroupServiceTest {

  @Test
  void testWhenProductGroupSavedThanItShouldExistInDataStore() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = ProductGroup.builder().id(1L).name("demo").build();
    service.save(entity);
    ProductGroup entityLookup = service.findByName(entity.getName());
    Assertions.assertEquals(entity.getName(), entityLookup.getName());
  }

  @Test
  void testWhenSameProductGroupNameGivenThanProductGroupNameIsNotUniqueExceptionShouldBeThrown() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = ProductGroup.builder().id(2L).name("demo").build();
    service.save(entity);
    Exception exception = Assertions.assertThrows(ProductGroupNameIsNotUniqueException.class, () -> {
      service.save(entity);
    });
    Assertions.assertEquals("Product group with " + entity.getName() + " already defined", exception.getMessage());
  }

  @Test
  void testWhenUpdateProductGroupThanItShouldUpdateInDataStore() {
    FakeProductGroupDataService service = new FakeProductGroupDataService();
    ProductGroup entity = ProductGroup.builder().id(2L).name("Demo 2").build();
    service.save(entity);
    entity = ProductGroup.builder().id(2L).name("Demo 3").build();
    service.update(entity);
    ProductGroup entityLookup = service.findByName(entity.getName());
    Assertions.assertEquals(entity.getName(), entityLookup.getName());
  }

}
