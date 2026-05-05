// Level 2, Aufgabe 3: Klasse Activity[cite: 1].
open class Activity(
    val description: String,
    val energyImpact: Int = -10, // Aktivitäten kosten meist Energie
    val happinessImpact: Int = 20
) {
    // Level 2, Aufgabe 3: execute-Methode nimmt ein Pet entgegen[cite: 1].
    open fun execute(pet: Pet) {
        // Level 3, Aufgabe 2 & 3: Prüfen, ob für Fußball ein Ball im Inventar ist. Wenn nicht -> Exception[cite: 1].
        if (description.contentEquals("Fußball") && !pet.hasItem("Ball")) {
            throw Exception("Um Fußball zu spielen, wird ein Ball im Inventar benötigt!")
        }

        // Werte anpassen
        pet.health.energy += energyImpact
        pet.happiness += happinessImpact
    }
}

class BakeCookies() : Activity("Kekse backen", -5, 20) {
    override fun execute(pet: Pet) {
        super.execute(pet) // Führt Standard-Verrechnung aus
        val keks = Item("Keks", ItemCategory.FOOD, 1.0, 5, 5)
        pet.addItem(keks) // Spezifische Logik: Item hinzufügen
    }
}
// Unterklasse für Laufen
class Running() : Activity("Laufen", -15, 10) {
    override fun execute(pet: Pet) {
        super.execute(pet)
    }
}

// Unterklasse für Fußball spielen
class PlayFootball() : Activity("Fußball spielen", -20, 30) {
    override fun execute(pet: Pet) {
        // Spezifische Logik: Bedingte Ausführung nur mit Ball
        if (pet.hasItem("Ball")) {
            super.execute(pet)
            println("Tor!")
        } else {
            println("Kein Ball vorhanden – Fußball spielen nicht möglich.")
        }
    }
}