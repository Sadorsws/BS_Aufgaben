import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.imageprovider.Assets
import de.th_koeln.imageprovider.Assets.Companion.snacks
import kotlin.random.Random

fun main() {
    val stage = Stage()

    val dog = Pet(stage)
    stage.addActor(dog.actor)
    stage.addActor(dog.actorEnergy)

    fun ausfuerung() {

        val item1 = Item()
        val item2 = Item()
        val item3 = Item()


        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
        dog.addItem(item1)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
        dog.addItem(item2)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
        dog.addItem(item3)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")

        dog.removeItem(item3)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
        dog.removeItem(item2)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
        dog.removeItem(item1)
        println("Item in all: ${dog.countItems()}")
        println("HappinesImpact: ${dog.happiness}")
    }
    // ausfuerung()

    val actorSnacksList = listOf(
        Assets.snacks.BOWL,
        Assets.snacks.COFFEE,
        Assets.snacks.COOKIE2,
        Assets.snacks.SANDWICH,
        Assets.snacks.BURGER
    )
    for (snacks in actorSnacksList) {

        val item = Item(ItemCategory.FOOD)


        val snackActor = Actor(snacks)
        snackActor.x = Random.nextInt(500)
        snackActor.y = Random.nextInt(500)
        stage.addActor(snackActor)


        snackActor.reactionForMouseClick = {
            dog.addItem(item)
            snackActor.visible = false
            println("HappinesImpact: ${dog.happiness}")
        }
    }

    val actorOtherList = listOf(
        Assets.misc.UFO,
        Assets.misc.BALL,
        Assets.misc.FLAG,
        Assets.misc.BASKET
    )
    for (other in actorOtherList) {

        val item = Item(ItemCategory.OTHER)


        val otherActor = Actor(other)
        otherActor.x = Random.nextInt(500)
        otherActor.y = Random.nextInt(500)
        stage.addActor(otherActor)


        otherActor.reactionForMouseClick = {
            dog.addItem(item)
            otherActor.visible = false
            println("HappinesImpact: ${dog.happiness}")
        }
    }

    val ButtonRun = Actor(Assets.EMPTY)
    ButtonRun.text.content = "Laufen"
    ButtonRun.x = 0
    ButtonRun.y = 0
    ButtonRun.width = 100
    ButtonRun.height = 100
    val Buttoncook = Actor(Assets.EMPTY)
    Buttoncook.text.content = "Kekse backen"
    Buttoncook.x = 0
    Buttoncook.y = 100
    Buttoncook.width = 100
    Buttoncook.height = 100
    val Buttonfootball = Actor(Assets.EMPTY)
    Buttonfootball.text.content = "Fußball spielen"
    Buttonfootball.x = 0
    Buttonfootball.y = 200
    Buttonfootball.width = 100
    Buttonfootball.height = 100

    stage.addActor(ButtonRun)
    stage.addActor(Buttoncook)
    stage.addActor(Buttonfootball)

    ButtonRun.reactionForMouseClick = {
        dog.doActivity()
    }
    Buttoncook.reactionForMouseClick = {
        dog.doActivity()
    }
    Buttonfootball.reactionForMouseClick = {
        dog.doActivity()
    }
}