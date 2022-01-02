package buckpal.account.application.service

import buckpal.account.application.port.`in`.SendMoneyCommand
import buckpal.account.application.port.`in`.SendMoneyUseCase
import buckpal.account.application.port.out.AccountLock
import buckpal.account.application.port.out.LoadAccountPort
import buckpal.account.application.port.out.UpdateAccountStatePort
import buckpal.common.UseCase
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@UseCase
@Transactional
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val accountLock: AccountLock,
    private val updateAccountStatePort: UpdateAccountStatePort,
    private val moneyTransferProperties: MoneyTransferProperties
) : SendMoneyUseCase {

    override fun sendMoney(command: SendMoneyCommand): Boolean {
        checkThreshold(command)

        val baselineDate = LocalDateTime.now().minusDays(10)

        val sourceAccount = loadAccountPort.loadAccount(
            command.sourceAccountId,
            baselineDate
        )

        val targetAccount = loadAccountPort.loadAccount(
            command.targetAccountId,
            baselineDate
        )

        accountLock.lockAccount(sourceAccount.getId())
        if (!sourceAccount.withdraw(command.money, targetAccount.getId())) {
            accountLock.releaseAccount(sourceAccount.getId())
            return false
        }

        accountLock.lockAccount(targetAccount.getId())
        if (!targetAccount.deposit(command.money, sourceAccount.getId())) {
            accountLock.releaseAccount(sourceAccount.getId())
            accountLock.releaseAccount(targetAccount.getId())
        }

        updateAccountStatePort.updateActivities(sourceAccount)
        updateAccountStatePort.updateActivities(targetAccount)

        accountLock.releaseAccount(sourceAccount.getId())
        accountLock.releaseAccount(targetAccount.getId())

        return true
    }

    private fun checkThreshold(command: SendMoneyCommand) {
        if (command.money > moneyTransferProperties.maximumTransferThreshold) {
            throw ThresholdExceededException(moneyTransferProperties.maximumTransferThreshold, command.money);
        }
    }
}