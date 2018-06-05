import java.io.*
import Category

var categories = ArrayList<Category>()
//var datfilepath = "C:/Users/Panda/Desktop/SM/StudyMonkey/src/categories.dat"
var datfilepath = "C:/Users/Panda/Desktop/SM/StudyMonkey/src/"
var ExitGame = 0

fun main(args: Array<String>)
{
    GameLoop()
}

fun GameLoop()
{
    var mainMenuString = "1. Answer Questions\n2. Edit Category\n3. Create Category\n4. Delete Category\n5. Exit\n"
    open_category_file(datfilepath + "Categories.dat")
    do {
        var answer = displayMenu(mainMenuString, '1', '5')

        when (answer) {
            1 -> AnswerQuestions() //done
            2 -> editCategory()    //needs to be done
            3 -> CreateCategory() //done
            4 -> deleteCategory() //done
            5 -> ExitGameLoop() //done
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
    }while(ExitGame != 1)
}

fun ExitGameLoop()
{
    //uncomment for production and after filepathing is decided
    write_category_file(datfilepath + "categories.dat")
    ExitGame = 1
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

1    } while(answer == null || answer.isEmpty() || answer[0].toChar() < startIndex || answer[0].toChar() > endIndex)

    return answer.toInt()
}

fun AnswerQuestions()
{
    if(!categories.isEmpty()) {
        val catselect = displaySelectCategory()
        gotocategory(categories[catselect - 1])
    }
    else
    {
        print("\nNo Categories to draw questions from...Please Add some\n")
    }
}

fun CreateCategory()
{
    val CreateMenuString = "\n1. Create A Category\n2. List Current Categories\n3. Exit to main menu\n"
    val createCat = displayMenu(CreateMenuString, '1', '3')
    if (createCat == 1)
    {
        CreateCategoryInputMenu()
    }
    else if(createCat == 2)
    {
        displayCategories()
        CreateCategory()
    }
    else
    {
        return
    }
}
fun CreateCategoryInputMenu()
{
    val CreateCatCorrect = "\n1. Category Name Correct\n2. Re-enter Category Name\n3. Exit to Main Menu\n"
    print("Creating a new category: \n")
    print("Please enter category name: \n")
    var catname: String?
    catname = readLine()

    if(catname != null)
    {
        catname.trim()
        print(catname)
        print("\nIs this the correct name for the category? \n")
        var x = displayMenu(CreateCatCorrect, '1', '3')
        if(x == 1)
        {
            var c = Category(catname)
            categories.add(c)
            print("\nWould you like to add flash cards to the new category?\n")
            val createcatinputaddcardsquestion = "\n1. Add Card(s)\n2. Create Another Category\n3. Exit to main menu\n"
            var addcardsquestion = displayMenu(createcatinputaddcardsquestion, '1', '3')
            if(addcardsquestion == 1)
            {
                //print("\nCall Add Cards Function Here\n")
                addCardsInputMenu(c)
            }
            else if(addcardsquestion == 2)
            {
                CreateCategoryInputMenu()
            }
            else
            {
                return
            }
        }
        else if(x == 2)
        {
            CreateCategoryInputMenu()
        }
        else
        {
            return
        }

    }
}

fun deleteCategory()
{
    if(categories.size != 0)
    {
        print("\nWhich Category would you like to delete?: \n")
        var deleteindex = displaySelectCategory()

        var newString = ""

        newString += "\nAre you sure you want to delete: " + categories[deleteindex - 1].categoryName + "\n"
        print(newString)

        val confirmcatdeletestring = "\n1. Yes\n2. No\n3. Exit to main menu\n"
        var confirmdelete = displayMenu(confirmcatdeletestring, '1', '3')

        when (confirmdelete) {
            1 -> categories.removeAt(deleteindex - 1)
            2 -> deleteCategory()
            3 -> return
            else -> { // Note the block
                print("BAD INPUT RETURNING TO MAIN MENU")
            }
        }
    }
    else
    {
        print("\nCurrently zero categories thus no delete possible! \n")
    }
}

fun editCategory()
{
    if(!categories.isEmpty())
    {
        print("\nWhich Category would you like to edit?: \n")
        var editindex = displaySelectCategory()

        print("\nWhat type of editing for this category?: \n")
        val editselectstring = "\n1. Rename Category\n2. Add Card\n3. Delete Card\n4. Exit to main menu\n"

        var takeActionIndex = displayMenu(editselectstring, '1', '3')

        when (takeActionIndex) {
            1 -> renameCategory(categories[editindex - 1])
            2 -> addCardsInputMenu(categories[editindex - 1])
            3 -> deleteCard(categories[editindex - 1])
            4 -> return
            else -> { // Note the block
                print("BAD INPUT RETURNING TO MAIN MENU")
            }
        }

    }
    else
    {
        print("\nNo categories available to edit, please create some\n")
    }
}

fun renameCategory(category: Category)
{
    val categoryrename = "\n Please Enter The New Category Name: \n"
   print("\nCurrent Category Name: \n")
    print(category.categoryName)
    print("\n")

    var answer1: String?
    do {
        print(categoryrename)
        print(":")
        answer1 = readLine()

        if(answer1 != null)
            answer1.trim()

    } while(answer1 == null)

    print("\n Is the following name correct?: \n")
    var renamecatinfostring = "\n Name: " + answer1 + "\n"
    print(renamecatinfostring)

    var renamecatconfirmstring = "\n1. Yes that name is correct\n2. No that name is incorrect\n3. Exit to main menu\n"
    var renameCatConfirm = displayMenu(renamecatconfirmstring, '1', '3')

    when (renameCatConfirm) {
        1 -> category.categoryName = answer1
        2 -> renameCategory(category)
        3 -> return
        else -> { // Note the block
            print("BAD INPUT RETURNING TO MAIN MENU")
        }
    }

}

fun addCardsInputMenu(category: Category)
{
    print("\nCurrent Category and Cards: \n")
    print(category.categoryName)
    print(":\n")
    displayCardsUnderCategory(category)
    val addCardsInputMenu = "\n1. Add Card(s)\n2. Exit to main menu\n"
    var addcardsquestion = displayMenu(addCardsInputMenu, '1', '2')

    when (addcardsquestion) {
        1 -> addCard(category)
        2 -> return
        else -> { // Note the block
            print("BAD INPUT RETURNING TO MAIN MENU")
        }
    }
}

fun addCard(category: Category)
{
    val CreateCardQuestionString = "\n Please Enter The Card Question: \n"
    val CreateCardAnswerString = "\n Please Enter The Card Answer: \n"
    val CreateCardQuestionAndAnswerString = "\n1. Card is correct and add it.\n2. Re-Enter Card Info\n3. Exit to main menu\n"
    val CreateAnotherCardQuestion = "\n1. Add another card \n2. Exit to main menu\n"

    var answer1: String?
    do {
        print(CreateCardQuestionString)
        print(":")
        answer1 = readLine()

        if(answer1 != null)
            answer1.trim()

        } while(answer1 == null)

    var answer2: String?
    do {
        print(CreateCardAnswerString)
        print(":")
        answer2 = readLine()

        if(answer2 != null)
            answer2.trim()

    } while(answer2 == null)

   print("\n Is the following Question and Answer correct?: \n")
    var newcardinfostring = "\n Question: " + answer1 + " Answer: " + answer2 + "\n"
    print(newcardinfostring)
    var addCardConfirm = displayMenu(CreateCardQuestionAndAnswerString, '1', '3')

    when (addCardConfirm) {
        1 -> category.addFlashCard(answer1, answer2)
        2 -> addCard(category)
        3 -> return
        else -> { // Note the block
            print("BAD INPUT RETURNING TO MAIN MENU")
        }
    }

    print("\n Add another card?: \n")
    var addanothercard = displayMenu(CreateAnotherCardQuestion, '1', '2')

    when (addanothercard) {
        1 -> addCard(category)
        2 -> return
        else -> { // Note the block
            print("BAD INPUT RETURNING TO MAIN MENU")
        }
    }
}

fun deleteCard(category: Category)
{
    if(!category.flashCardList.isEmpty())
    {
        print("\nWhich Card would you like to delete?: \n")
        var deleteindex = displaySelectCard(category)
        var newString = ""

        newString += "\nAre you sure you want to delete: " + category.flashCardList[deleteindex - 1].toString() + "\n"

        val confirmcarddeletestring = "\n1. Yes\n2. No\n3. Exit to main menu\n"
        print("\nAre you sure you want to delete this card?\n")
        var confirmdelete = displayMenu(confirmcarddeletestring, '1', '3')

        when (confirmdelete) {
            1 -> category.flashCardList.removeAt(deleteindex - 1)
            2 -> deleteCard(category)
            3 -> return
            else -> { // Note the block
                print("BAD INPUT RETURNING TO MAIN MENU")
            }
        }
    }
    else
    {
        print("\nCategory currently doesn't contain any flash cards to delete \n")
    }
}

fun displaySelectCard(category: Category): Int
{
    var i: Int = 0
    var newString = ""

    for(flashcard in category.flashCardList)
    {
        i++
        newString += i.toString() + " - " + flashcard.flashcard + " Answer: " + flashcard.answer + "\n"
    }

    val x = displayMenu(newString,'0', i.toString()[0])
    return x
}

fun displayCardsUnderCategory(category: Category)
{
    print("\nCurrent Flash Cards: \n")
    for(flashcard in category.flashCardList)
    {
        var newString = "\n" + flashcard.flashcard + " Answer: " + flashcard.answer + "\n"
    }
}

fun displayCategories()
{
    var i: Int = 0
    var newString = ""

    categories.forEach({
        i++
        newString += i.toString() + " - " + it.categoryName + "\n"

    })
    print(newString)

}

fun displaySelectCategory(): Int
{
    var i: Int = 0
    var newString = ""

    categories.forEach({
        i++
        newString += i.toString() + " - " + it.categoryName + "\n"
    })

    val x = displayMenu(newString,'0', i.toString()[0])
    return x
    //gotocategory(categories[x-1])
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