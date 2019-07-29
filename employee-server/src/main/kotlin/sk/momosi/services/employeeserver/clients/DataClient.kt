package sk.momosi.services.employeeserver.clients

import org.springframework.cloud.openfeign.FeignClient
import sk.momosi.services.Constants
import sk.momosi.servicesinterfaces.DataServerApi

@FeignClient(name = Constants.DATA_SERVER)
interface DataClient: DataServerApi
