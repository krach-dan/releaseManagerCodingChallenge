package com.tset.releaseManager.api

import com.tset.releaseManager.entities.MicroService
import com.tset.releaseManager.services.SystemVersionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("services")
class VersionController(private val impl: SystemVersionService) {

    @GetMapping("/systemVersion={systemVersion}")
    fun getServicesBySystemVersion(@PathVariable(value = "systemVersion") systemVersion: Int): List<MicroService>{
        return impl.getExistingServicesForSystemVersion(systemVersion)?: listOf()
    }
}