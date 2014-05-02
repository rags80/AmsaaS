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

import com.ams.billingandpayment.domain.model.servicecatalog.ServicePlan;
import com.ams.billingandpayment.domain.model.servicecatalog.ServicePrice;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServicePlanRepository")
public class ServicePlanRepositoryImpl implements ServicePlanRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	@Override
	public String createOrUpdate(ServicePlan servicePlan)
	{
		this.entityManager.merge(servicePlan);
		this.entityManager.flush();
		return servicePlan.getSrvcPlanName();

	}

	@Override
	public void delete(String srvcPlanName)
	{
		ServicePlan servicePlan = this.findById(srvcPlanName);
		this.entityManager.remove(servicePlan);
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
	public List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("SELECT psp FROM PlanServicePrice AS sr WHERE sr.srvcPlan.srvcPlanName=:splName", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.billingandpayment.domain.repository.ServicePlanRepository#
	 * findAllServicePricesByCriteria(java.util.Map)
	 */
	@Override
	public List<ServicePrice> findAllServicePricesByCriteria(Map criteriaMap)
	{

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ams.billingandpayment.domain.repository.ServicePlanRepository#
	 * findAllServicePricesByCriteria(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ServicePrice> findAllServicePricesByCriteria(String srvcPlanName, String string)
	{
		return null;
	}

	@Override
	public ServicePlan findById(String srvcPlanName)
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("SELECT spl FROM ServicePlan AS spl LEFT JOIN FETCH spl.srvcPriceSet WHERE spl.srvcPlanName=:splName", ServicePlan.class);
		query.setParameter("splName", srvcPlanName);
		return query.getSingleResult();
	}

	@Override
	public Page<ServicePlan> findNextPageData(Page<ServicePlan> page)
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("select SP from ServicePlan SP ", ServicePlan.class).setMaxResults(page.getNextIndex()).setFirstResult(page.getCurrentIndex());
		Page<ServicePlan> srvcPlanPage = new Page<ServicePlan>(page.getNoOfRecordsPerFetch(), page.nextIndexIs());
		srvcPlanPage.setPageDataList(query.getResultList());
		return srvcPlanPage;
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
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("SELECT sr FROM PlanServicePrice AS sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		return query.getSingleResult();
	}

	@Override
	public String saveOrUpdateServicePriceToPlan(ServicePrice srvcRate)
	{
		if (this.entityManager.merge(srvcRate) != null)
		{
			this.entityManager.flush();
			return "success";
		}
		else
		{
			return "failure";
		}

	}

}
