package org.bolanos.grpc.services

import io.grpc.stub.StreamObserver
import org.bolanos.domain.v1.Balance
import org.bolanos.domain.v1.BalanceAddRequest
import org.bolanos.domain.v1.BalanceCheckRequest
import org.bolanos.domain.v1.BalanceDeductRequest
import org.bolanos.domain.v1.BankServiceGrpc


class BankService : BankServiceGrpc.BankServiceImplBase() {
    private val database = AccountDatabase()

    override fun getBalance(request: BalanceCheckRequest?, responseObserver: StreamObserver<Balance>?) {
        val account = request?.accountNumber ?: -1

        responseObserver?.onNext(database.get_balance(account))
        responseObserver?.onCompleted()
    }

    override fun addBalance(request: BalanceAddRequest?, responseObserver: StreamObserver<Balance>?) {
        val account = request?.accountNumber ?: -1
        val amount = request?.amount ?: 0
        val balance = database.add_balance(account, amount)

        responseObserver?.onNext(balance)
        responseObserver?.onCompleted()
    }

    override fun deductBalance(request: BalanceDeductRequest?, responseObserver: StreamObserver<Balance>?) {
        val account = request?.accountNumber ?: -1
        val amount = request?.amount ?: 0

        val balance = database.deduct_balance(account, amount);
        responseObserver?.onNext(balance)
        responseObserver?.onCompleted()
    }
}