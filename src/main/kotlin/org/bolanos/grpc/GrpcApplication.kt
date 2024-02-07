package org.bolanos.grpc

import io.grpc.ServerBuilder
import org.bolanos.common.v1.Person
import org.bolanos.domain.v1.Context
import org.bolanos.grpc.services.BankService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcApplication

fun main(args: Array<String>) {
    runApplication<GrpcApplication>(*args)

    var context = Context.newBuilder().setPerson(Person.newBuilder().build()).build()

    val server = ServerBuilder.forPort(6565)
        .addService(BankService())
        .build()

    server.start()
    server.awaitTermination()
}
