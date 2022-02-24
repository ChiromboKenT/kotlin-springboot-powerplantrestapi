package assessments.com.powerplantrestapi.repository

import assessments.com.powerplantrestapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String>