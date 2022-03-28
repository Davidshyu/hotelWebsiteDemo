package com.activityOrder.model;

import java.sql.Date;
import java.util.List;


public class ActivityOrderService {
	private ActivityOrderDAO_interface dao;

	public ActivityOrderService() {
		dao = new ActivityOrderJNDIDAO();
	}

	public ActivityOrderVO insertActivityOrder(Integer mem_id,Integer activity_session_id,Date order_time,Integer entered_number,Date activity_started,Date activity_end,Integer order_amount,Integer order_state,Integer refund_state,String order_memo) {
		ActivityOrderVO activityOrderVO = new ActivityOrderVO();
		activityOrderVO.setMem_id(mem_id);
		activityOrderVO.setActivity_session_id(activity_session_id);
		activityOrderVO.setOrder_time(order_time);
		activityOrderVO.setEntered_number(entered_number);
		activityOrderVO.setActivity_started(activity_started);
		activityOrderVO.setActivity_end(activity_end);
		activityOrderVO.setOrder_amount(order_amount);
		activityOrderVO.setOrder_state(order_state);
		activityOrderVO.setRefund_state(refund_state);
		activityOrderVO.setOrder_memo(order_memo);	
		dao.insert(activityOrderVO);
		return activityOrderVO;
	}

	public ActivityOrderVO updateActivityOrder(Integer activity_order_id,Integer mem_id,Integer activity_session_id,Date order_time,Integer entered_number,Date activity_started,Date activity_end,Integer order_amount,Integer order_state,Integer refund_state,String order_memo) {
		ActivityOrderVO activityOrderVO = new ActivityOrderVO();
		activityOrderVO.setActivity_order_id(activity_order_id);
		activityOrderVO.setMem_id(mem_id);
		activityOrderVO.setActivity_session_id(activity_session_id);
		activityOrderVO.setOrder_time(order_time);
		activityOrderVO.setEntered_number(entered_number);
		activityOrderVO.setActivity_started(activity_started);
		activityOrderVO.setActivity_end(activity_end);
		activityOrderVO.setOrder_amount(order_amount);
		activityOrderVO.setOrder_state(order_state);
		activityOrderVO.setRefund_state(refund_state);
		activityOrderVO.setOrder_memo(order_memo);	
		dao.update(activityOrderVO);
		return activityOrderVO;
	}

	public void deleteActivityOrder(Integer activity_order_id) {
		dao.delete(activity_order_id);
	}

	public ActivityOrderVO getOneActivityOrder(Integer activity_order_id) {
		return dao.findByPrimaryKey(activity_order_id);
	}

	public List<ActivityOrderVO> getAllActivityOrder() {
		return dao.getAll();
	}
	public List<ActivityOrderVO> getActivityOrderByMemId(Integer mem_id){
		return dao.getAllByMemId(mem_id);
	}
}
