package com.epam.modelweb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by OLEG on 01.12.2015.
 */

@XmlRootElement(name = "summarySuccess")
public class SummarySuccess extends Summary {





    private String successMessage;

    public SummarySuccess() {
    }

    public SummarySuccess(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
