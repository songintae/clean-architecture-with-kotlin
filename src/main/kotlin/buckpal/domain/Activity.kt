package buckpal.domain

import java.time.LocalDateTime

class Activity(
    ownerAccountId: AccountId,
    sourceAccountId: AccountId,
    targetAccountId: AccountId,
    timestamp: LocalDateTime,
    money: Money
) {
    private val id: ActivityId?
    private val ownerAccountId: AccountId
    private val sourceAccountId: AccountId
    private val targetAccountId: AccountId
    private val timestamp: LocalDateTime
    private val money: Money

    init {
        this.id = null
        this.ownerAccountId = ownerAccountId
        this.sourceAccountId = sourceAccountId
        this.targetAccountId = targetAccountId
        this.timestamp = timestamp
        this.money = money
    }

    fun getTargetAccountId(): AccountId {
        return targetAccountId
    }

    fun getSourceAccountId(): AccountId {
        return sourceAccountId
    }

    fun getMoney(): Money {
        return money
    }
}

data class ActivityId(
    val value: Long
)