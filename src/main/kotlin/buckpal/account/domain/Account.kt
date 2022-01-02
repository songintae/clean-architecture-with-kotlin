package buckpal.account.domain

import java.time.LocalDateTime

class Account(
    id: AccountId,
    baselineBalance: Money,
    activityWindow: ActivityWindow
) {

    private val id: AccountId
    private val baselineBalance: Money
    private val activityWindow: ActivityWindow

    init {
        this.id = id
        this.baselineBalance = baselineBalance
        this.activityWindow = activityWindow
    }

    fun calculateBalance(): Money {
        return baselineBalance.plus(activityWindow.calculateBalance(id))
    }

    fun withdraw(money: Money, targetAccountId: AccountId): Boolean {
        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal = Activity(
            ownerAccountId = id,
            sourceAccountId = id,
            targetAccountId = targetAccountId,
            timestamp = LocalDateTime.now(),
            money = money
        )

        activityWindow.addActivity(withdrawal)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return (this.calculateBalance() - money).isPositive()
    }

    fun deposit(money: Money, sourceAccountId: AccountId): Boolean {
        val deposit = Activity(
            ownerAccountId = id,
            sourceAccountId = sourceAccountId,
            targetAccountId = id,
            timestamp = LocalDateTime.now(),
            money
        )
        activityWindow.addActivity(deposit)
        return true
    }

    fun getId(): AccountId {
        return id
    }
}

data class AccountId(
    val value: Long
)