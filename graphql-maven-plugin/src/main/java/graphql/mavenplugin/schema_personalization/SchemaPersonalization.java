/**
 * 
 */
package graphql.mavenplugin.schema_personalization;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * This class contains the data in java form that the user has put in his/her schema configuration file.<BR/>
 * It is not public, as only {@link JsonSchemaPersonalization} may use it.
 * 
 * @See {@link JsonSchemaPersonalization}
 * @author EtienneSF
 */
@Data
class SchemaPersonalization {

	List<EntityPersonalization> entityPersonalizations = new ArrayList<>();

}
