package tips.smallapps.impl.productgroup.data.postgresql;

import static tips.smallapps.impl.productgroup.data.postgresql.generated.tables.ProductGroupTable.PRODUCT_GROUP_TABLE;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.RecordMapper;
import org.jooq.exception.DataAccessException;

import tips.smallapps.api.productgroup.data.ProductGroup;
import tips.smallapps.api.productgroup.data.exceptions.ProductGroupNameIsNotUniqueException;
import tips.smallapps.api.productgroup.data.service.ProductGroupDataService;

public class ProductGroupDataServiceImpl implements ProductGroupDataService {

  private DSLContext dslContext;

  public ProductGroupDataServiceImpl(DSLContext dslContext) {
    super();
    this.dslContext = dslContext;
  }

  @Override
  public void save(ProductGroup entity) {
    try {
      dslContext.insertInto(PRODUCT_GROUP_TABLE, PRODUCT_GROUP_TABLE.GROUP_NAME, PRODUCT_GROUP_TABLE.VERSION)
          .values(entity.getName(), 0).execute();
    } catch (DataAccessException dae) {
      if (ExceptionUtils.isSqlIntegrityConstraintViolationException(dae)) {
        throw new ProductGroupNameIsNotUniqueException(entity.getName(), dae);
      } else {
        throw dae;
      }
    }
  }

  @Override
  public ProductGroup findByName(String name) {
    List<ProductGroup> items =
        dslContext.select(PRODUCT_GROUP_TABLE.PGR_ID, PRODUCT_GROUP_TABLE.GROUP_NAME, PRODUCT_GROUP_TABLE.VERSION)
            .from(PRODUCT_GROUP_TABLE).where(PRODUCT_GROUP_TABLE.GROUP_NAME.eq(name))
            .fetch(new RecordMapper<Record3<Long, String, Integer>, ProductGroup>() {
              @Override
              public ProductGroup map(Record3<Long, String, Integer> rec) {
                return new ProductGroup(rec.component1(), rec.component2(), rec.component3());
              }
            });
    if (items.isEmpty()) {
      return null;
    }
    return items.get(0);
  }

  @Override
  public void update(ProductGroup entity) {
    dslContext.update(PRODUCT_GROUP_TABLE).set(PRODUCT_GROUP_TABLE.GROUP_NAME, entity.getName())
        .set(PRODUCT_GROUP_TABLE.VERSION, PRODUCT_GROUP_TABLE.VERSION.plus(1))
        .where(PRODUCT_GROUP_TABLE.PGR_ID.eq(entity.getId())).and(PRODUCT_GROUP_TABLE.VERSION.eq(entity.getVersion()))
        .execute();
  }

  @Override
  public ProductGroup findById(Long id) {
    List<ProductGroup> items =
        dslContext.select(PRODUCT_GROUP_TABLE.PGR_ID, PRODUCT_GROUP_TABLE.GROUP_NAME, PRODUCT_GROUP_TABLE.VERSION)
            .from(PRODUCT_GROUP_TABLE).where(PRODUCT_GROUP_TABLE.PGR_ID.eq(id))
            .fetch(new RecordMapper<Record3<Long, String, Integer>, ProductGroup>() {
              @Override
              public ProductGroup map(Record3<Long, String, Integer> rec) {
                return new ProductGroup(rec.component1(), rec.component2(), rec.component3());
              }
            });
    if (items.isEmpty()) {
      return null;
    }
    return items.get(0);
  }

}
