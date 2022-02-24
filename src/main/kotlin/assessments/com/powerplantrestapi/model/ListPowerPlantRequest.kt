package assessments.com.powerplantrestapi.model

data class ListPowerPlantRequest(

    val page: Int,
    val isPagged : Boolean,
    val size: Int,
    //val topFilter : String,
    //val bottomFilter : String,
    val locationFilter : String,

)
