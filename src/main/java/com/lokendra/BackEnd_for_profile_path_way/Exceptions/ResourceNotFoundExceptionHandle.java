package com.lokendra.BackEnd_for_profile_path_way.Exceptions;

public class ResourceNotFoundExceptionHandle extends Exception{

    String resourceName;
    String fieldName;
    Integer id;

    public ResourceNotFoundExceptionHandle(String resourceName, String fieldName, Integer id) {
        super(String.format("%s not found with %s: %d", resourceName, fieldName, id));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.id = id;
    }
}
