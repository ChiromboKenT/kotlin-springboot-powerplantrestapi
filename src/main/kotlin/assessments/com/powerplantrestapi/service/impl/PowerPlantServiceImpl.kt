package assessments.com.powerplantrestapi.service.impl

import assessments.com.powerplantrestapi.entity.PowerPlant
import assessments.com.powerplantrestapi.error.NotFoundException
import assessments.com.powerplantrestapi.model.CreatePowerPlantRequest
import assessments.com.powerplantrestapi.model.ListPowerPlantRequest
import assessments.com.powerplantrestapi.model.PowerPlantResponse
import assessments.com.powerplantrestapi.model.UpdatePowerPlantRequest
import assessments.com.powerplantrestapi.repository.PowerPlantRepository
import assessments.com.powerplantrestapi.service.PowerPlantService
import assessments.com.powerplantrestapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class PowerPlantServiceImpl(
    val powerPlantRepository: PowerPlantRepository,
    val validationUtil: ValidationUtil
) : PowerPlantService {

    private fun convertPowerPlantToPowerPlantResponse(powerPlant: PowerPlant): PowerPlantResponse {

        return PowerPlantResponse(

            sequenceNumber = powerPlant.sequenceNumber,
            plantName = powerPlant.plantName,
            pstatabb = powerPlant.pstatabb,
            year = powerPlant.year,
            generatorId = powerPlant.generatorId,
            generatorStatus = powerPlant.generatorStatus,
            genntan = powerPlant.genntan,
            createdAt = powerPlant.createdAt,
            updatedAt = powerPlant.updatedAt

        )

    }

    private fun findPowerPlantByIdOrThrowNotFound(sequenceNumber: Int): PowerPlant {

        val powerplant = powerPlantRepository.findByIdOrNull(sequenceNumber)

        if (powerplant == null) {
            throw NotFoundException()
        } else {
            return powerplant
        }

    }

    override fun create(createPowerPlantRequest: CreatePowerPlantRequest): PowerPlantResponse {

        validationUtil.validate(createPowerPlantRequest)

        val powerplant = PowerPlant(

            sequenceNumber = createPowerPlantRequest.sequenceNumber,
            plantName = createPowerPlantRequest.plantName,
            generatorId = createPowerPlantRequest.generatorId,
            pstatabb = createPowerPlantRequest.pstatabb,
            year = createPowerPlantRequest.year,
            generatorStatus = createPowerPlantRequest.generatorStatus,
            genntan = createPowerPlantRequest.genntan!!,
            createdAt = Date(),
            updatedAt = null

        )

        powerPlantRepository.save(powerplant)

        return convertPowerPlantToPowerPlantResponse(powerplant)

    }

    override fun get(sequenceNumber: Int): PowerPlantResponse {

        val powerplant = findPowerPlantByIdOrThrowNotFound(sequenceNumber)

        return convertPowerPlantToPowerPlantResponse(powerplant)

    }

    override fun update(sequenceNumber: Int , updatePowerPlantRequest: UpdatePowerPlantRequest): PowerPlantResponse {

        val powerplant = findPowerPlantByIdOrThrowNotFound(sequenceNumber)

        validationUtil.validate(updatePowerPlantRequest)

        powerplant.apply {

            generatorId = updatePowerPlantRequest.generatorId
            plantName = updatePowerPlantRequest.plantName
            pstatabb = updatePowerPlantRequest.pstatabb
            year = updatePowerPlantRequest.year
            generatorStatus = updatePowerPlantRequest.generatorStatus
            genntan = updatePowerPlantRequest.genntan!!

            updatedAt = Date()

        }

        powerPlantRepository.save(powerplant)

        return convertPowerPlantToPowerPlantResponse(powerplant)

    }

    override fun list(listPowerPlantRequest: ListPowerPlantRequest): List<PowerPlantResponse> {

        return if(listPowerPlantRequest.isPagged){
            val page = powerPlantRepository.findAll(PageRequest.of(listPowerPlantRequest.page, listPowerPlantRequest.size))
                .filter{it.pstatabb.contains(listPowerPlantRequest.locationFilter, true)}
            val powerplants: List<PowerPlant> = page.get().collect(Collectors.toList())

            powerplants.map { convertPowerPlantToPowerPlantResponse(it) }
        }else{

            val powerplants: List<PowerPlant> = powerPlantRepository.findAll()
                .filter{it.pstatabb.contains(listPowerPlantRequest.locationFilter, true)}
            powerplants.map { convertPowerPlantToPowerPlantResponse(it) }

        }

    }

    override fun delete(sequenceNumber: Int) {

        val powerplant = findPowerPlantByIdOrThrowNotFound(sequenceNumber)

        powerPlantRepository.delete(powerplant)
    }

}