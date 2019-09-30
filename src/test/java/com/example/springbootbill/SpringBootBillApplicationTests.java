package com.example.springbootbill;

import com.example.springbootbill.entities.Bill;
import com.example.springbootbill.entities.BillProvider;
import com.example.springbootbill.entities.Provider;
import com.example.springbootbill.mapper.BillMapper;
import com.example.springbootbill.mapper.ProviderMapper;
import com.example.springbootbill.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBillApplicationTests {

    @Autowired
    private ProviderMapper providerMapper;

    @Autowired
    private BillMapper billMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void SelectTest(){
        List<Provider> providers = providerMapper.getProviders(new Provider());
        //providers.forEach(provider -> System.out.println(provider));
        providers.forEach(System.out::println);
    }

    @Test
    public void addTest(){
        Provider provider = providerMapper.getProviderById(1);
        provider.setProviderName("A货云服务供应商22");
        providerMapper.updateProvider(provider);
    }


    @Test
    public void compareTest(){
        String[] arrs = {"qw","qssa","1sssaa","qweqwrssz","ssa","q"};
        //Integer[] arr = {11,232,11};
        ArrayList<String> list = new ArrayList<String>(arrs.length);
        Collections.addAll(list, arrs);
        //Collections.sort(list, (String s1, String s2) -> (s1.compareTo(s2)));
        //Collections.sort(list, (String s1, String s2) -> (s1.length()-s2.length()));

        Comparator<String> sortCompare = (String s1, String s2) -> (s1.charAt(s1.length()-1)-s2.charAt(s2.length()-1));
        Collections.sort(list,sortCompare);
        list.forEach(str -> System.out.println(str));
    }

    @Test
    public void billInsert(){
        /*BillProvider existBill = billMapper.getBillById(1);
        //System.out.println(existBill);
        Bill bill = (Bill) existBill;
        bill.setBillName("ESC包年云服务花钱");
        billMapper.updateBill(bill);
        List<BillProvider> bills = billMapper.getBills(new Bill());
        bills.forEach(billProvider -> System.out.println(billProvider));
        Bill bill = new Bill();
        bill.setBillName("阿里云包年活动");
        bill.setBillCode("B_007");
        bill.setBillCom("日");
        bill.setBillNum(21);
        bill.setMoney(2000.0);
        bill.setPay(22325);
        bill.setPid(1);
        billMapper.insertBill(bill);*/
    }

    @Test
    public void delBill(){
        billMapper.deleteBill(6);
    }

    @Test
    public void stringParseDate() throws ParseException {
        String s = new String("1996-09-20");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        System.out.println(date);
    }

    @Autowired
    private UserMapper userMapper;
    @Test
    public void userDelTest(){
        userMapper.deleteUser(2);
    }


}
