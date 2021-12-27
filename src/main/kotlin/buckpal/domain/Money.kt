package buckpal.domain

import java.math.BigInteger

data class Money(
    private val amount: BigInteger
) {
    companion object {
        val ZERO: Money = valueOf(0)

        fun valueOf(value: Long): Money {
            return Money(BigInteger.valueOf(value))
        }
    }

    operator fun plus(value: Money): Money {
        return Money(this.amount + value.amount)
    }

    operator fun minus(value: Money): Money {
        return this + (-value)
    }

    private operator fun unaryMinus(): Money {
        return Money(-this.amount)
    }

    fun isPositive(): Boolean {
        return amount >= BigInteger.ZERO
    }

    fun isGreaterThen(value: Money): Boolean {
        return this.amount > value.amount
    }
}