package com.tset.releaseManager.api.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class DeployInfo(
    @field:NotEmpty
    val serviceName:String,

    @field:NotNull
    @field:Min(value = 0)
    val serviceVersionNumber:Int )