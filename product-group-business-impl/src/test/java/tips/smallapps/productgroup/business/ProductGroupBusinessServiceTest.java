package tips.smallapps.productgroup.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.productgroup.business.ProductGroupAlreadyExistException;
import tips.smallapps.productgroup.business.ProductGroupBusinessService;
import tips.smallapps.productgroup.business.ProductGroupDoesNotExistException;

class ProductGroupBusinessServiceTest {

  @Test
  void testWhenDuplicateProductGroupNameProvidedThanProductGroupAlreadyExistExceptionShouldReturn() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    ProductGroup sample = ProductGroup.builder().id(1L).name("Demo").build();
    dataService.addToStore(sample);

    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    Exception exception = Assertions.assertThrows(ProductGroupAlreadyExistException.class, () -> {
      pgbs.createNewProductGroup(sample);
    });
    Assertions.assertTrue(exception.getMessage().contains(sample.getName()));
  }

  @Test
  void testWhenNewProductGroupNameProvidedThanProductGroupDataServiceShouldBeCalled() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    dataService.clear();
    ProductGroup sample = ProductGroup.builder().id(1L).name("Demo").build();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    pgbs.createNewProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
  }

  @Test
  void testWhenProductGroupUpdatedThanNewNameShouldExistInDatastore() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    dataService.clear();
    ProductGroup sample = ProductGroup.builder().id(1L).name("Demo").build();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    pgbs.createNewProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
    sample = ProductGroup.builder().id(1L).name("Demo 2").build();
    pgbs.updateProductGroup(sample);
    Assertions.assertNotNull(dataService.findByName(sample.getName()));
  }

  @Test
  void testWhenInvalidProductGroupUpdatedProvidedThanProductGroupDoesNotExistExceptionShouldReturn() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    ProductGroup sample = ProductGroup.builder().id(1L).name("Demo").build();
    dataService.clear();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    Exception exception = Assertions.assertThrows(ProductGroupDoesNotExistException.class, () -> {
      pgbs.updateProductGroup(sample);
    });
    Assertions.assertTrue(exception.getMessage().contains(sample.getId().toString()));
  }

  @Test
  void testWhenInvalidProductGroupNameProvidedThanProductGroupDoesNotExistExceptionShouldReturn() {
    FakeProductGroupDataService dataService = new FakeProductGroupDataService();
    ProductGroup sample = ProductGroup.builder().id(1L).name("Demo").build();
    dataService.clear();
    ProductGroupBusinessService pgbs = new ProductGroupBusinessService(dataService);
    Exception exception = Assertions.assertThrows(ProductGroupDoesNotExistException.class, () -> {
      pgbs.findByName(sample.getName());
    });
    Assertions.assertTrue(exception.getMessage().contains(sample.getName().toString()));
  }

}
