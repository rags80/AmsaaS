package archive;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ams.billingandpayment.domain.model.service.ServiceRate;
import com.ams.sharedkernel.domain.model.measureandunits.Money;
import com.ams.sharedkernel.domain.model.measureandunits.Period;
import com.ams.sharedkernel.domain.model.measureandunits.Unit;
import com.ams.usermanagement.domain.model.Person;

public class ServiceCharge
{

	public Money forPeriod(Person chargedPersn, Period billPeriod,
						ServiceRate
						serviceRate, Date chargedDate)
	{

		this.chargedPerson = chargedPersn;
		this.srvcChargeDate = chargedDate;
		this.chargeForService = serviceRate.getService();

		Unit billFreqUnit = serviceRate.getSrvcChargeRate().getPerUnit();
		BigDecimal qty = new BigDecimal(0);
		Date srvcStartDate = chargedPersn.getPersnServiceProfile().getSrvcSubcrptnStartDate();
		switch (billFreqUnit)
		{
		case Months:
			if (srvcStartDate.after(billPeriod.getFromDate()))
			{
				double noOfDays =
						Days.daysBetween(new DateTime(srvcStartDate).toDateMidnight(), new
										DateTime(billPeriod.getToDate()).toDateMidnight()).getDays();
				double daysInMonth = Days.daysBetween(new
												DateTime(billPeriod.getFromDate()).toDateMidnight(), new
												DateTime(billPeriod.getToDate()).toDateMidnight()).getDays();
				System.out.println("No of days:" + noOfDays);
				System.out.println("days in Bill period:" + daysInMonth);
				qty = new BigDecimal(noOfDays / daysInMonth);

			}
			else
			{
				qty = new BigDecimal(1);
			}
			break;

		}

		setPriceAndQuantity(qty, Unit.Months,
						serviceRate.getSrvcChargeRate().getPrice().getAmount(),
						serviceRate.getSrvcChargeRate().getPrice().getCurrency());
		return this.chargedAmount;
	}

}
