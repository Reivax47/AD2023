import kotlin.math.abs

data class cellule(val x: Int, val y: Int, var value: Char, val distance: Int)

fun main() {
    val trouvees = mutableListOf<cellule>()

    var maxX = 0
    var maxY = 0

    val canLookNorth = listOf('S', '|', 'L', 'J')
    val canLookSouth = listOf('S', '|', '7', 'F')
    val canLookEast = listOf('S', '-', 'L', 'F')
    val canLookWest = listOf('S', '-', '7', 'J')

    fun trouveLeS(input: List<String>): cellule {

        var y = 0
        var x = 0

        for (i in input.indices) {
            if (input[i].contains('S')) {
                y = i
                x = input[i].indexOf('S')
                return cellule(x, y, 'S', 0)
            }
        }

        return cellule(-1, -1, 'Z', -1)
    }

    fun lesVoisines(depart: cellule, input: List<String>): MutableList<cellule> {

        val reponse = mutableListOf<cellule>()
        val x = depart.x
        val y = depart.y

        if (x - 1 >= 0 && canLookWest.any { it == depart.value } && canLookEast.any { it == input[y][x - 1] } && trouvees.none { it.x == x - 1 && it.y == y }) {
            reponse.add(cellule(x - 1, y, input[y][x - 1], depart.distance + 1))
        }

        if (x + 1 <= maxX && canLookEast.any { it == depart.value } && canLookWest.any { it == input[y][x + 1] } && trouvees.none { it.x == x + 1 && it.y == y }) {
            reponse.add(cellule(x + 1, y, input[y][x + 1], depart.distance + 1))
        }


        if (y - 1 >= 0 && canLookNorth.any { it == depart.value } && canLookSouth.any { it == input[y - 1][x] } && trouvees.none { it.x == x && it.y == y - 1 }) {
            reponse.add(cellule(x, y - 1, input[y - 1][x], depart.distance + 1))
        }


        if (y + 1 <= maxY && canLookSouth.any { it == depart.value } && canLookNorth.any { it == input[y + 1][x] } && trouvees.none { it.x == x && it.y == y + 1 }) {
            reponse.add(cellule(x, y + 1, input[y + 1][x], depart.distance + 1))
        }



        return reponse

    }

    fun part1(input: List<String>): Int {
        var reponse = 0
        maxX = input[0].length - 1
        maxY = input.size - 1
        val voisines = mutableListOf<cellule>()
        var depart = trouveLeS(input)
        trouvees.add(depart)
        voisines.addAll(lesVoisines(depart, input))

        while (voisines.isNotEmpty()) {
            trouvees.addAll(voisines)
            val temp = mutableListOf<cellule>()
            temp.addAll(voisines)
            voisines.clear()
            temp.forEach { une ->
                voisines.addAll(lesVoisines(une, input))
            }

        }

        trouvees.sortByDescending { it.distance }
        reponse = trouvees[0].distance
        return reponse
    }


    fun Char.mieux(): Char {
       return when (this) {

            '|' -> '║'
            '-' -> '═'
            'L' -> '╚'
            'J' -> '╝'
            '7' -> '╗'
            'F' -> '╔'
            '.' -> '.'
            'S' -> 'S'
           else -> {'Z'}
       }

    }

    fun part2(input: List<String>): Long {
        var reponse = 0L
        trouvees.clear()
        maxX = input[0].length - 1
        maxY = input.size - 1
        val voisines = mutableListOf<cellule>()
        var depart = trouveLeS(input)
        trouvees.add(depart)
        voisines.addAll(lesVoisines(depart, input))

        var liste = mutableListOf<Char>('|', 'L','J','7','F','-')
        voisines.forEach { une ->
            if (une.x == depart.x - 1) {
                liste = liste.filter { it in canLookWest }.toMutableList()
            }
            if (une.x == depart.x + 1) {
                liste = liste.filter { it in canLookEast }.toMutableList()
            }
            if (une.y == depart.y - 1) {
                liste = liste.filter { it in canLookNorth }.toMutableList()
            }
            if (une.y == depart.y + 1) {
                liste = liste.filter { it in canLookSouth }.toMutableList()
            }
        }

        depart.value = liste[0]
        while (voisines.isNotEmpty()) {
            trouvees.addAll(voisines)
            val temp = mutableListOf<cellule>()
            temp.addAll(voisines)
            voisines.clear()
            temp.forEach { une ->
                voisines.addAll(lesVoisines(une, input))
            }

        }

        val output = mutableListOf<String>()
        for (y in input.indices) {
            var ligne = "".padStart(maxX + 1, 'I')
            val lesGagnants = trouvees.filter { it.y == y }
            lesGagnants.forEach { un ->
                val charReplace = un.value.mieux()
                ligne = ligne.substring(0, un.x) + charReplace + ligne.substring(un.x + 1)
            }
            output.add(ligne)

        }
        output[depart.y] = output[depart.y].substring(0, depart.x) + depart.value.mieux() + output[depart.y].substring(depart.x + 1)

        input.forEach { it ->
            println(it)
        }
        println()
        output.forEach { it ->
            println(it)
        }

        val lesIkonVire = mutableListOf<cellule>()
        for (x in output[0].indices) {
            if (output[0][x] == 'I') {
                lesIkonVire.add(cellule(x, 0, 'I', -1))
            }
            if (output[maxY][x] == 'I') {
                lesIkonVire.add(cellule(x, maxY, 'I', -1))
            }
        }
        for (y in 1..<maxY) {
            if (output[y][0] == 'I') {
                lesIkonVire.add(cellule(0, y, 'I', -1))
            }
            if (output[y][maxX] == 'I') {
                lesIkonVire.add(cellule(maxX, y, 'I', -1))
            }

        }
        while (lesIkonVire.isNotEmpty()) {
            val lui = lesIkonVire[0]
            lesIkonVire.removeAt(0)
            val x = lui.x
            val y = lui.y

            if (x - 1 >= 0 && output[y][x - 1] == 'I' && lesIkonVire.none { it.x == x - 1 && it.y == y }) {
                lesIkonVire.add(cellule(x - 1, y, input[y][x - 1], -1))
            }
            if (x + 1 <= maxX && output[y][x + 1] == 'I' && lesIkonVire.none { it.x == x + 1 && it.y == y }) {
                lesIkonVire.add(cellule(x + 1, y, input[y][x + 1], -1))
            }


            if (y - 1 >= 0 && output[y - 1][x] == 'I' && lesIkonVire.none { it.x == x && it.y == y - 1 }) {
                lesIkonVire.add(cellule(x, y - 1, input[y - 1][x], -1))
            }
            if (y + 1 <= maxY && output[y + 1][x] == 'I' && lesIkonVire.none { it.x == x && it.y == y + 1 }) {
                lesIkonVire.add(cellule(x, y + 1, input[y + 1][x], -1))
            }

            output[y] = output[y].substring(0, lui.x) + '.' + output[y].substring(lui.x + 1)
        }

        println()
        output.forEach { it ->
            println(it)
        }

        for (y in output.indices) {
            for (x in output[y].indices) {
                if (output[y][x] == 'I') {
                    var nbUP = 0
                    var nbDown = 0
                    var nbCrossing = 0

                    for (posX in x + 1 until output[y].length) {
                        if (output[y][posX] == '║') {
                            nbCrossing ++

                        } else if (output[y][posX] == '╝' || output[y][posX] == '╚') {
                            nbUP++
                        } else if (output[y][posX] == '╔' || output[y][posX] == '╗') {
                            nbDown++
                        }

                    }
                    if (nbUP % 2 != 0 && nbDown % 2 != 0) {
                        nbCrossing++
                    }

                    if (nbCrossing % 2 == 0) {
                        output[y] = output[y].substring(0, x) + '.' + output[y].substring(x + 1)
                    }
                }
            }
        }

        output.forEach { it ->
            reponse += it.count { it == 'I' }
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day10_test")
    check(part1(testInput) == 8)

    testInput = readInput("Day10_test2")
    println("Test 2")
    part2(testInput).println()
    check(part2(testInput) == 4L)
    testInput = readInput("Day10_test3")
    println("Test 3")
    part2(testInput).println()
    check(part2(testInput) == 4L)
    testInput = readInput("Day10_test4")
    println("Test 4")
    part2(testInput).println()
    check(part2(testInput) == 8L)
    testInput = readInput("Day10_test5")
    println("Test 5")
    part2(testInput).println()
    check(part2(testInput) == 10L)
//    check(part2(testInput) == 2L)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
