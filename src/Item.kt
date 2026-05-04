// Level 1, Aufgabe 5: Enum für die Item-Kategorien.
enum class ItemCategory {
    FOOD, TOY, OTHER
}


// Level 1, Aufgabe 5: Klasse Item mit allen geforderten Eigenschaften[cite: 1].
class Item(
    val name: String,
    val category: ItemCategory,
    var amount: Double = 1.0,
    val energyImpact: Int = 10,
    val happinessImpact: Int = 10
)