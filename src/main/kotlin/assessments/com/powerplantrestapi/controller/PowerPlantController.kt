package assessments.com.powerplantrestapi.controller

import assessments.com.powerplantrestapi.model.*
import assessments.com.powerplantrestapi.service.PowerPlantService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/power-plant")
class PowerPlantController(val powerPlantService: PowerPlantService) {

    @PostMapping(
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createPowerPlant(
        @RequestBody createPowerPlantRequest: CreatePowerPlantRequest
    ): ResponseEntity<WebResponse<PowerPlantResponse>> {

        println(createPowerPlantRequest)
        val powerPlantResponse = powerPlantService.create(createPowerPlantRequest)


        return ResponseEntity(WebResponse(
            code = 201,
            status = "CREATED",
            data = powerPlantResponse
        ), HttpStatus.CREATED)

    }

    @GetMapping(
        value = ["/{sequenceNumber}"],
        produces = ["application/json"]
    )
    fun getPowerPlant(@PathVariable(value = "sequenceNumber") sequenceNumber: Int): ResponseEntity<WebResponse<PowerPlantResponse>> {

        val powerPlantResponse = powerPlantService.get(sequenceNumber)

        return ResponseEntity(WebResponse(
            code = 200,
            status = "OK",
            data = powerPlantResponse
        ),HttpStatus.OK)

    }

    @PutMapping(
        value = ["{sequenceNumber}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updatePowerPlant(
        @PathVariable(value = "sequenceNumber") sequenceNumber: Int,
        @RequestBody updatePowerPlantRequest: UpdatePowerPlantRequest
    ): WebResponse<PowerPlantResponse> {

        val powerPlantResponse = powerPlantService.update(sequenceNumber, updatePowerPlantRequest)

        return WebResponse(
            code = 200,
            status = "OK",
            data = powerPlantResponse
        )

    }

    @GetMapping(
        produces = ["application/json"]
    )
    fun listPowerPlants(@RequestParam(value="top", required = false, defaultValue = "") topNfilter: String,
                        @RequestParam(value="bottom", required = false, defaultValue = "") bottomNFilter: String,
                        @RequestParam(value = "location", required = false, defaultValue = "") locationFilter: String,
                        @RequestParam(value = "size", required = false, defaultValue = "") size: String,
                        @RequestParam(value = "page", required = false, defaultValue = "") page: String,
    ): ResponseEntity<WebResponse<Collection<PowerPlantResponse>>> {

        var pageSize = if (!locationFilter.isNullOrBlank() &&  !size.isNullOrBlank()) size.toInt() else 0
        var pageNumber = if (!locationFilter.isNullOrBlank() &&  !size.isNullOrBlank()) size.toInt() else 0
        var hasPaging = (!locationFilter.isNullOrBlank() &&  !size.isNullOrBlank())


        val request = ListPowerPlantRequest(size = pageSize, page = pageNumber, isPagged = hasPaging,locationFilter=locationFilter)
        val response = powerPlantService.list(request)


        return ResponseEntity(WebResponse(
            code = 200,
            status = "OK",
            data = response
        ), HttpStatus.OK)


    }

    @DeleteMapping(
        value = ["/{sequenceNumber}"],
        produces = ["application/json"]
    )
    fun deletePowerPlant(
        @PathVariable(value = "sequenceNumber") sequenceNumber: Int
    ): WebResponse<String> {

        powerPlantService.delete(sequenceNumber)

        return WebResponse(
            code = 200,
            status = "OK",
            data = "DELETED $sequenceNumber"
        )
    }

}