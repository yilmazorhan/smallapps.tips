package tips.smallapps.api.productgroup.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@ToString(of = {"id", "name"})
public class ProductGroup {

  public Long id;

  public String name;

  public int version;

}
