package tips.smallapps.productgroup.business;

public class ProductGroupDoesNotExistException extends RuntimeException {

  private static final long serialVersionUID = -394078545391923795L;

  public ProductGroupDoesNotExistException(Long groupId) {
    super("ProductGroup with id " + groupId + " does not exist.");
  }
  
  public ProductGroupDoesNotExistException(String name) {
    super("ProductGroup with name " + name + " does not exist.");
  }
}
