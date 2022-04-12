fun main() {
    val typeCard = "Мир"
    val transferAmount = 50000
    val previousTransaction = 700

    val commission = calculationCommission(
        typeCard = typeCard,
        previousTransaction = previousTransaction,
        transferAmount = transferAmount
    )
    println("При переводе: $transferAmount копеек с карты: $typeCard, комиссия составит: $commission копеек")
}

fun calculationCommission(typeCard: String = "VK Pay", previousTransaction: Int = 0, transferAmount: Int): Int {
    val prefLimit = 75000
    val overrunLimit = prefLimit - previousTransaction - transferAmount
    val minCommissionForMastercardMaestro = 20
    val commissionForMastercardMaestro = 0.06 / 100
    val minCommissionForVisaMir = 35
    val commissionForVisaMir = 0.75 / 100
    val totalCommissionForVisaMir = transferAmount * commissionForVisaMir

    return when (typeCard) {
        "Mastercard", "Maestro" -> if (overrunLimit < 0) (kotlin.math.abs(overrunLimit) * commissionForMastercardMaestro + minCommissionForMastercardMaestro).toInt() else 0
        "Visa", "Мир" -> if (totalCommissionForVisaMir > minCommissionForVisaMir) totalCommissionForVisaMir.toInt() else minCommissionForVisaMir
        else -> 0
    }
}