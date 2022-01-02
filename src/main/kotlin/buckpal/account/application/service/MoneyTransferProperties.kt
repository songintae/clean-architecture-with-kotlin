package buckpal.account.application.service

import buckpal.account.domain.Money

class MoneyTransferProperties(
    val maximumTransferThreshold: Money
) {
    constructor() : this(Money.valueOf(1_000_000L))
}