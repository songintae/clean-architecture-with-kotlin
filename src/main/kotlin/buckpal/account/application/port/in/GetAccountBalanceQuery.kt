package buckpal.account.application.port.`in`

import buckpal.account.domain.AccountId
import buckpal.account.domain.Money

interface GetAccountBalanceQuery {
    fun getAccountBalance(accountId: AccountId): Money
}