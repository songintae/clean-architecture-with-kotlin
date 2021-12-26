package buckpal.application

import buckpal.application.port.`in`.SendMoneyCommand
import buckpal.application.port.`in`.SendMoneyUseCase
import buckpal.application.port.out.AccountLock
import buckpal.application.port.out.LoadAccountPort
import buckpal.application.port.out.UpdateAccountStatePort
import org.springframework.transaction.annotation.Transactional

@Transactional
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val accountLock: AccountLock,
    private val updateAccountStatePort: UpdateAccountStatePort
) : SendMoneyUseCase {

    override fun sendMoney(command: SendMoneyCommand): Boolean {
        TODO("비즈니스 규칙 검증")
        TODO("모델 상태 조작")
        TODO("출력 값 변환")
    }
}