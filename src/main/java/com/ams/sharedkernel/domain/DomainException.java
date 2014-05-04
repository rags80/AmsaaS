package com.ams.sharedkernel.domain;

public class DomainException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2463863730457225308L;

    public DomainException() {
    }

    public DomainException(String msg) {
        super(msg);
    }

}
