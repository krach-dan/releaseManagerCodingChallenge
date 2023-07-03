package com.tset.releaseManager.api

import com.tset.releaseManager.api.dto.DeployInfo
import com.tset.releaseManager.entities.MicroService
import com.tset.releaseManager.services.MicroServiceService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("deploy")
class DeployController(private val impl: MicroServiceService) {

    @PostMapping
    fun updateSystemVersionNumber(@Valid @RequestBody request: DeployInfo):Int {

        val microService: MicroService? = impl.getRecentServiceByServiceName(request.serviceName)
        if(microService == null || request.serviceVersionNumber > microService.versionNumber){
            return impl.addNewMicroService(request)
        }

        return microService.systemVersions.get(microService.systemVersions.size-1).version
    }
}