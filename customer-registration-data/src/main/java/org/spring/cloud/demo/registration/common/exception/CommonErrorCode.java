package org.spring.cloud.demo.registration.common.exception;

import lombok.Getter;

/**
 * Common error codes
 * Format: [ERR-11xx]
 */
@Getter
public enum CommonErrorCode implements ErrorCode {

    /** Generic Error **/
    DATABASE_NOT_REACHABLE_ERROR("ERR-1100", "Failed to connect to database [{0}]"),
    APPLICATION_ERROR("ERR-1101", "Application Error"),
    APPLICATION_FATAL_ERROR("ERR-1102", "Fatal Application Error [{0}]"),
    APPLICATION_FATAL_ERROR_MSG("ERR-1103", "Fatal Application Error"),
    DATA_ACCESS_ERROR("ERR-1104", "Database Error"),
    UNSUPPORTED_OPERATION("ERR-1105", "Operation unsupported"),
    DATA_NOT_FOUND("ERR-1106", "Data not found [{0}]"),
    FILE_NOT_FOUND("ERR-1107", "File '{0}' not found"),
    DIR_CREATION_ERROR("ERR-1108", "Dir '{0}' creation error"),
    SERVICE_NOT_AVAILABLE("ERR-1109", "Event bus service not available [{0}]"),
    APP_SERVICE_NOT_FOUND("ERR-1110", "Application Service not found for entity [{0}]"),
    EVENT_BUS("ERR-1111", "Error sending to event bus with topic [{0}]: [{1}]"),
    NO_EVENT_BUS_LISTENER("ERR-1112", "No event listener found for [{0}]"),
    EVENT_DISPATCH_FAILED("ERR-1113", "Event dispatch failed"),
    CRITERIA_CANNOT_EMPTY("ERR-1114", "Criteria cannot be empty"),
    UNAUTHORIZED("ERR-1115", "Authorization Error"),

    /** Generic Error   **/
    UNABLE_TO_CONNECT_DB("ERR-1116", "Unable to connect database"),
    UNABLE_TO_READ_FILE("ERR-1117", "Unable to read file {0}"),
    WORKFLOW_REQUEST_DISPATCH_ERROR("ERR-1118", "Unable to submit data {0} to Workflow service"),

    /** Unique **/
    UNIQUE_ATTRIBUTES_NOT_FOUND("ERR-1119", "Unique attributes not defined in [{0}]"),
    UNIQUE_VALUE_NULL("ERR-1120", "Unique value cannot be null for [{0}]"),
    JSON_PARSING_ENTITY_TYPE_NOT_FOUND("ERR-1121", "[{0}] entity type not found for json serialization"),
    CRITERIA_NOT_SUPPORTED("ERR-1122", "Criteria not supported for [{0}]"),
    INDEX_TYPE_NOT_SUPPORTED("ERR-1123", "Index [{0}] not supported for [{1}]"),
    NON_AUTHORIZED_BOOKING_CENTER("ERR-1124", "Not authorized to view data with Booking Center [{0}]"),
    COMPOSITE_UNIQUE_ATTRIBUTES_NOT_SUPPORTED("ERR-1125", "Composite unique attributes not supported for [{0}]"),

    /** Input Error **/
    NOT_NUMBER("ERR-1131", "Invalid data. {0} should be number"),
    DATA_CANNOT_EMPTY("ERR-1132", "{0} cannot be empty"),
    INVALID_INPUT("ERR-1133", "Invalid input {0}"),
    INVALID_DATA("ERR-1134", "Invalid data {0}"),
    INVALID_FORMAT("ERR-1135", "Invalid format {0}"),
    INVALID_FILE("ERR-1136", "Invalid file {0}"),
    INVALID_HTTP_REQUEST("ERR-1137", "Error process HTTP request"),
    INPUT_IS_EMPTY("ERR-1138", "{0} input is empty"),
    JSON_PROCESSING_ERROR("ERR-1139", "Convert to json failure, {0}"),
    JSON_PARSING_ERROR("ERR-1140", "Unable to parse the json {0} {1}"),
    JSON_FILE_NOT_FOUND("ERR-1141", "Json file [{0}] not found"),
    JSON_FORMAT_ERROR("ERR-1142", "Invalid Format: field [{0}], value [{1}] @{2}, expected type [{3}]"),
    EXPORT_FAILURE("ERR-1143", "Export failed"),
    IMPORT_FAILURE("ERR-1144", "Import failed"),
    ARRAY_INPUTS_NOT_SUPPORTED("ERR-1145", "Array inputs not supported"),
    DATE_PARSING_ERROR("ERR-1146", "Invalid date format: {0}"),
    DATE_FORMAT_NOT_SUPPORTED("ERR-1147", "Date format [{0}] not supported"),
    TYPE_CONVERTION_ERROR("ERR-1148", "Convertion error [{0}] from [{1}] to [{2}]"),
    UNEXPECTED_CHAR("ERR-1149","Unexpected Character : [{0}]"),
    CLASS_CONVERSION("ERR-1150", "Unable to convert {0} to {1}"),
    UNKNOWN_FUNCTION("ERR-1151", "Unknown Function : [{0}]"),

    /** Data Access Error **/
    REF_DATA_PROP_NOT_FOUND("ERR-1152", "Reference data not found for property [{0}] of [{1}]"),
    DUPLICATE_ENTRY_ERROR("ERR-1153", "Duplicate entry insertion - [{0}] in [{1}]"),
    DATA_DEPENDENCY_VIOLATION("ERR-1154", "Cannot delete current record because it is being referenced"),
    COLUMN_VALUE_NULL("ERR-1155", "Cannot save null value for [{0}] of [{1}]"),
    REF_DATA_NOT_FOUND("ERR-1156", "Ref data not found: {0}"),
    UNIQUE_KEY_NULL("ERR-1157", "UniqueKey value cannot be null for field [{0}] of [{1}]"),
    UNIQUE_KEY_INACCESSIBLE("ERR-1158", "UniqueKey property is not accessible : [{0}]"),
    MAPSTORE_NOT_SUPPORT_COMPOSITE_UNIQUE("ERR-1159", "Simple Mapstore is not supported for composite unique keys"),
    DATA_SIZE_EXCEEDED("ERR-1160", "Result size [{0}] exceeds maximum limit [{1}]. Include more filters"),

    /** Component Error **/
    MODULE_ENTITY_NOT_FOUND("ERR-1161", "Module Entity [{0}] not found"),
    FIELD_NOT_FOUND("ERR-1162", "Field [{0}] not found"),
    MAPPER_NOT_FOUND("ERR-1163", "Mapper not found for type [{0}]"),
    MAPSTORE_CONFIG_EMPTY("ERR-1164", "MapStore config cannot be empty"),
    MAPSTORE_NAME_EMPTY("ERR-1165", "MapStore name cannot be empty for [{0}]"),
    MAPSTORE_NOT_FOUND("ERR-1166", "MapStore not found for entity [{0}]"),
    MAPSTORE_ANNOTATION_NOT_FOUND("ERR-1167", "MapStore annotation not found for class [{0}]"),
    MAPSTORE_FIELD_NOT_INDEXED("ERR-1168", "MapStore field not indexed"),
    MAPSTORE_OPERATION_NOT_SUPPORTED("ERR-1169", "MapStore operation not supported"),
    MAPSTORE_LOAD_FAILED("ERR-1170", "MapStore [{0}] load failed"),
    MAPSTORE_NAME_ALREADY_EXISTS("ERR-1171", "MapStore name [{0}] already exists"),
    BEAN_ID_FIELD_NOT_FOUND("ERR-1172", "Id field not defined in [{0}]"),
    BEAN_NAME_FIELD_NOT_FOUND("ERR-1173", "Name field not defined in [{0}]"),
    BEAN_UNIQUE_FIELD_NOT_FOUND("ERR-1174", "Unique fields not defined in [{0}]"),
    COMPONENT_METADATA_NOT_FOUND("ERR-1175", "Component UI metadata is not found"),
    COMPONENT_REINITIALIZE_ERROR("ERR-1176", "{0} Component cannot be initialized more than once"),
    SIMPLE_BEAN_NOT_SUPPORTED("ERR-1177", "Simple bean not supported for {0}"),
    MAPSTORE_INVALID_BEANCLASS("ERR-1178", "MapStore BeanClass should extend from AbstractBean"),
    DUPLICATE_MODULE_INITIALIZER("ERR-1179", "Order of ModuleEntity Initializer should be unique {0}"),

    /** Tree **/
    TREE_SELF_PARENT_ERROR("ERR-1181", "{0} cannot be its own parent"),
    TREE_CYCLIC_NODE_ERROR("ERR-1182", "Cyclic node found for {0}"),
    TREE_PARENT_NOT_FOUND("ERR-1183", "Parent {0} not found for {1}"),
    TREE_IS_NULL("ERR-1184", "Hierarchy has not been initialized yet. Please refresh"),
    TREE_NODE_NOT_FOUND("ERR-1185", "Node not found for key [{0}]"),

    /** State Code **/
    DEFAULT_STATE_CODE_NOT_DEFINED("ERR-1186", "Default state code not defined for entity {0}"),
    STATE_TRANSITION_NOT_DEFINED("ERR-1187", "State Transition not defined for [{0} : {1} : {2}]"),
    STATE_AUTO_TRANSITION_CYCLIC("ERR-1188", "State auto transition not more than 5"),
    DATA_CATEGORY_NOT_FOUND("ERR-1189","[{0}] - Category not found in ModuleEntityHierarchy"),


    /** Drools **/
    NO_RULES_DIR("ERR-1191", "Directory [{0}] for Drools files not found"),
    NO_RULE_FILES("ERR-1192", "Drools files not found"),
    NO_KMODULE("ERR-1193", "kmodule.xml not found"),

    /** Import/Export**/

    IMPORT_EXPORT_LOCK_NOT_ACQUIRED("ERR-1194","Import/Export Lock not acquired for [Service {0}]"),
    ERROR_DECRYPTING_CREDENTIALS("ERR-1195", "Error decrypting credentials [{0}]"),
    ERROR_ENCRYPTING_CREDENTIALS("ERR-1196", "Error encrypting credentials")
    ;

    private final String code;
    private final String message;

    CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
