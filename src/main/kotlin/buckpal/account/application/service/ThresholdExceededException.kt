package buckpal.account.application.service

import buckpal.account.domain.Money

class ThresholdExceededException : RuntimeException {
    constructor(
        message: String
    ) : super(message)

    constructor(
        message: String,
        cause: Throwable
    ) : super(message = message, cause = cause)

    constructor(
        threshold: Money,
        actual: Money
    ) : super("Maximum threshold for transferring money exceeded: tried to transfer $actual but threshold is $threshold")
}