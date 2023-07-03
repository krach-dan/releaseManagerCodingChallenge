package com.tset.releaseManager.entities

import jakarta.persistence.*


@Entity
@Table(name="SYSTEM_VERSION")
data class SystemVersion (

    @Id
    var version: Int,

    @ManyToMany(cascade = [CascadeType.ALL])
    var microServices: List<MicroService>?
) {
    constructor(id: Int) : this(id, null)
    constructor() : this(0, null)
}