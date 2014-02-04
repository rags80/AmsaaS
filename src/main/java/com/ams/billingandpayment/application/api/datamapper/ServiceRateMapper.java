package com.ams.billingandpayment.application.api.datamapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ams.billingandpayment.application.api.servicedata.ServiceRateDto;
import com.ams.billingandpayment.domain.model.service.ServiceChargeType;
import com.ams.billingandpayment.domain.model.service.ServicePlan;
import com.ams.billingandpayment.domain.model.service.ServiceRate;
import com.ams.billingandpayment.domain.model.service.ServiceRate.ServiceRateCategory;
import com.ams.billingandpayment.domain.repository.ServicePlanRepository;
import com.ams.sharedkernel.domain.model.measureandunits.Currency;
import com.ams.sharedkernel.domain.model.measureandunits.Frequency;
import com.ams.sharedkernel.domain.model.measureandunits.Money;
import com.ams.sharedkernel.domain.model.measureandunits.Rate;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;

@Component
public class ServiceRateMapper
{

	@Autowired
	private ServicePlanRepository	servicePlanRepository;

	public ServiceRate getServiceRate(ServiceRateDto srvcRateDTO)
	{
		ServicePlan spl = servicePlanRepository.findById(srvcRateDTO.getSrvcPlanName());
		Rate rate = new Rate(new Money(new BigDecimal(srvcRateDTO.getChargeAmount()), Currency.valueOf(srvcRateDTO.getChargeCurrency())), Unit.valueOf(srvcRateDTO.getChargeUnit()));

		ServiceRate srvcRate = new ServiceRate(spl,
										ServiceRateCategory.valueOf(srvcRateDTO.getSrvcRateCategory()),
										srvcRateDTO.getService(),
										srvcRateDTO.getChargeName(),
										ServiceChargeType.valueOf(srvcRateDTO.getChargeType()),
										rate,
										Frequency.valueOf(srvcRateDTO.getChargeFrequency()),
										srvcRateDTO.isPercentBased());

		return srvcRate;

	}

	public ServiceRateDto getServiceRateDTO(ServiceRate srvcRate)
	{
		ServiceRateDto srvcRateDto = new ServiceRateDto();
		srvcRateDto.setSrvcPlanName(srvcRate.getSrvcPlan().getSrvcPlanName());
		srvcRateDto.setService(srvcRate.getService());
		srvcRateDto.setChargeName(srvcRate.getSrvcChargeName());
		srvcRateDto.setChargeType(srvcRate.getSrvcRateId().getSvcChargeType().toString());
		srvcRateDto.setChargeAmount(srvcRate.getSrvcChargeRate().getPrice().getAmount().doubleValue());
		srvcRateDto.setChargeCurrency(srvcRate.getSrvcChargeRate().getPrice().getCurrency().toString());
		srvcRateDto.setChargeUnit(srvcRate.getSrvcChargeRate().getPerUnit().toString());
		srvcRateDto.setChargeFrequency(srvcRate.getSrvcChargeFrequency().toString());
		srvcRateDto.setPercentBased(srvcRate.isPercentileBased());
		return srvcRateDto;

	}

	public List<ServiceRateDto> getServiceRateDTOList(List<ServiceRate> srvcRateList)
	{
		List<ServiceRateDto> srvcRateDTOList = new ArrayList<ServiceRateDto>();

		if (srvcRateList.size() != 0)
		{
			for (ServiceRate srvcRate : srvcRateList)
			{
				srvcRateDTOList.add(this.getServiceRateDTO(srvcRate));
			}
		}

		return srvcRateDTOList;

	}

}
