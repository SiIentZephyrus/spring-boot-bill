package com.example.springbootbill.controller;

import com.example.springbootbill.entities.Provider;
import com.example.springbootbill.mapper.ProviderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class ProviderController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProviderMapper providerMapper;

    @GetMapping("/providers")
    public String list(Model model,Provider provider){
        List<Provider> list = providerMapper.getProviders(provider);
        model.addAttribute("list",list);
        model.addAttribute("providerName",provider.getProviderName());
        return "provider/list";
    }

    @GetMapping("/provider/{pid}")
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
    }

    @PostMapping("getAllProviderName")
    @ResponseBody
    public List<Provider> getAllProviderName(){
        List<Provider> providers = providerMapper.getProviders(null);
        return providers;
    }

}
