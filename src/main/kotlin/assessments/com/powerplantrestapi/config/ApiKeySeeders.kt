package assessments.com.powerplantrestapi.config

import assessments.com.powerplantrestapi.entity.ApiKey
import assessments.com.powerplantrestapi.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeySeeders(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apiKey = "a4db08b7-5729-4ba9-8c08-f2df493465a"

    override fun run(args: ApplicationArguments?) {

        if (!apiKeyRepository.existsById(apiKey)) {
            val key = ApiKey(id = apiKey)
            apiKeyRepository.save(key)
        }
    }
}