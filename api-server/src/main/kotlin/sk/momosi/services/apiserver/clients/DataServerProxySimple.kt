package sk.momosi.services.apiserver.clients

import org.springframework.cloud.openfeign.FeignClient
import sk.momosi.services.Constants.DATA_SERVER
import sk.momosi.servicesinterfaces.DataServerApi

@FeignClient(name = DATA_SERVER)
interface DataServerProxySimple: DataServerApi
