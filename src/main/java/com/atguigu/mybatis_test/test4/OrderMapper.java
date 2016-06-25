package com.atguigu.mybatis_test.test4;

import com.atguigu.mybatis_test.bean.Order;
import org.apache.ibatis.annotations.Select;

/**
 * @author xueaohui
 */
public interface OrderMapper {
    @Select("select order_id id, order_no orderNo,order_price price from orders where order_id=#{id}")
    Order selectOrder(int id);
}
