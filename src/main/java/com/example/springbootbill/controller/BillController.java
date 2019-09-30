package com.example.springbootbill.controller;

import com.example.springbootbill.entities.Bill;
import com.example.springbootbill.entities.BillProvider;
import com.example.springbootbill.mapper.BillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BillController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BillMapper billMapper;

    @GetMapping("/bills")
    public String list(Model model, Bill bill){
        List<BillProvider> list = billMapper.getBills(bill);
        model.addAttribute("list",list);
        model.addAttribute("searchBill",bill);
        return "bill/list";
    }

    @GetMapping("/bill/{bid}")
    public String getBillById(@RequestParam(value="type", defaultValue = "view")String type, @PathVariable("bid")Integer bid, Model model){
        BillProvider bill = billMapper.getBillById(bid);
        model.addAttribute("bill",bill);
        return "bill/"+type;
    }

    @GetMapping("/bill")
    public String toAddPage(){
        return "bill/add.html";
    }

    @PostMapping("/bill")
    public String addBill(Bill bill){
        billMapper.insertBill(bill);
        return "redirect:/bills";
    }

    @PutMapping("/bill")
    public String updateBill(Bill bill){
        billMapper.updateBill(bill);
        return "redirect:/bills";
    }

    @DeleteMapping("bill/{bid}")
    public String delBill(@PathVariable("bid")Integer bid){
        billMapper.deleteBill(bid);
        return "redirect:/bills";
    }

    //ajax返回前端所有bill中的供应商信息
    @PostMapping("/providerNames")
    @ResponseBody
    public List<BillProvider> getProviderNames(){
        List<BillProvider> bills = billMapper.getPidAndProviderName();
        return bills;
    }

    //返回bill中所有的付款情况
    @PostMapping("/payStat")
    @ResponseBody
    public List<Bill> getPayStat(){
        List<Bill> pays = billMapper.getPayStat();
        return pays;
    }

    /*@GetMapping("/provider/{pid}")
    public String findProvider(@RequestParam(value = "type",defaultValue = "view")String type, @PathVariable("pid") Integer pid, Model model){
        Provider existProvider = providerMapper.getProviderById(pid);
        model.addAttribute("provider",existProvider);
        return "provider/"+type;
    }

    @GetMapping("/provider")
    public String toAddPage(){
        return "provider/add";
    }



    @PutMapping("/provider")
    public String updateProvider(Provider provider){
        provider.setCreateDate(new Date());
        providerMapper.updateProvider(provider);
        return "redirect:/providers";
    }

    @DeleteMapping("/provider/{pid}")
    public String delProvider(@PathVariable("pid")Integer pid){
        providerMapper.deleteProvider(pid);
        return "redirect:/providers";
    }

    @PostMapping("provider")
    public String addProvider(Provider provider){
        provider.setCreateDate(new Date());
        providerMapper.addProvider(provider);
        return "redirect:/providers";
    }*/
}
