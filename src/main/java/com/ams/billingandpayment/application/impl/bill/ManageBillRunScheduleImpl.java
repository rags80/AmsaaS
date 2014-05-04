package com.ams.billingandpayment.application.impl.bill;

import com.ams.billingandpayment.application.api.service.bill.ManageBillRunSchedule;
import com.ams.billingandpayment.domain.model.schedule.BillRunSchedule;
import com.ams.billingandpayment.domain.model.schedule.Schedule;
import com.ams.users.domain.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Raghavendra Badiger
 */
@Transactional
@org.springframework.stereotype.Service("ManageBillRunSchedule")
public class ManageBillRunScheduleImpl implements ManageBillRunSchedule {

    private BillRunSchedule billRunSchedule;

    @Override
    public boolean newBillRunSchedule(List<Person> billableUserList, Schedule schedule) {
        this.billRunSchedule = new BillRunSchedule(billableUserList, schedule);
        return false;
    }

}
