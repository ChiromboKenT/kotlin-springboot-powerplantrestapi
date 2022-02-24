package assessments.com.powerplantrestapi.model

import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdatePowerPlantRequest(

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
