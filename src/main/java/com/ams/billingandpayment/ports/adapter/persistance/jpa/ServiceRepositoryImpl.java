package com.ams.billingandpayment.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.billingandpayment.domain.model.servicecatalog.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServiceRepository")
public class ServiceRepositoryImpl implements ServiceRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	@Override
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

	@Override
	public void delete(String svcCode)
	{
		Service svc = this.entityManager.find(Service.class, svcCode);
		this.entityManager.remove(svc);

	}

	@Override
	public List<Service> findAll()
	{
		TypedQuery<Service> query = this.entityManager.createQuery("select S from Service S", Service.class);
		return query.getResultList();
	}

	@Override
	public Service findById(String svcCode)
	{
		return this.entityManager.find(Service.class, svcCode);
	}

	@Override
	public Page<Service> findNextPageData(Page<Service> page)
	{
		TypedQuery<Service> query = this.entityManager.createQuery("select S from Service S order by S.srvcDescription DESC", Service.class).setMaxResults(page.getNextIndex()).setFirstResult(page.getCurrentIndex());
		Page<Service> srvcPage = new Page<Service>(page.getNoOfRecordsPerFetch(), page.nextIndexIs());
		srvcPage.setPageDataList(query.getResultList());
		return srvcPage;
	}

}
