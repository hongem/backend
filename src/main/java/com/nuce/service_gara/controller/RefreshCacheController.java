package com.nuce.service_gara.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class RefreshCacheController {

    @GetMapping("/clear-cache")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = {"CACHE_SERVICE", "CACHE_JOB", "CACHE_PART"}, allEntries = true)
    public String clearCache() {
        return "Clear cache success";
    }
}
