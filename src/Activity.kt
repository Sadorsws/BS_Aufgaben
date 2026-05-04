
// Level 2, Aufgabe 3: Klasse Activity[cite: 1].
class Activity(
    val description: String,
    val energyImpact: Int = -10, // Aktivitäten kosten meist Energie
    val happinessImpact: Int = 20
) {
    // Level 2, Aufgabe 3: execute-Methode nimmt ein Pet entgegen[cite: 1].
    fun execute(pet: Pet) {
        // Level 3, Aufgabe 2 & 3: Prüfen, ob für Fußball ein Ball im Inventar ist. Wenn nicht -> Exception[cite: 1].
        if (description == "Fußball spielen" && !pet.hasItem("Ball")) {
            throw Exception("Um Fußball zu spielen, wird ein Ball im Inventar benötigt!")
        }

        // Werte anpassen
        pet.health.energy += energyImpact
        pet.happiness += happinessImpact

        // Level 3, Aufgabe 4: Verzweigung für Bühnen-Effekte über das Companion Object von Game[cite: 1].
        when (description) {
            "Kekse backen" -> {
                println("Effekt: Kekse erscheinen auf der Stage!")
                // Game.instance.stage.addActor(...)
            }
            "Laufen" -> {
                println("Effekt: Kodee bewegt sich hin und her!")
                pet.actor.x += 20
                pet.actor.x -= 20
            }
            "Fußball spielen" -> {
                println("Effekt: Der Ball fliegt über die Stage!")
            }
        }
    }
}