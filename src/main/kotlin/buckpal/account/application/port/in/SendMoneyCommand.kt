package buckpal.account.application.port.`in`

import buckpal.account.domain.AccountId
import buckpal.account.domain.Money

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