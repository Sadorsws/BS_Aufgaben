import de.th_koeln.basicstage.Stage


class Game(val stage: Stage, val pet: Pet) {

    // Level 3, Aufgabe 4 (Tipp): Companion Object macht Stage für Activities zugänglich[cite: 1].
    // Vorteil: Activities müssen die Stage nicht einzeln im Konstruktor übergeben bekommen.
    // Nachteil: Globale Zustände können zu schwer auffindbaren Bugs führen ("Spaghetti-Code").
    companion object {
        lateinit var instance: Game
            private set
    }

    init {
        instance = this
    }
}