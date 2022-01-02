package buckpal.account.application.port.out

import buckpal.account.domain.Account
import buckpal.account.domain.AccountId
import java.time.LocalDateTime

interface LoadAccountPort {
    fun loadAccount(accountId: AccountId, baselineDate: LocalDateTime): Account
}