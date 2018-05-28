import java.io.*
import Category



var mainMenuString = "1. Select Category\n2. Edit Category\n3. Create Category\n4. Delete Category\n"
var categories = ArrayList<Category>()

fun main(args: Array<String>)
{
//    var c = Category("CST 101")
//
//    c.addFlashCard("C++ is a ______ language?", "object-oriented")
//    c.addFlashCard("C is a _______ language?", "procedural language")
//    c.addFlashCard("Delphi is based on ______ language?", "Pascal/Algol")
//    categories.add(c)
//
//    c = Category("CST 116")
//    c.addFlashCard("What is the main entry point of a C++ program?", "int main()");
//    c.addFlashCard("What is an alternative to the IF/ELSE statement?", "switch()");
//    c.addFlashCard("What is return used for?", "return from function");
//    categories.add(c)
//
//    c = Category("CST 136")
//    c.addFlashCard("What can't C do?", "C is God")
//    c.addFlashCard("What can C++ do?", "OOP")
//    c.addFlashCard("Who can do it?", "Bjarne Stroustrup")
//    categories.add(c)


    //write_category_file("/Users/jshardy/OneDrive/Documents/Projects/StudyMonkey/src/categories.dat")
    open_category_file("/Users/jshardy/OneDrive/Documents/Projects/StudyMonkey/src/categories.dat")

    var answer = displayMenu(mainMenuString, '1', '4')

    when (answer) {
        1 -> displaySelectCategory()
        2 -> print("x == 2")    //edit
        else -> { // Note the block
            print("x is neither 1 nor 2")
        }
    }
}

fun displayMenu(menu: String, startIndex: Char, endIndex: Char) : Int
{
    var answer: String?
    do {
        print(menu)
        print(":")
        answer = readLine()

        if(answer != null)
            answer.trim()

1    } while(answer == null || answer[0].toChar() < startIndex || answer[0].toChar() > endIndex)

    return answer.toInt()
}

fun displaySelectCategory()
{
    var i: Int = 0
    var newString = ""

    categories.forEach({
        i++
        newString += i.toString() + " - " + it.categoryName + "\n"
    })

    val x = displayMenu(newString, '1', '3')
    gotocategory(categories[x])
}

fun gotocategory(category: Category)
{
    for(flashcard in category.flashCardList)
    {
        var newString = "\n" + flashcard.flashcard + "\n"
        var inserted = false
        var answer_array = ArrayList<String>()

        for (i in 1..3)
        {
            var rindex = category.rand()
            var whereAnswer = category.rand(0, 1)

            if(whereAnswer == 0 || inserted) {
                answer_array.add(category.flashCardList[rindex].answer)
                newString += i.toString() + ". " + category.flashCardList[rindex].answer + "\n"
            }
            else if(!inserted) {
                answer_array.add(flashcard.answer)
                newString += i.toString() + ". " + flashcard.answer + "\n"
                inserted = true
            }
        }
        var answer_index = displayMenu(newString, '1', '3')
        //remember to subtract one
        var correct_answer = flashcard.try_question(answer_array[answer_index - 1])

        if(correct_answer)
            println("Correct!!!")
        else
            println("Incorrect...")
    }
}

fun write_category_file(file: String)
{
    ObjectOutputStream(FileOutputStream(file)).writeObject(categories)
}

fun open_category_file(file: String)
{
    categories = ObjectInputStream(FileInputStream(file)).readObject() as ArrayList<Category>
}