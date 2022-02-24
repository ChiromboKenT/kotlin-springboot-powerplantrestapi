package assessments.com.powerplantrestapi.model

import java.util.*

data class PowerPlantResponse(

    val sequenceNumber: Int,
    val generatorId: String,
    val plantName: String,
    val pstatabb: String,
    val year: String,
    val generatorStatus: String,
    val genntan: String?,
    val createdAt: Date,
    val updatedAt: Date?



)
