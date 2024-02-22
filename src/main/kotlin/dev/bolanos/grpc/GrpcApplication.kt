package dev.bolanos.grpc

import io.grpc.ServerBuilder
import dev.bolanos.v1.common.Person
import dev.bolanos.v1.common.Context
import dev.bolanos.grpc.services.BankService
import dev.bolanos.grpc.services.DomainService
import dev.bolanos.v3.common.Television
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcApplication

fun main(args: Array<String>) {
    runApplication<GrpcApplication>(*args)

    var context = Context.newBuilder().setPerson(Person.newBuilder().build()).build()
    var television = Television.newBuilder().setIsFlat(true).build()

    val server = ServerBuilder.forPort(6565)
        .addService(BankService())
        .build()

    server.start()
    server.awaitTermination()
}
