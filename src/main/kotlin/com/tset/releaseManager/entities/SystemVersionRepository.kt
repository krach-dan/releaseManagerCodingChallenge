package com.tset.releaseManager.entities

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface SystemVersionRepository : CrudRepository<SystemVersion, Int> {

    @Query("select MAX(sv.version) FROM SystemVersion sv")
    fun getRecentSystemVersion(): Int?
}