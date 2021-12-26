package buckpal.application.port.`in`

import buckpal.domain.AccountId
import buckpal.domain.Money

class SendMoneyCommand(
    sourceAccountId: AccountId,
    targetAccountId: AccountId,
    money: Money
) {
    val sourceAccountId: AccountId
    val targetAccountId: AccountId
    val money: Money

    init {
        this.sourceAccountId = sourceAccountId
        this.targetAccountId = targetAccountId
        assert(money.isGreaterThen(Money.ZERO)) { "Money must be greater than 0" }
        this.money = money
    }
}