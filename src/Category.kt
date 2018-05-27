import java.io.Serializable

data class Category(val category: String) : Serializable {
    var categoryName: String = category
    private var flashCardList = ArrayList<String>()

    fun addFlashCard(flashcard: String) {
        flashCardList.add(flashcard);
    }

    fun removeFlashCard(flashcard: String) {
        flashCardList.remove(flashcard);
    }

    //this overrides the array index operator
    fun get(index: Int) : String
    {
        return flashCardList[index]
    }
}