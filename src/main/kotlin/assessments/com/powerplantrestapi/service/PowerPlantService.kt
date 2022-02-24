package assessments.com.powerplantrestapi.service

import assessments.com.powerplantrestapi.model.CreatePowerPlantRequest
import assessments.com.powerplantrestapi.model.ListPowerPlantRequest
import assessments.com.powerplantrestapi.model.PowerPlantResponse
import assessments.com.powerplantrestapi.model.UpdatePowerPlantRequest

interface PowerPlantService {

    fun create(createPowerPlantRequest: CreatePowerPlantRequest): PowerPlantResponse

    fun get(sequenceNumber: Int): PowerPlantResponse

    fun update(sequenceNumber: Int, updatePowerPlantRequest: UpdatePowerPlantRequest): PowerPlantResponse

    fun delete(sequenceNumber: Int)

    fun list(listPowerPlantRequest: ListPowerPlantRequest): List<PowerPlantResponse>

}