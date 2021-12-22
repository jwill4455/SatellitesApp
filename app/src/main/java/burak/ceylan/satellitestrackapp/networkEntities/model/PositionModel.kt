package burak.ceylan.satellitestrackapp.networkEntities.model

import com.google.gson.annotations.SerializedName

data class PositionModel(

    @field:SerializedName("list")
    val list: List<ListItem?>? = null
)

data class PositionsItem(

    @field:SerializedName("posX")
    val posX: Double? = null,

    @field:SerializedName("posY")
    val posY: Double? = null
)

data class ListItem(

    @field:SerializedName("positions")
    val positions: List<PositionsItem?>? = null,

    @field:SerializedName("id")
    val id: String? = null
)
