package com.lambdaschool.schools.models;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDetails
{
    private String title;
    private int status;
    private String details;
    private Date timestamp;
    private String developermessage;
    private List<ValidationErrors> errors = new ArrayList<>();

    public ErrorDetails() {

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getDevelopermessage()
    {
        return developermessage;
    }

    public void setDevelopermessage(String developermessage)
    {
        this.developermessage = developermessage;
    }

    public List<ValidationErrors> getErrors()
    {
        return errors;
    }

    public void setErrors(List<ValidationErrors> errors)
    {
        this.errors = errors;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }
}
