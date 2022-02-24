package assessments.com.powerplantrestapi.repository

import assessments.com.powerplantrestapi.entity.PowerPlant
import org.springframework.data.jpa.repository.JpaRepository

interface PowerPlantRepository : JpaRepository<PowerPlant, Int>