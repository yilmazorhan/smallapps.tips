package tips.smallapps.web.generated.productgroup.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tips.smallapps.web.generated.productgroup.model.FindProductGroupQueryOperationResponseAllOf;
import tips.smallapps.web.generated.productgroup.model.OperationResponse;
import tips.smallapps.web.generated.productgroup.model.ProductGroup;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FindProductGroupQueryOperationResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-01-17T12:36:37.707123+03:00[Europe/Istanbul]")
public class FindProductGroupQueryOperationResponse   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("message")
  private String message;

  @JsonProperty("body")
  private ProductGroup body = null;

  public FindProductGroupQueryOperationResponse code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @ApiModelProperty(value = "")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public FindProductGroupQueryOperationResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  */
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public FindProductGroupQueryOperationResponse body(ProductGroup body) {
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
    FindProductGroupQueryOperationResponse findProductGroupQueryOperationResponse = (FindProductGroupQueryOperationResponse) o;
    return Objects.equals(this.code, findProductGroupQueryOperationResponse.code) &&
        Objects.equals(this.message, findProductGroupQueryOperationResponse.message) &&
        Objects.equals(this.body, findProductGroupQueryOperationResponse.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FindProductGroupQueryOperationResponse {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

