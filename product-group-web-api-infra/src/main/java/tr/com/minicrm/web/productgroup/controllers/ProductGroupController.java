package tr.com.minicrm.web.productgroup.controllers;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.productgroup.business.ProductGroupBusinessService;
import tips.smallapps.web.generated.productgroup.api.ProductGroupApi;
import tips.smallapps.web.generated.productgroup.model.FindProductGroupQuery;
import tips.smallapps.web.generated.productgroup.model.FindProductGroupQueryOperationResponse;
import tips.smallapps.web.generated.productgroup.model.NewProductGroup;
import tips.smallapps.web.generated.productgroup.model.OperationResponse;

@RestController
@RequiredArgsConstructor
public class ProductGroupController implements ProductGroupApi {

  private final ProductGroupBusinessService service;

  @Override
  public ResponseEntity<OperationResponse> createNewProductGroup(@Valid NewProductGroup newProductGroup) {
    service.createNewProductGroup(new ProductGroup() {

      @Override
      public int getVersion() {
        return 0;
      }

      @Override
      public String getName() {
        return newProductGroup.getName();
      }

      @Override
      public Long getId() {
        return null;
      }
    });
    return new ResponseEntity<OperationResponse>(new OperationResponse().code("0").message("OK"), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<FindProductGroupQueryOperationResponse> findProductGroupByName(
      @Valid FindProductGroupQuery findProductGroupQuery) {
    ProductGroup findByName = service.findByName(findProductGroupQuery.getName());
    FindProductGroupQueryOperationResponse response = new FindProductGroupQueryOperationResponse()
        .body(new tips.smallapps.web.generated.productgroup.model.ProductGroup().id(findByName.getId())
            .name(findByName.getName()).version(findByName.getVersion())).code("0").message("OK");
    return new ResponseEntity<FindProductGroupQueryOperationResponse>(response, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<OperationResponse> updateProductGroup(
      tips.smallapps.web.generated.productgroup.model.@Valid ProductGroup productGroup) {
    service.updateProductGroup(new ProductGroup() {
      @Override
      public int getVersion() {
        return productGroup.getVersion();
      }

      @Override
      public String getName() {
        return productGroup.getName();
      }

      @Override
      public Long getId() {
        return productGroup.getId();
      }
    });
    return new ResponseEntity<OperationResponse>(new OperationResponse().code("0").message("OK"), HttpStatus.OK);
  }

}
