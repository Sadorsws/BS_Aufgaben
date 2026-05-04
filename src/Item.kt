enum class ItemCategory {
    FOOD, TOY, OTHER
}

class Item (var category: ItemCategory = ItemCategory.OTHER) {
    var name = ""
    var amount = 5.0
    var energyImpact = 10
    var happinessImpact = 10
}