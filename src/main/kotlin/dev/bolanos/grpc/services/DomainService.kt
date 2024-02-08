package dev.bolanos.grpc.services

//import dev.bolanos.v1.common.Address
import dev.bolanos.v1.common.Context
import dev.bolanos.v1.common.Domain
import org.springframework.stereotype.Service

@Service
class DomainService {
    fun getDomain():Domain = Domain.newBuilder()
		.setFoo(1)
		.setBar("2")
		.setBaz("3")
//		.setAddress(Address.newBuilder().setCity("SF").setStreet("sansome").setPostbox(94104).build())
		.setContext(Context.newBuilder().setZab("1").setBaz("2").build())
        .build()
}