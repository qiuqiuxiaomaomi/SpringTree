package com.bonaparte.service;

import com.bonaparte.bean.MajorityBean;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/6/29.
 * 序列化机制 (json, hessian)
 * 字节流到对象，对象到字节流，数据从磁盘到内存，数据从内存持久化到磁盘
 */
@Service
public class SerializeService {

    public void serializeCheck(){
        MajorityBean majorityBean = new MajorityBean();
        majorityBean.setAdress("中国成都");
        majorityBean.setMajorityName("计算机学院");
        majorityBean.setStudentCount(65200);
        majorityBean.setCode("001002003");
        majorityBean.setUniversityName("四川大学");
        System.out.println(majorityBean);
    }
}
