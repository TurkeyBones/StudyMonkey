import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Random

data class FlashCard(val flashcard: String, val answer: String, var last_good_answer: LocalDate = LocalDate.now(), var last_answered_correctly: Boolean = true) : Serializable {
    fun try_question(possible_answer: String) : Boolean {
        if(possible_answer == answer) {
            last_good_answer = LocalDate.now()
            last_answered_correctly = true
        }
        else {
            last_answered_correctly = false
        }
        return last_answered_correctly
    }
}
data class Category(val category: String) : Serializable {
    var categoryName: String = category
    var flashCardList = ArrayList<FlashCard>()
    private var current_flash_card = 0
    private val random = Random()

    fun addFlashCard(flashcard: String, answer: String) {
        flashCardList.add(FlashCard(flashcard, answer))
    }

    fun removeFlashCard(flashcard: String) {
        flashCardList.forEach({
            if(it.flashcard == flashcard) {
                flashCardList.remove(it)
            }
        })
    }

    //this overrides the array index operator
    fun get(index: Int) : FlashCard {
        return flashCardList[index]
    }

    fun size() : Int {
        return flashCardList.size
    }

    fun rand(from: Int = 0, to: Int = size()) : Int {
        return random.nextInt(to - from) + from
    }
}