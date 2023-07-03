package com.tset.releaseManager.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name="SERVICES")
data class MicroService(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    var serviceName: String,

    var versionNumber: Int,

    @ManyToMany
    @JsonIgnore
    var systemVersions: MutableList<SystemVersion>
){
    constructor(serviceName: String, versionNumber: Int, systemVersion: MutableList<SystemVersion>)
            : this(0, serviceName, versionNumber, systemVersion)

    constructor() : this(0, "", 0, mutableListOf(SystemVersion()))
}