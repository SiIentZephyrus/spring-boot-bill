package com.example.springbootbill.mapper;

import com.example.springbootbill.entities.Bill;
import com.example.springbootbill.entities.BillProvider;

import java.util.List;

public interface BillMapper {
    List<BillProvider> getBills(Bill bill);
    BillProvider getBillById(Integer bid);
    //返回的是只有pid和供应商的名字用来选择
    List<BillProvider> getPidAndProviderName();
    //查询bill表中付款情况
    List<Bill> getPayStat();
    int insertBill(Bill bill);
    int updateBill(Bill bill);
    int deleteBill(Integer bid);

}
