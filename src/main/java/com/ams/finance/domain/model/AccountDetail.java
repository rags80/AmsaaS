package com.ams.finance.domain.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AccountDetail implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String acntDetNotes;

    public String getAcntDetNotes() {
        return acntDetNotes;
    }

    public void setAcntDetNotes(String notes) {
        this.acntDetNotes = notes;
    }

}
