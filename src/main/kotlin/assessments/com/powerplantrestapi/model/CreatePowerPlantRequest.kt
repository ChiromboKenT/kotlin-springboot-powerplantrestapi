package assessments.com.powerplantrestapi.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreatePowerPlantRequest(

    @field:NotNull
    val sequenceNumber: Int,

    @field:NotBlank
    val generatorId : String,

    @field:NotBlank
    val plantName: String,

    @field:NotBlank
    val pstatabb: String,

    @field:NotNull
    val year: String,

    @field:NotBlank
    val generatorStatus: String,

    @field:NotBlank
    val genntan: String?,


)