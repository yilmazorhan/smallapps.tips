package tips.smallapps.web.generated.productgroup.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tips.smallapps.web.generated.productgroup.model.ProductGroup;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FindProductGroupQueryOperationResponseAllOf
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-17T12:36:37.707123+03:00[Europe/Istanbul]")
public class FindProductGroupQueryOperationResponseAllOf   {
  @JsonProperty("body")
  private ProductGroup body = null;

  public FindProductGroupQueryOperationResponseAllOf body(ProductGroup body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
  */
  @ApiModelProperty(value = "")

  @Valid

  public ProductGroup getBody() {
    return body;
  }

  public void setBody(ProductGroup body) {
    this.body = body;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FindProductGroupQueryOperationResponseAllOf findProductGroupQueryOperationResponseAllOf = (FindProductGroupQueryOperationResponseAllOf) o;
    return Objects.equals(this.body, findProductGroupQueryOperationResponseAllOf.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FindProductGroupQueryOperationResponseAllOf {\n");
    
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

