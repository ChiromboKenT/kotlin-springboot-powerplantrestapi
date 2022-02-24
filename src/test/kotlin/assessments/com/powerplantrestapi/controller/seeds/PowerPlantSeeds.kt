package assessments.com.powerplantrestapi.controller.seeds

import assessments.com.powerplantrestapi.entity.PowerPlant
import assessments.com.powerplantrestapi.repository.PowerPlantRepository
import java.util.*

class PowerPlantSeeds(private val powerPlantRepository: PowerPlantRepository) {

    fun insertMultiplePowerPlants() {
        powerPlantRepository.saveAll(
            listOf(
                PowerPlant(
                    sequenceNumber = 0,
                    generatorId = "23C",
                    plantName = "Alukele",
                    pstatabb = "AK",
                    year = "2007",
                    generatorStatus = "RE",
                    genntan = "590",
                    createdAt = Date(),
                    updatedAt = null
                ),
                PowerPlant(
                    sequenceNumber = 1,
                    generatorId = "23E",
                    plantName = "Fraca",
                    pstatabb = "AK",
                    year = "1990",
                    generatorStatus = "RE",
                    genntan = "123456",
                    createdAt = Date(),
                    updatedAt = null
                ),
                PowerPlant(
                    sequenceNumber = 2,
                    generatorId = "34E",
                    plantName = "Eskom",
                    pstatabb = "#SE",
                    year = "170",
                    generatorStatus = "RE",
                    genntan = "123456",
                    createdAt = Date(),
                    updatedAt = null
                )

            )
        )
    }
}