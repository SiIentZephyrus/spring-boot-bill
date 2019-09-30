package com.example.springbootbill.mapper;

import com.example.springbootbill.entities.Provider;

import java.util.List;

//@Mapper
public interface ProviderMapper {
    //@Select("select * from provider where pid = #{pid}")
    Provider getProviderById(Integer pid);

    List<Provider> getProviders(Provider provider);

    int addProvider(Provider provider);
    int deleteProvider(Integer pid);
    int updateProvider(Provider provider);
}
