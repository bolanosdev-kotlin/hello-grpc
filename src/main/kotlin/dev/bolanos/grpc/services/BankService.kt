package dev.bolanos.grpc.services

import io.grpc.stub.StreamObserver
import dev.bolanos.v1.common.Balance
import dev.bolanos.v1.services.BalanceAddRequest
import dev.bolanos.v1.services.BalanceCheckRequest
import dev.bolanos.v1.services.BalanceDeductRequest
import dev.bolanos.v1.services.BankServiceGrpc


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