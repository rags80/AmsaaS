package com.ams.billing.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.billing.domain.model.servicecatalog.Service;
import com.ams.billing.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServiceRepository")
public class ServiceRepositoryImpl implements ServiceRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public String createOrUpdate(Service srvc)
	{
		if (srvc.getSrvcCode() == null)
		{
			this.entityManager.persist(srvc);
		}
		else
		{
			this.entityManager.merge(srvc);
		}

		System.out.println(srvc.toString());
		return srvc.getSrvcCode();

	}

	public void delete(String svcCode)
	{
		Service svc = this.entityManager.find(Service.class, svcCode);
		this.entityManager.remove(svc);

	}

	public Service findById(String svcCode)
	{
		return this.entityManager.find(Service.class, svcCode);
	}

	public List<Service> findAll()
	{
		TypedQuery<Service> query = this.entityManager.createQuery("select S from Service S", Service.class);
		return query.getResultList();
	}

	public Page<Service> findNextPageData(Page<Service> page)
	{
		TypedQuery<Service> query = this.entityManager.createQuery("select S from Service S ", Service.class).setMaxResults(page.getNoOfRecordsPerFetch()).setFirstResult(page.nextIndexIs());
		Page<Service> srvcPage = new Page<Service>(page.getNoOfRecordsPerFetch(), page.nextIndexIs());
		srvcPage.setPageDataList(query.getResultList());
		return srvcPage;
	}

}
