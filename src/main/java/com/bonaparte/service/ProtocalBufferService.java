package com.bonaparte.service;

import com.alibaba.fastjson.JSON;
import com.bonaparte.bean.Company;
import com.bonaparte.bean.CompanyProto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangmingquan on 2018/7/11.
 */
@Service
public class ProtocalBufferService {

    public void basicOp(){
        CompanyProto.Companys.Builder builders = CompanyProto.Companys.newBuilder();
        CompanyProto.Companys.Company.Builder builder = CompanyProto.Companys.Company.newBuilder();
        builder.setId(1);
        builder.setMemberCount(10);
        builder.setName("大象公司");
        builder.addAliasName("大象有限公司");
        builder.addAliasName("大象科技公司");
        builders.addCompanys(builder);

        CompanyProto.Companys.Company.Builder builder2 = CompanyProto.Companys.Company.newBuilder();
        builder2.setId(2);
        builder2.setMemberCount(16);
        builder2.setName("小铁人公司");
        builder2.addAliasName("小铁人有限公司");
        builder2.addAliasName("小铁人科技公司");
        builders.addCompanys(builder2);

        CompanyProto.Companys companys = builders.build();
        System.out.println("输出对象信息" + companys);
        System.out.println("输出对象字节化信息" + companys.toByteArray());
        System.out.println("pb长度" + companys.toByteArray().length);
        modelToJavaEntity(companys.getCompanysList());
    }

    public void modelToJavaEntity(List<CompanyProto.Companys.Company> companyList){
        List<Company> companies = new ArrayList<>();
        for(CompanyProto.Companys.Company company: companyList){
            Company companyTemp = new Company();
            companyTemp.setName(company.getName());
            companyTemp.setId(company.getId());
            companyTemp.setMemberCount(company.getMemberCount());
            companyTemp.setAliasName(company.getAliasNameList());

            companies.add(companyTemp);
        }

        String str = JSON.toJSONString(companies);
        System.out.println("反序列化对象为：" + str);
        System.out.println("反序列化长度为" + str.length());
    }
}
