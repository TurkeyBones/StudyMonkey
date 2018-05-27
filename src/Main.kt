import java.io.*
import Category
import sun.plugin2.liveconnect.ArgumentHelper.writeObject



var mainMenuString = "1. Select Category\n2. Edit Category\n3. Create Category\n4. Delete Category\n"
var categories = ArrayList<Category>()

fun main(args: Array<String>)
{
//    var c = Category("CST 238")
//
//    c.addFlashCard("C++ is a ______ language?")
//    c.addFlashCard("C is a _______ language?")
//    c.addFlashCard("Delphi is based on ______ language?")
//    categories.add(c)
//
//    c = Category("CST 116")
//    c.addFlashCard("What is the main entry point of a C++ program?");
//    c.addFlashCard("What is an alternative to the IF/ELSE statement?");
//    c.addFlashCard("What is return used for?");
//    categories.add(c)
//
//    c = Category("CST 136")
//    c.addFlashCard("What can't C do?")
//    c.addFlashCard("What can C++ do?")
//    c.addFlashCard("Who can do it?")
//    categories.add(c)

    var answer = displayMenu(mainMenuString, '1', '4')

    //write_category_file("/Users/jshardy/OneDrive/Documents/Projects/StudyMonkey/src/categories.dat")
    open_category_file("/Users/jshardy/OneDrive/Documents/Projects/StudyMonkey/src/categories.dat")
    when (answer) {
        1 -> displaySelectCategory()
        2 -> print("x == 2")
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
    } while(answer == null || answer[0] < startIndex || answer[0] > endIndex)

    return answer.toInt()
}

fun displaySelectCategory()
{
    var i = 1
    var newString = ""

    categories.forEach({
        newString += i.toString() + " - " + it.categoryName + "\n"
        i++
    })
    displayMenu(newString.toString(), '1', i.toChar())
}

fun write_category_file(file: String)
{
    ObjectOutputStream(FileOutputStream(file)).writeObject(categories)
}

fun open_category_file(file: String)
{
    categories = ObjectInputStream(FileInputStream(file)).readObject() as ArrayList<Category>
}