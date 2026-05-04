class Activity {
    var energyImpact = 10
    val happinessImpact = 10
    val description = ""

    fun execute(pet: Pet ) {
        pet.happiness = happinessImpact
        pet.health.energy = energyImpact

    }

}