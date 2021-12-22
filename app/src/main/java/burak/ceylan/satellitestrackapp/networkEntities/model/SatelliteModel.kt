package burak.ceylan.satellitestrackapp.networkEntities.model

import com.google.gson.annotations.SerializedName

data class SatelliteModel(

    @field:SerializedName("mass")
    val mass: Int? = null,

    @field:SerializedName("cost_per_launch")
    val costPerLaunch: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("first_flight")
    val firstFlight: String? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("active")
    val active: Boolean? = null
)
