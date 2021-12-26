package buckpal.domain

class ActivityWindow(
    activities: MutableList<Activity>
) {
    private val activities: MutableList<Activity>

    init {
        this.activities = activities
    }

    constructor(vararg activities: Activity) : this(activities.toMutableList())

    fun calculateBalance(accountId: AccountId): Money {
        val depositBalance = activities.filter { it.getTargetAccountId() == accountId }
            .map(Activity::getMoney)
            .fold(Money.ZERO) { acc, money -> acc.plus(money) }

        val withdrawalBalance = activities.filter { it.getSourceAccountId() == accountId }
            .map(Activity::getMoney)
            .fold(Money.ZERO) { acc, money -> acc.plus(money) }

        return depositBalance.minus(withdrawalBalance)
    }

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
}