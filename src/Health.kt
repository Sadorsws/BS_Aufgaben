import kotlin.random.Random

class Health (var energy: Int,var fitness: Int){


    init {
        energy = Random.nextInt(500)
        fitness = Random.nextInt(100)
    }
}