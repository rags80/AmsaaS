package com.ams.billing.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.billing.domain.model.servicecatalog.ServicePlan;
import com.ams.billing.domain.model.servicecatalog.ServicePrice;
import com.ams.billing.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServicePlanRepository")
public class ServicePlanRepositoryImpl implements ServicePlanRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public String createOrUpdate(ServicePlan servicePlan)
	{
		this.entityManager.merge(servicePlan);
		this.entityManager.flush();
		return servicePlan.getSrvcPlanName();

	}

	public void delete(String srvcPlanName)
	{
		ServicePlan servicePlan = this.findById(srvcPlanName);
		this.entityManager.remove(servicePlan);
	}

	public ServicePlan findById(String srvcPlanName)
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("SELECT spl FROM ServicePlan AS spl LEFT JOIN FETCH spl.serviceRateSet WHERE spl.srvcPlanName=:splName", ServicePlan.class);
		query.setParameter("splName", srvcPlanName);
		return query.getSingleResult();
	}

	public List<ServicePlan> findAll()
	{
		TypedQuery<ServicePlan> query = this.entityManager.createQuery("select S from ServicePlan S", ServicePlan.class);
		return query.getResultList();
	}

	public Page<ServicePlan> findNextPageData(Page<ServicePlan> page)
	{
		return null;
	}

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

	public void deleteServicePriceOfPlan(String srvcPlanName, String srvcCode)
	{
		Query query = this.entityManager.createQuery("DELETE FROM ServiceRate sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode");
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		query.executeUpdate();
	}

	public List<ServicePrice> findAllServicePricesByCriteria(String srvcPlanName, String srvcCode)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("SELECT sr FROM PlanServicePrice AS sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		return query.getResultList();
	}

	public List<ServicePrice> findAllServicePriceByPlanName(String srvcPlanName)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("SELECT psp FROM PlanServicePrice AS sr WHERE sr.srvcPlan.srvcPlanName=:splName", ServicePrice.class);
		query.setParameter("splName", srvcPlanName);
		return query.getResultList();
	}

	public Page<ServicePrice> findNextPageOfServicePrices(String srvcPlanName, int startIndex, int offset)
	{
		TypedQuery<ServicePrice> query = this.entityManager.createQuery("select SR from ServiceRate SR where SR.srvcPlan.srvcPlanName=:splName order by SR.srvcRateId", ServicePrice.class).setMaxResults(offset).setFirstResult(startIndex);
		query.setParameter("splName", srvcPlanName);
		Page<ServicePrice> srvcRatePage = new Page<ServicePrice>(startIndex, offset);
		srvcRatePage.setPageDataList(query.getResultList());
		return srvcRatePage;

	}

}
