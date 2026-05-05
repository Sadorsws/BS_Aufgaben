import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.basicstage.geoevents.IntersectionEventListener
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

fun main() {
//    val actorColaps = IntersectionEventListener()

    // Stage und Pet initialisieren
    val stage = Stage()
    val pet = Pet()

    //Pet Actor Eigenschaften
    pet.actor.x = 250
    pet.actor.y = 250
    pet.actor.width = 100
    pet.actor.height = 100

    //Pet staus Eigenschaften
    pet.statusText.x = 10
    pet.statusText.width = 100

    // Pet und seine Statusanzeige zur Stage hinzufügen
    stage.addActor(pet.actor)
    stage.addActor(pet.statusText)

    // Activity-Objekte erstellen
    val activityRun = Activity("Laufen", energyImpact = -10, happinessImpact = 15)
    val activityBake = Activity("Kekse backen", energyImpact = -5, happinessImpact = 20)
    val activityFootball = Activity("Fußball spielen", energyImpact = -15, happinessImpact = 25)

    // Laufen-Button
    val buttonRun = Actor(Assets.EMPTY)
    buttonRun.text.content = "Laufen"
    buttonRun.width = 100
    buttonRun.x = 10
    buttonRun.y = 100
    buttonRun.reactionForMouseClick = { pet.doActivity(activityRun) } // Übergibt das Activity-Objekt an doActivity
    stage.addActor(buttonRun)

    // Backen-Button
    val buttonBake = Actor(Assets.EMPTY)
    buttonBake.text.content = "Kekse backen"
    buttonBake.width = 100
    buttonBake.x = 10
    buttonBake.y = 150
    buttonBake.reactionForMouseClick = { pet.doActivity(activityBake) }
    stage.addActor(buttonBake)

    // Fußball-Button
    val buttonFootball = Actor(Assets.EMPTY)
    buttonFootball.text.content = "Fußball spielen"
    buttonFootball.width = 100
    buttonFootball.x = 10
    buttonFootball.y = 200
    buttonFootball.reactionForMouseClick = { pet.doActivity(activityFootball) }
    stage.addActor(buttonFootball)

    //Vierter Button(Bonuspunkt)
    val buttonvierter = Actor(Assets.EMPTY)
    buttonvierter.text.content = "Lernen"
    buttonvierter.width = 100
    buttonvierter.x = 100
    buttonvierter.y = 150


    //Vierter Button(Bonuspunkt)
    val buttonfuenf = Actor(Assets.EMPTY)
    buttonfuenf.text.content = "Ins Kino gehen"
    buttonfuenf.width = 100
    buttonfuenf.x = 100
    buttonfuenf.y = 200

    //Blumenliste
    val flowerlist = listOf(
        Actor(Assets.flowers.FLOWER1),
        Actor(Assets.flowers.FLOWER2),
        Actor(Assets.flowers.FLOWER3)
    )
    //Blumenliste anzeigen
    for (flower in flowerlist) {
        val random1 = Random.nextInt(100,350)
        flower.x = random1
        flower.y = random1
        stage.addActor(flower)
    }

    // Snacksliste (Kategorie FOOD) auf der Stage verteilen
    val actorSnacksList = listOf(
        Assets.snacks.BOWL, Assets.snacks.COFFEE, Assets.snacks.COOKIE2,
        Assets.snacks.SANDWICH, Assets.snacks.BURGER
    )

    //Snackliste Ausgabe auf der Stage
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


        otherActor.reactionForMouseClick = {
            val toyItem = Item(name = otherName, category = ItemCategory.OTHER, energyImpact = 0, happinessImpact = 10)
            pet.addItem(toyItem)
            otherActor.visible = false // Spielzeug verschwindet in das Inventar
        }
    }
}