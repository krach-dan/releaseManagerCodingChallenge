package com.tset.releaseManager.services

import com.tset.releaseManager.api.dto.DeployInfo
import com.tset.releaseManager.entities.MicroService
import com.tset.releaseManager.entities.MicroServiceRepository
import com.tset.releaseManager.entities.SystemVersion
import org.springframework.stereotype.Service

@Service
class MicroServiceService(val db: MicroServiceRepository, val systemVersionService: SystemVersionService) {

    fun getRecentServiceByServiceName(serviceName: String) : MicroService?{
        return db.getRecentServiceByServiceName(serviceName)
    }

    fun addNewMicroService(deployInfo: DeployInfo): Int{

        val recentVersion = systemVersionService.getRecentSystemVersion()

        val systemVersion = SystemVersion(recentVersion + 1)


        val service =
            MicroService(versionNumber = deployInfo.serviceVersionNumber, serviceName = deployInfo.serviceName, systemVersion = mutableListOf(systemVersion))
        val listOfMicroServices: MutableList<MicroService> = mutableListOf(service)

        listOfMicroServices += getAlreadyDeployedMicroservices(recentVersion, deployInfo.serviceName)

        systemVersion.microServices = listOfMicroServices

        systemVersionService.addSystemVersion(systemVersion)

        return systemVersion.version
    }

    private fun getAlreadyDeployedMicroservices(recentVersion: Int, serviceName: String): List<MicroService>{
        var deployedMicroServices = systemVersionService.getExistingServicesForSystemVersion(recentVersion)

        return if (!deployedMicroServices.isNullOrEmpty()) {
            deployedMicroServices = deployedMicroServices.filter { s -> s.serviceName != serviceName }
            deployedMicroServices
        }else{
            listOf()
        }
    }

}