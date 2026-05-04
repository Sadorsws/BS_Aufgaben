import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

fun main() {
    // Stage und Pet initialisieren
    val stage = Stage()
    val pet = Pet()

    // Level 3, Aufgabe 4 (Tipp): Das Game-Objekt wird erstellt und setzt damit
    // die Game.instance für globale Effekte (z.B. Kekse auf Stage erscheinen lassen).
    val game = Game(stage, pet)

    // Pet und seine Statusanzeige zur Stage hinzufügen
    stage.addActor(pet.actor)
    stage.addActor(pet.statusText)

    // ==========================================
    // Level 2, Aufgabe 4: Buttons für Aktivitäten
    // ==========================================

    // Activity-Objekte erstellen
    val activityRun = Activity("Laufen", energyImpact = -10, happinessImpact = 15)
    val activityBake = Activity("Kekse backen", energyImpact = -5, happinessImpact = 20)
    val activityFootball = Activity("Fußball spielen", energyImpact = -15, happinessImpact = 25)

    // Laufen-Button
    val buttonRun = Actor(Assets.EMPTY)
    buttonRun.text.content = "Laufen"
    buttonRun.x = 10
    buttonRun.y = 100
    buttonRun.reactionForMouseClick = { pet.doActivity(activityRun) } // Übergibt das Activity-Objekt an doActivity
    stage.addActor(buttonRun)

    // Backen-Button
    val buttonBake = Actor(Assets.EMPTY)
    buttonBake.text.content = "Kekse backen"
    buttonBake.x = 10
    buttonBake.y = 150
    buttonBake.reactionForMouseClick = { pet.doActivity(activityBake) }
    stage.addActor(buttonBake)

    // Fußball-Button
    val buttonFootball = Actor(Assets.EMPTY)
    buttonFootball.text.content = "Fußball spielen"
    buttonFootball.x = 10
    buttonFootball.y = 200
    buttonFootball.reactionForMouseClick = { pet.doActivity(activityFootball) }
    stage.addActor(buttonFootball)


    // ==========================================
    // Level 1, Aufgabe 6: Snacks und Spielzeug auf der Stage
    // ==========================================

    // Snacks (Kategorie FOOD) auf der Stage verteilen
    val actorSnacksList = listOf(
        Assets.snacks.BOWL, Assets.snacks.COFFEE, Assets.snacks.COOKIE2,
        Assets.snacks.SANDWICH, Assets.snacks.BURGER
    )

    for (snackAsset in actorSnacksList) {
        val snackActor = Actor(snackAsset)
        snackActor.x = Random.nextInt(200, 700)
        snackActor.y = Random.nextInt(50, 500)
        stage.addActor(snackActor)

        // Wenn angeklickt, erstelle Item und füge es hinzu
        snackActor.reactionForMouseClick = {
            val foodItem = Item(name = "Snack", category = ItemCategory.FOOD, energyImpact = 15, happinessImpact = 5)
            pet.addItem(foodItem)
            snackActor.visible = false // Snack verschwindet von der Stage
        }
    }

    // Spielsachen (Kategorie OTHER) auf der Stage verteilen (z.B. Ball, Ufo)[cite: 1]
    // Wir nutzen Pair<Asset, String> um direkt den richtigen Namen zuzuordnen
    val actorOtherList = listOf(
        Pair(Assets.misc.BALL, "Ball"),
        Pair(Assets.misc.UFO, "Ufo")
    )

    for (other in actorOtherList) {
        val otherAsset = other.first
        val otherName = other.second

        val otherActor = Actor(otherAsset)
        otherActor.x = Random.nextInt(200, 700)
        otherActor.y = Random.nextInt(50, 500)
        stage.addActor(otherActor)

        // Wenn angeklickt, erstelle Item und füge es dem Inventar hinzu[cite: 1]
        otherActor.reactionForMouseClick = {
            val toyItem = Item(name = otherName, category = ItemCategory.OTHER, energyImpact = 0, happinessImpact = 10)
            pet.addItem(toyItem)
            otherActor.visible = false // Spielzeug verschwindet in das Inventar
        }
    }
}