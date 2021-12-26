package buckpal.domain

import java.math.BigInteger

data class Money(
    val amount: BigInteger
) {
    companion object {
        val ZERO: Money = valueOf(0)

        fun valueOf(value: Long): Money {
            return Money(BigInteger.valueOf(value))
        }
    }

    fun plus(value: Money): Money {
        return Money(this.amount.add(value.amount))
    }

    fun minus(value: Money): Money {
        return this.plus(value.negate())
    }

    fun negate(): Money {
        return Money(this.amount.negate())
    }

    fun isPositive(): Boolean {
        return amount >= BigInteger.ZERO
    }
}