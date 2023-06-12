package net.myblog.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private String fildValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fildValue) {
        super(String.format("%s not found with %s : '%s'",resourceName,fieldName,fildValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fildValue = fildValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFildValue() {
        return fildValue;
    }

    public String getResourceName() {
        return resourceName;
    }
}
