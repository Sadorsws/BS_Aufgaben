import de.th_koeln.basicstage.Actor
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

// Level 1, Aufgabe 1: Definition der Klasse Pet[cite: 1].
class Pet(val actor: Actor = Actor(Assets.dog.HAPPY)) {

    var name: String = "Kodee"

    // Level 1, Aufgabe 2: health ist nun ein Health-Objekt[cite: 1].
    var health: Health = Health()

    // Happiness kann von außen gelesen, aber idealerweise nur durch Methoden verändert werden.
    var happiness: Int = 50

    // Level 1, Aufgabe 3: Actor für die Textanzeige[cite: 1].
    val statusText = Actor(Assets.EMPTY)

    // Level 1, Aufgabe 5: Privates Inventar (Sichtbarkeit: Level 2, Aufgabe 5)[cite: 1].
    private val inventory = mutableListOf<Item>()

    // Level 3, Aufgabe 5: Speichert die letzte Aktivität, um Langeweile zu simulieren[cite: 1].
    private var lastActivity: String? = null

    // Level 2, Aufgabe 2: Berechnete Eigenschaft (wahr, wenn Energie < 20)[cite: 1].
    val hungry: Boolean
        get() = health.energy < 20

    init {
        // Initiale UI-Anpassung
        statusText.x = 50
        statusText.y = 50
        updateUI()

        // Level 1, Aufgabe 4 & Level 3, Aufgabe 1: Zeitschritt-Logik (40 Steps = 1 Sekunde)[cite: 1].
        statusText.animation.everyNsteps.timeSpan = 40
        statusText.animation.everyNsteps.reactionForTimePassed = {
            lifeGoesOn() // Ruft jede Sekunde (bzw. Spiel-Minute) lifeGoesOn auf
        }
    }

    // Level 3, Aufgabe 1: minutesAwake mit Backing-Field und Seiteneffekt[cite: 1].
    var minutesAwake: Int = 0
        set(value) {
            field = value
            // Seiteneffekt: Energie sinkt alle 10 Minuten[cite: 1].
            if (field > 0 && field % 10 == 0) {
                health.energy -= 5
                updateUI()
            }
        }

    // Level 3, Aufgabe 1: hoursAwake greift in get/set auf minutesAwake zu[cite: 1].
    var hoursAwake: Int
        get() = minutesAwake / 60
        set(value) {
            minutesAwake = value * 60
        }


    // Level 1, Aufgabe 3: Reduziert Energie zufällig[cite: 1].
    private fun lifeGoesOn() {
        minutesAwake += 1 // Level 3, A1: 1 Spiel-Minute verstreicht[cite: 1].

        if (Random.nextBoolean()) { // Zufällige Wahrscheinlichkeit
            health.energy -= Random.nextInt(1, 5)
        }
        updateUI()
    }

    // Level 1, Aufgabe 5 & Level 2, Aufgabe 1: Items hinzufügen[cite: 1].
    fun addItem(item: Item) {
        if (item.category == ItemCategory.FOOD) {
            // Wenn es Essen ist, sofort verfüttern und Redundanz vermeiden[cite: 1].
            try {
                feed(item)
            } catch (e: Exception) {
                println("Fehler beim Essen: ${e.message}")
            }
        } else {
            inventory.add(item)
            happiness += item.happinessImpact
            updateUI()
            println("${item.name} wurde zum Inventar hinzugefügt.")
        }
    }

    // Level 1, Aufgabe 5: Item entfernen[cite: 1].
    fun removeItem(item: Item) {
        if (inventory.remove(item)) {
            happiness -= item.happinessImpact
            updateUI()
        }
    }

    // Level 1, Aufgabe 5: Items zählen[cite: 1].
    fun countItems(): Int = inventory.size

    // Level 3, Aufgabe 2: Prüft, ob ein Item mit bestimmtem Namen existiert[cite: 1].
    fun hasItem(itemName: String): Boolean {
        return inventory.any { it.name.equals(itemName, ignoreCase = true) }
    }

    // Level 2, Aufgabe 1 & Level 3, Aufgabe 3: Methode feed wirft Exception bei Non-Food[cite: 1].
    fun feed(item: Item) {
        if (item.category != ItemCategory.FOOD) {
            throw Exception("Dieses Item ist kein FOOD und kann nicht gegessen werden!")
        }
        health.energy += item.energyImpact
        happiness += item.happinessImpact
        updateUI()
        println("${item.name} wurde gegessen! Energie und Happiness steigen.")
    }

    // Level 2, Aufgabe 3 & Level 3, Aufgabe 5: Führt Aktivität aus[cite: 1].
    fun doActivity(activity: Activity) {
        try { // Level 3, Aufgabe 3: try-catch für Ausnahmesituationen[cite: 1].

            // Level 3, Aufgabe 5: Strafe für doppelte Aktivität[cite: 1].
            if (lastActivity == activity.description) {
                val penalty = Random.nextInt(10, 31) // Zufallswert zwischen 10 und 30
                happiness -= penalty
                println("Das ist langweilig geworden! Happiness sinkt um $penalty.")
            }

            activity.execute(this) // Führt Level 2, A3 aus[cite: 1].
            lastActivity = activity.description
            updateUI()

        } catch (e: Exception) {
            println("Aktivität '${activity.description}' fehlgeschlagen: ${e.message}")
        }
    }

    // Hilfsmethode, um den Text-Actor zentral zu aktualisieren (Level 1, A3 & A5)[cite: 1].
    private fun updateUI() {
        statusText.text.content =
            "Energy: ${health.energy} | Happy: $happiness | Hungry: $hungry | Time awake: $minutesAwake min"
    }
}