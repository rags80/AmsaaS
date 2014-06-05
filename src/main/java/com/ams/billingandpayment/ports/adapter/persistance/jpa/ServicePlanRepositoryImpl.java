package com.ams.billingandpayment.ports.adapter.persistance.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePlan;
import com.ams.billingandpayment.domain.model.serviceportfolio.ServicePrice;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServicePlanRepository")
public class ServicePlanRepositoryImpl implements ServicePlanRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager	entityManager;

	@Override
	public String create(ServicePlan entity)
	{
		this.entityManager.persist(entity);
		return entity.getSrvcPlanName();
	}

	@Override
	public String update(ServicePlan entity)
	{
		this.entityManager.merge(entity);
		this.entityManager.flush();
		return entity.getSrvcPlanName();
	}

	@Override
	public String delete(String srvcPlanName)
	{
		ServicePlan servicePlan = this.findById(srvcPlanName);
		this.entityManager.remove(servicePlan);
		return servicePlan.getSrvcPlanName();
	}

	@Override
	public void deleteServicePriceOfPlan(String srvcPlanName, String srvcCode)
	{
		Query query = this.entityManager.createQuery("DELETE FROM ServiceRate sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode");
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		query.executeUpdate();
	}

	@Override
	public List<ServicePlan> findAll()
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("select S from ServicePlan S", ServicePlan.class);
		return query.getResultList();
	}

	@Override
	public ServicePlan findServicePlanOfPerson(long billedPersonId)
	{
		TypedQuery<ServicePlan> query = this.entityManager.createNamedQuery("Select SS.subscribedSrvcsPlan from ServiceSubscription SS where SS.srvcSubcrptnOfPerson.persnId=:id", ServicePlan.class);
		query.setParameter("id", billedPersonId);
		return query.getSingleResult();
	}

	@Override
	public List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("SELECT sp FROM ServicePrice AS sp WHERE sp.srvcPlan.srvcPlanName=:splName", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		return query.getResultList();
	}

	@Override
	public List<ServicePrice> findAllServicePricesByCriteria(Map criteriaMap)
	{

		return null;
	}

	@Override
	public List<ServicePrice> findAllServicePricesByCriteria(String srvcPlanName, String string)
	{
		return null;
	}

	@Override
	public ServicePlan findById(String srvcPlanName)
	{
		/*
		 * TypedQuery<ServicePlan> query = this.entityManager.createQuery(
		 * "SELECT spl FROM ServicePlan AS spl LEFT JOIN FETCH spl.srvcPriceSet WHERE spl.srvcPlanName=:splName"
		 * , ServicePlan.class); query.setParameter("splName", srvcPlanName);
		 * return query.getSingleResult();
		 */

		ServicePlan spl = this.entityManager.find(ServicePlan.class, srvcPlanName);
		return spl;
	}

	@Override
	public Page<ServicePlan> findNextPageData(Page<ServicePlan> page)
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("select SP from ServicePlan SP ", ServicePlan.class).setMaxResults(page.getOffset()).setFirstResult(page.getIndex());
		page.setPageDataList(query.getResultList());
		return page;
	}

	@Override
	public Page<ServicePrice> findNextPageOfServicePrices(String srvcPlanName, int startIndex, int offset)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("select SR from ServiceRate SR where SR.srvcPlan.srvcPlanName=:splName", ServicePrice.class).setMaxResults(offset).setFirstResult(startIndex);
		query.setParameter("splName", srvcPlanName);
		Page<ServicePrice> srvcRatePage = new Page<ServicePrice>(startIndex, offset);
		srvcRatePage.setPageDataList(query.getResultList());
		return srvcRatePage;

	}

	@Override
	public ServicePrice findServicePriceByCriteria(String srvcPlanName, String srvcCode)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("select SPR from PlanServicePrice as SPR where SPR.srvcPlan.srvcPlanName=:splName and SPR.service.srvcCode=:srvcCode", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		return query.getSingleResult();
	}

	@Override
	public String saveOrUpdateServicePriceToPlan(ServicePrice srvcRate)
	{
		this.entityManager.persist(srvcRate);
		return "success";

	}

}
