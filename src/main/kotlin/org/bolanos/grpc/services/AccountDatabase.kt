package org.bolanos.grpc.services

import org.bolanos.domain.v1.Balance

class AccountDatabase {
    private val database: MutableMap<Int, Int> = (1..100).associateWith {
        it*100
    } as MutableMap<Int, Int>

    fun get_balance(account: Int): Balance {
        val balance = database[account]
        if (balance != null) {
            return Balance.newBuilder().setAccount(account).setAmount(balance).build()
        }

        throw Exception("Balance not found for account $account")
    }

    fun add_balance(account: Int, amount:Int): Balance {
        val balance = database[account]

        if (balance != null) {
            val newBalance = balance + amount
            database[account] = newBalance
            return Balance.newBuilder()
                .setAccount(account)
                .setAmount(newBalance)
                .build()
        }

        throw Exception("Balance not found for account $account")
    }

    fun deduct_balance(account: Int, amount:Int): Balance {
        val balance = database[account]

        if (balance != null) {
            val newBalance = balance - amount
            if(amount >= 0){
                database[account] = newBalance
                return Balance.newBuilder()
                    .setAccount(account)
                    .setAmount(newBalance)
                    .build()
            }

            throw Exception("Balance not enough")
        }

        throw Exception("Balance not found for account $account")
    }

}