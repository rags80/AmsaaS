package com.ams.billingandpayment.domain.model.servicecatalog;

import com.ams.users.domain.model.Person;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity implementation class for Entity: ServiceProfile
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICESUBSCRIPTION")
public class ServiceSubscription implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long srvcSubcrptnId;
    private String srvcSubcrptnName;
    private Person srvcSubcrptnOfPerson;
    private Date srvcSubcrptnStartDate;
    private Date srvcSubcrptnEndDate;
    private Status srvcSubcrptnStatus;
    private ServicePlan subscribedSrvcsPlan;

    public ServiceSubscription() {
        super();
    }

	/*
     * PERSON SERVICE PROFILE DOMAIN LOGIC
	 */

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Column(name = "SUBSCRPTN_ENDDATE")
    public Date getSrvcSubcrptnEndDate() {
        return this.srvcSubcrptnEndDate;
    }

    public void setSrvcSubcrptnEndDate(Date srvcSubcrptnendDate) {
        this.srvcSubcrptnEndDate = srvcSubcrptnendDate;
    }

    /*
     * ACCESSOR & MUTATORS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SUBSCRPTN_ID")
    public Long getSrvcSubcrptnId() {
        return this.srvcSubcrptnId;
    }

    public void setSrvcSubcrptnId(Long srvcSubcrptnId) {
        this.srvcSubcrptnId = srvcSubcrptnId;
    }

    @Column(name = "SUBSCRPTN_NAME")
    public String getSrvcSubcrptnName() {
        return this.srvcSubcrptnName;
    }

    public void setSrvcSubcrptnName(String srvcSubcrptnName) {
        this.srvcSubcrptnName = srvcSubcrptnName;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    @JsonIgnore
    public Person getSrvcSubcrptnOfPerson() {
        return this.srvcSubcrptnOfPerson;
    }

    public void setSrvcSubcrptnOfPerson(Person srvcSubcrptnOfPerson) {
        this.srvcSubcrptnOfPerson = srvcSubcrptnOfPerson;
    }

    @Column(name = "SUBSCRPTN_STARTDATE")
    public Date getSrvcSubcrptnStartDate() {
        return this.srvcSubcrptnStartDate;
    }

    public void setSrvcSubcrptnStartDate(Date srvcSubcrptnStartDate) {
        this.srvcSubcrptnStartDate = srvcSubcrptnStartDate;
    }

    @Column(name = "SUBSCRPTN_STATUS")
    public Status getSrvcSubcrptnStatus() {
        return this.srvcSubcrptnStatus;
    }

    public void setSrvcSubcrptnStatus(Status srvcSubcrptnStatus) {
        this.srvcSubcrptnStatus = srvcSubcrptnStatus;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SERVICEPLAN_ID")
    public ServicePlan getSubscribedSrvcsPlan() {
        return this.subscribedSrvcsPlan;
    }

    public void setSubscribedSrvcsPlan(ServicePlan srvcPlan) {
        this.subscribedSrvcsPlan = srvcPlan;
    }

    public boolean isSrvcSubcrptnStatus(Status status) {
        if (this.srvcSubcrptnStatus.equals(status)) {
            return true;
        } else {
            return false;
        }

    }

}
