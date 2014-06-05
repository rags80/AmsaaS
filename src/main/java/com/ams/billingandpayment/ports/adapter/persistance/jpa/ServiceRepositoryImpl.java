package com.ams.billingandpayment.ports.adapter.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.billingandpayment.domain.model.serviceportfolio.Service;
import com.ams.billingandpayment.domain.repository.ServiceRepository;
import com.ams.sharedkernel.domain.repository.Page;

@Repository("ServiceRepository")
public class ServiceRepositoryImpl implements ServiceRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager	entityManager;

	@Override
	public String create(Service entity)
	{
		this.entityManager.persist(entity);
		return entity.getSrvcCode();

	}

	@Override
	public String update(Service entity)
	{
		this.entityManager.merge(entity);
		this.entityManager.flush();
		return entity.getSrvcCode();
	}

	@Override
	public String delete(String svcCode)
	{
		Service svc = this.entityManager.find(Service.class, svcCode);
		this.entityManager.remove(svc);
		return svc.getSrvcCode();

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
		TypedQuery<Service> query = this.entityManager.createQuery("select S from Service S order by S.srvcCode ASC", Service.class).setMaxResults(page.getOffset()).setFirstResult(page.getIndex());
		Page<Service> srvcPage = new Page<Service>(page.getIndex(), page.getOffset());
		srvcPage.setPageDataList(query.getResultList());
		return srvcPage;
	}

}
