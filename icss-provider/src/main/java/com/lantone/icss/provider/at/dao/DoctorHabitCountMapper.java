package com.lantone.icss.provider.at.dao;

import com.lantone.core.orm.EntityMapper;
import com.lantone.icss.api.at.model.DoctorHabitCount;
import com.lantone.icss.api.at.model.wrapper.DoctorHabitCountWrapper;

public interface DoctorHabitCountMapper extends EntityMapper<DoctorHabitCount, DoctorHabitCountWrapper, Long>{

	/**
	 * @Description:获取是否记录过该医生本记录的习惯
	 * @author:luwg
	 * @time:2017年1月17日 下午2:33:54
	 */
	public DoctorHabitCountWrapper getDoctorHabitCount(DoctorHabitCountWrapper docHabit);
	
	/**
	 * @Description:新增医生习惯
	 * @author:luwg
	 * @time:2017年1月17日 下午4:27:14
	 */
	public void insertDoctorHabitCount(DoctorHabitCountWrapper docHabit);
	
	/**
	 * @Description:修改医生习惯
	 * @author:luwg
	 * @time:2017年1月17日 下午4:29:18
	 */
	public void updateDoctorHabitCount(DoctorHabitCountWrapper docHabit);
}
