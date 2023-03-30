package com.rawt.recomender.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "person", url = "${recomender.personApiUrl}")
public interface PersonClient {

    @RequestMapping("/")
    public String getPerson(@RequestParam String inc);
}
