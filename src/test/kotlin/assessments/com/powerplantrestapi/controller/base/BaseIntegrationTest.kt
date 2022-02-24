package assessments.com.powerplantrestapi.controller.base

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional

@ContextConfiguration(initializers = [BaseITInitializer::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseIntegrationTest {

    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    @Transactional
    protected fun cleanDB() {
        val tablesToTruncate = listOf("powerplant").joinToString()
        val sql = """  
            TRUNCATE TABLE $tablesToTruncate CASCADE 
        """.trimIndent()
        jdbcTemplate?.execute(sql)
    }
}