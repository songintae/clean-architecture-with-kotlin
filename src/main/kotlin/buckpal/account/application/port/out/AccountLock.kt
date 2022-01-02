package buckpal.account.application.port.out

import buckpal.account.domain.AccountId

interface AccountLock {
    fun lockAccount(id: AccountId)
    fun releaseAccount(id: AccountId)
}