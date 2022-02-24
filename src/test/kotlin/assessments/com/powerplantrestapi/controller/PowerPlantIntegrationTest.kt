package assessments.com.powerplantrestapi.controller

import assessments.com.powerplantrestapi.controller.base.BaseIntegrationTest
import assessments.com.powerplantrestapi.controller.seeds.PowerPlantSeeds
import assessments.com.powerplantrestapi.entity.PowerPlant
import assessments.com.powerplantrestapi.repository.PowerPlantRepository
import com.fasterxml.jackson.databind.ObjectMapper
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.json.JSONArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

class PowerPlantIntegrationTest  : BaseIntegrationTest() {

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var powerPlantRepository: PowerPlantRepository
    val baseUrl = "/api/power-plant"

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        cleanDB()
        PowerPlantSeeds(powerPlantRepository).insertMultiplePowerPlants()
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }
    @Nested
    @DisplayName("GET api/single-powerplant")
    inner class GetSinglePowerPlant{


        @Test
        fun `should return not found for single plant`() {
            val sequenceNumber = 10
            //That
            val performGet = mockMvc.perform(get("$baseUrl/$sequenceNumber")
                .header("X-Api-Key","a4db08b7-5729-4ba9-8c08-f2df493465a"))

            performGet
                .andExpect(status().isNotFound)

        }

        @Test
        fun `should return not authenticated`() {
            //Given
            val sequenceNumber = 10
            //That
            mockMvc.get("$baseUrl/$sequenceNumber")
                .andExpect { status { isUnauthorized() } }
        }
    }

    @Nested
    @DisplayName("GET api/get all power plants")
    inner class  GetPowerPlants{
        @Test
        fun `should return not authenticated`() {
            //That
            mockMvc.get("$baseUrl")
                .andExpect { status { isUnauthorized() } }
        }
        @Test
        fun `should return all powerplants`() {
            val request = mockMvc.perform(
                    get(baseUrl)
                        .header("X-Api-Key", "a4db08b7-5729-4ba9-8c08-f2df493465a"))
            val response = request.andExpect(status().isOk)
            val jsonArray = response.andReturn().response.contentAsString

            // Http verification
            assertNotNull(jsonArray)
        }

    }

    @Nested
    @DisplayName("POST api/add-plant")
    inner class PostPlant {
        @Test
        fun `should return bad request for empty post`() {
            val performPost = mockMvc.perform(
                MockMvcRequestBuilders.post("$baseUrl")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-Api-Key","a4db08b7-5729-4ba9-8c08-f2df493465a")
            )

            performPost.andDo { println() }
                .andExpect(status().isBadRequest)
        }
        @Test
        fun `should return unsupported media type`() {
            //GIVEN
            val plant = PowerPlant(
                sequenceNumber = 2,
                generatorId = "23E",
                plantName = "Alukele",
                pstatabb = "#rER",
                year = "2007",
                generatorStatus = "RE",
                genntan = "123456",
                createdAt = Date(),
                updatedAt = null
            )
            val performPost = mockMvc.perform(
                MockMvcRequestBuilders.post("$baseUrl")
                    .content(objectMapper.writeValueAsString(plant))
                    .header("X-Api-Key","a4db08b7-5729-4ba9-8c08-f2df493465a")
            )

            performPost.andDo { println() }
                .andExpect(status().isUnsupportedMediaType)
        }
        @Test
        fun `should return not authenticated`() {
            //GIVEN
            val plant = PowerPlant(
                sequenceNumber = 2,
                generatorId = "23E",
                plantName = "Alukele",
                pstatabb = "#rER",
                year = "2007",
                generatorStatus = "RE",
                genntan = "123456",
                createdAt = Date(),
                updatedAt = null
            )

            val performPost = mockMvc.perform(
                MockMvcRequestBuilders.post("$baseUrl")
                    .content(objectMapper.writeValueAsString(plant))
                    .contentType(MediaType.APPLICATION_JSON)

            )

            performPost.andDo { println() }
                .andExpect(status().isUnauthorized)

        }

        @Test
        fun `should return CREATED status code`() {
            //GIVEN
            val plant = PowerPlant(
                sequenceNumber = 2,
                generatorId = "23E",
                plantName = "Alukele",
                pstatabb = "#rER",
                year = "2007",
                generatorStatus = "RE",
                genntan = "123456",
                createdAt = Date(),
                updatedAt = null
            )

            val performPost = mockMvc.perform(
                MockMvcRequestBuilders.post("$baseUrl")
                .content(objectMapper.writeValueAsString(plant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Api-Key","a4db08b7-5729-4ba9-8c08-f2df493465a"))

            performPost.
            andDo{ println()}
                .andExpect(status().isCreated)

        }

        @Test
        fun `should return 400 status code`() {
            //GIVEN
            val plant = PowerPlant(
                sequenceNumber = 2,
                generatorId = "23E",
                plantName = "",
                pstatabb = "#rER",
                year = "2007",
                generatorStatus = "RE",
                genntan = "123456",
                createdAt = Date(),
                updatedAt = Date()
            )

            val performPost = mockMvc.perform(
                MockMvcRequestBuilders.post("$baseUrl")
                .content(objectMapper.writeValueAsString(plant))
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Api-Key","a4db08b7-5729-4ba9-8c08-f2df493465a"))

            performPost.andDo{ println()}
                .andExpect(status().isBadRequest)

        }
    }


}