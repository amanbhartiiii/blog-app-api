package com.bharti.blog_app_api.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;
    String fieldValue2;
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue ) {
        super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue2 ) {
        super(String.format("%s not found with %s : %s",resourceName,fieldName,fieldValue2));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue2 = fieldValue2;
    }
}
