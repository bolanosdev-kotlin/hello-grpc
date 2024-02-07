package org.bolanos.grpc.services

import org.bolanos.common.v1.Address
import org.bolanos.domain.v1.Context
import org.bolanos.domain.v1.Domain
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