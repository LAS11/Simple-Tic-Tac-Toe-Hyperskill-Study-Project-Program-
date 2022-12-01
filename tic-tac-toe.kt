package tictactoe

enum class Status(val str: String) {
    X_WINS ("X wins"),
    O_WINS ("O wins"),
    DRAW ("Draw") 
}

fun printField(items: MutableList<Char>) {
    println("---------")
    println("| ${items[0]} ${items[1]} ${items[2]} |")
    println("| ${items[3]} ${items[4]} ${items[5]} |")
    println("| ${items[6]} ${items[7]} ${items[8]} |")
    println("---------")
}

fun getItemPos(x: Int, y: Int): Int {
    return when {
        x == 1 && y == 1 -> 0
        x == 1 && y == 2 -> 1
        x == 1 && y == 3 -> 2
        x == 2 && y == 1 -> 3
        x == 2 && y == 2 -> 4
        x == 2 && y == 3 -> 5
        x == 3 && y == 1 -> 6
        x == 3 && y == 2 -> 7
        x == 3 && y == 3 -> 8
        else -> {
            println("Coordinates should be from 1 to 3!")
            return -1
        }
    }
}

fun isAvaliable(coord: Int, items: MutableList<Char>): Boolean {
    if (items[coord] != '_') {
        println("This cell is occupied! Choose another one!")
        return false
    }
    return true
}

fun checkCoords(coords: String): Int {
    if (coords.length != 3 || !coords.first().isDigit() || !coords.last().isDigit()) {
        println("You should enter numbers!")
        return -1
    }
    
    val x = coords.first().digitToInt()
    val y = coords.last().digitToInt()
    return getItemPos(x, y)
}

fun selectWinner(items: MutableList<Char>): Char {
    if (items[0] == items[1] && items[1] == items[2] && items[0] != '_') {
        return (items[1])
    }
    if (items[3] == items[4] && items[4] == items[5] && items[3] != '_') {
        return (items[4])
    }
    if (items[6] == items[7] && items[7] == items[8] && items[6] != '_') {
        return (items[7])
    }
    if (items[2] == items[4] && items[4] == items[6] && items[2] != '_') {
        return (items[4])
    }
    if (items[0] == items[3] && items[3] == items[6] && items[0] != '_') {
        return (items[3])
    }
    if (items[1] == items[4] && items[4] == items[7] && items[1] != '_') {
        return (items[4])
    }
    if (items[2] == items[5] && items[5] == items[8] && items[2] != '_') {
        return (items[5])
    }
    if (items[0] == items[4] && items[4] == items[8] && items[0] != '_') {
        return (items[4])
    }
    return 'n'
}

fun isFieldFull(items: MutableList<Char>): Boolean {
    for (ch in items) {
        if (ch == '_') {
            return false
        }
    }
    return true
}

fun main() {

    var items = MutableList(9) {'_'}
    var coords: String
    var itemCoord: Int
    var moveForX = true

    while (!isFieldFull(items) && selectWinner(items) == 'n') {
        printField(items)
        do {
            coords = readln()
            itemCoord = checkCoords(coords)
        } while (itemCoord == -1 || !isAvaliable(itemCoord, items))
        items[itemCoord] = if (moveForX) 'X' else 'O'
        moveForX = !moveForX
    }
    
    printField(items)
    when (selectWinner(items)) {
        'X' -> print(Status.X_WINS.str)
        'O' -> print(Status.O_WINS.str)
        else -> print(Status.DRAW.str)
    }
}
