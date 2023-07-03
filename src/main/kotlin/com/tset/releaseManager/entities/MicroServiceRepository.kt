package com.tset.releaseManager.entities

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MicroServiceRepository : CrudRepository<MicroService, Int> {

    @Query("SELECT s FROM MicroService s WHERE s.serviceName = :serviceName order by s.versionNumber desc LIMIT 1")
    fun getRecentServiceByServiceName(serviceName:String): MicroService?
}