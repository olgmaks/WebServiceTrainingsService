package com.epam.modelweb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by OLEG on 01.12.2015.
 */

@XmlRootElement(name = "summaryFault")
public class SummaryFault extends Summary {



    private String errorMessage;


    public SummaryFault() {
    }

    public SummaryFault(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
