package buckpal.account.application.service

import buckpal.account.application.port.`in`.GetAccountBalanceQuery
import buckpal.account.application.port.out.LoadAccountPort
import buckpal.account.domain.AccountId
import buckpal.account.domain.Money
import java.time.LocalDateTime

class GetAccountBalanceService(
    private val loadAccountPort: LoadAccountPort
) : GetAccountBalanceQuery {
    override fun getAccountBalance(accountId: AccountId): Money {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
            .calculateBalance();
    }
}