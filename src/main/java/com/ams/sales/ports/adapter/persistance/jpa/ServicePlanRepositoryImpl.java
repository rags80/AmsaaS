package com.ams.sales.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.sales.domain.model.servicecatalog.ServicePlan;
import com.ams.sales.domain.model.servicecatalog.ServiceRate;
import com.ams.sales.domain.model.servicecatalog.ServiceRateId;
import com.ams.sales.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServicePlanRepository")
public class ServicePlanRepositoryImpl implements ServicePlanRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public String createOrUpdate(ServicePlan servicePlan)
	{
		if (servicePlan.getSrvcPlanName() == null)
		{
			entityManager.persist(servicePlan);
		}
		else
		{
			entityManager.merge(servicePlan);

		}

		entityManager.flush();
		return servicePlan.getSrvcPlanName();

	}

	public void delete(String srvcPlanName)
	{
		ServicePlan servicePlan = this.findById(srvcPlanName);
		entityManager.remove(servicePlan);
	}

	public ServicePlan findById(String srvcPlanName)
	{
		TypedQuery<ServicePlan> query = entityManager.createQuery("SELECT spl FROM ServicePlan AS spl LEFT JOIN FETCH spl.serviceRateSet WHERE spl.srvcPlanName=:splName", ServicePlan.class);
		query.setParameter("splName", srvcPlanName);
		return query.getSingleResult();
	}

	public List<ServicePlan> findAll()
	{
		TypedQuery<ServicePlan> query = entityManager.createQuery("select S from ServicePlan S", ServicePlan.class);
		return query.getResultList();
	}

	public String saveOrUpdateServiceRate(ServiceRate srvcRate)
	{
		if (entityManager.merge(srvcRate) != null)
		{
			entityManager.flush();
			return "success";
		}
		else
		{
			return "failure";
		}

	}

	public void deleteServiceRate(ServiceRateId srvcRateId)
	{
		if (srvcRateId != null)
		{
			ServiceRate srvcRatePlan = entityManager.find(ServiceRate.class, srvcRateId);
			entityManager.remove(srvcRatePlan);
		}

	}

	public void deleteServiceRateByCriteria(String srvcPlanName, String srvcCode)
	{
		Query query = entityManager.createQuery("DELETE FROM ServiceRate sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode");
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		query.executeUpdate();
	}

	public List<ServiceRate> findAllServiceRateByCriteria(String srvcPlanName, String srvcCode)
	{
		TypedQuery<ServiceRate> query = entityManager.createQuery("SELECT sr FROM ServiceRate AS sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode", ServiceRate.class);
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		return query.getResultList();
	}

	public List<ServiceRate> findAllServiceRateByPlanName(String srvcPlanName)
	{
		TypedQuery<ServiceRate> query = entityManager.createQuery("SELECT sr FROM ServiceRate AS sr WHERE sr.srvcPlan.srvcPlanName=:splName", ServiceRate.class);
		query.setParameter("splName", srvcPlanName);
		return query.getResultList();
	}

	public Page<ServicePlan> findNextPageData(Page<ServicePlan> page)
	{
		TypedQuery<ServicePlan> query = entityManager.createQuery("select SPL from ServicePlan SPL ", ServicePlan.class).setMaxResults(page.getNoOfRecordsPerFetch()).setFirstResult(page.nextIndexIs());
		Page<ServicePlan> srvcPlanPage = new Page<ServicePlan>(page.getNoOfRecordsPerFetch(), page.nextIndexIs());
		srvcPlanPage.setPageDataList(query.getResultList());
		return srvcPlanPage;
	}

	public Page<ServiceRate> findServiceRateNextPageData(String srvcPlanName, int startIndex, int offset)
	{
		TypedQuery<ServiceRate> query = entityManager.createQuery("select SR from ServiceRate SR where SR.srvcPlan.srvcPlanName=:splName order by SR.srvcRateId", ServiceRate.class).setMaxResults(offset).setFirstResult(startIndex);
		query.setParameter("splName", srvcPlanName);
		Page<ServiceRate> srvcRatePage = new Page<ServiceRate>(startIndex, offset);
		srvcRatePage.setPageDataList(query.getResultList());
		return srvcRatePage;

	}

	public void deleteServiceRateByCriteria(String srvcPlanName, String srvcCode, String chargeType)
	{
		Query query = entityManager.createQuery("DELETE FROM ServiceRate sr WHERE sr.srvcPlan.srvcPlanName=:splName AND sr.service.srvcCode=:srvcCode AND sr.srvcRateId.svcChargeType=:chargeType");
		query.setParameter("splName", srvcPlanName);
		query.setParameter("srvcCode", srvcCode);
		// query.setParameter("chargeType",
		// ServiceRate.ServiceChargeType.valueOf(chargeType));
		query.executeUpdate();
	}
}
