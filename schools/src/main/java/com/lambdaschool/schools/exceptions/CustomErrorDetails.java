package com.lambdaschool.schools.exceptions;

import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorDetails extends DefaultErrorAttributes
{
    // Title  (Subject)
    // Status (HTTP)
    // Details (In actual English)
    // Time Stamp
    // Developer Message
    // Errors (validation)
    // Field Name
    // Message

    @Autowired
    HelperFunctions helperFunctions;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttrs = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> errorDetails = new LinkedHashMap<>();
        errorDetails.put("title", errorAttrs.get("error"));
        errorDetails.put("status", errorAttrs.get("status"));
        errorDetails.put("details", errorAttrs.get("details"));
        errorDetails.put("timestamp", errorAttrs.get("timestamp"));
        errorDetails.put("developerMessage", "path " + errorAttrs.get("path"));
        errorDetails.put("errors", helperFunctions.getConstraintViolations(this.getError(webRequest)));

        return errorDetails;
    }



}
