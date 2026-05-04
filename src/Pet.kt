import de.th_koeln.basicstage.Actor
import de.th_koeln.basicstage.Stage
import de.th_koeln.imageprovider.Assets
import kotlin.random.Random

class Pet (val stage: Stage = Stage()){

    val name = "Pet"
    val health = Health(50,80)
    var happiness = 20
    val actor = Actor(Assets.dog.HAPPY)
    val actorEnergy = Actor(Assets.EMPTY)
    val inventory = mutableListOf<Item>()

    val hungry: Boolean
    get() = health.energy < 20

    fun actorSettings(){
        actor.x = 200   //Random.nextInt(500)
        actor.y = 200   //Random.nextInt(500)
        actor.width = 200
        actor.height = 200

        actorEnergy.text.content = "Dog Healthy: ${health.energy}"
        actorEnergy.x = 100
        actorEnergy.y = 100
        actorEnergy.width = 200
        actorEnergy.height = 50
    }
    init {
        actorSettings()

        actorEnergy.animation.everyNsteps.timeSpan = 160
        actorEnergy.animation.everyNsteps.reactionForTimePassed = {
            lifeGoesOn()
        }
    }
    fun addItem(item: Item){
        inventory.add(item)
        happiness += item.happinessImpact
        if (item.category == ItemCategory.FOOD) {
            feed(item)
        }
        else{ inventory.add(item) }

    }
    fun removeItem(item: Item){
        inventory.remove(item)
        happiness -= item.happinessImpact
    }
    fun countItems():Int = inventory.size
    fun lifeGoesOn(){
        val deducation = Random.nextInt(10)
        if(health.energy - deducation >= 0){
            health.energy -= deducation
            actorEnergy.text.content = "Dog Healthy: ${health.energy}"
        }
        else{
            actorEnergy.text.content = "Game Over"
        }
    }

    fun feed(item: Item){
        happiness += item.happinessImpact

    }


    fun doActivity(){
        val activity = Activity()
        activity.execute(this)
    }

}