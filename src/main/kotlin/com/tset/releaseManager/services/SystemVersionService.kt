package com.tset.releaseManager.services

import com.tset.releaseManager.entities.MicroService
import com.tset.releaseManager.entities.SystemVersion
import com.tset.releaseManager.entities.SystemVersionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SystemVersionService(val db: SystemVersionRepository) {

    fun getRecentSystemVersion(): Int {
        return db.getRecentSystemVersion() ?: 0
    }

    fun addSystemVersion(systemVersion: SystemVersion) {
        db.save(systemVersion)
    }

    fun getExistingServicesForSystemVersion(version: Int): List<MicroService>? {
        val systemVersion: Optional<SystemVersion> = db.findById(version)
        return if(systemVersion.isPresent){
            systemVersion.get().microServices
        }else{
            listOf()
        }
    }
}