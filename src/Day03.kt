fun main() {

    fun checkSymbole(input: List<String>, x1: Int, x2: Int, y: Int): Boolean {

        if (y < 0 || y >= input.size) {
            return false
        }
        val debut = if (x1 < 0) 0 else x1
        val fin = if (x2 >= input[0].length) input[0].length else x2


        val test = input[y].subSequence(debut, fin).filter { !it.isDigit() && it != '.' }

        return test.isNotEmpty()
    }

    fun symbole(input: List<String>, posY: Int, posXDebut: Int, posXFin: Int): Boolean {
        val xDebut = posXDebut - 1
        val xFin = posXFin + 1

        for (i in posY - 1..posY + 1) {
            if (checkSymbole(input, xDebut, xFin, i)) {
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {

        var reponse = 0
        val largeur = input[0].length
        for (posY in 0 until input.size) {
            var posXDebut = 0

            while (posXDebut < largeur) {

                if (input[posY][posXDebut].isDigit()) {
                    var posXfin = posXDebut
                    while (posXfin < largeur && input[posY][posXfin].isDigit()) {
                        posXfin++
                    }
                    if (symbole(input, posY, posXDebut, posXfin)) {
                        reponse += input[posY].subSequence(posXDebut, posXfin).toString().toInt()
                    }
                    posXDebut = posXfin
                } else {
                    posXDebut++
                }
            }
        }
        return reponse
    }

    fun ints(
        x: Int,
        input: List<String>,
        y: Int
    ): MutableList<Int> {
        val reponse = mutableListOf<Int>()
        var posX = x - 1
        while (posX >= 0 && input[y][posX].isDigit()) {
            posX--
        }
        var valeur = input[y].subSequence(posX + 1, x).toString()
        if (valeur != "") {
            reponse.add(valeur.toInt())
        }

        posX = x + 1
        while (posX < input[0].length && input[y][posX].isDigit()) {
            posX++
        }
        valeur = input[y].subSequence(x +1, posX ).toString()
        if (valeur != "") {
            reponse.add(valeur.toInt())
        }
        return reponse
    }

    fun listAdjacent(input: List<String>, x: Int, y: Int): MutableList<Int> {
        val reponse = ints(x, input, y)

        var posY = y - 1
        if (posY >= 0) {
            if (!input[posY][x].isDigit()) {
                reponse.addAll(ints(x,input,posY))
            } else {
                var debX = x
                var finX = x
                while (debX > 0 && input[posY][debX].isDigit()) {
                    debX --
                }
                while (finX < input[0].length && input[posY][finX].isDigit()) {
                    finX ++
                }
                val valeur = input[posY].subSequence(debX +1, finX)
                if (valeur != "") {
                    reponse.add(valeur.toString().toInt())
                }
            }
        }

        posY = y + 1
        if (posY < input.size) {
            if (!input[posY][x].isDigit()) {
                reponse.addAll(ints(x,input,posY))
            }else {
                var debX = x
                var finX = x
                while (debX > 0 && input[posY][debX].isDigit()) {
                    debX --
                }
                while (finX < input[0].length && input[posY][finX].isDigit()) {
                    finX ++
                }
                val valeur = input[posY].subSequence(debX + 1, finX)
                if (valeur != "") {
                    reponse.add(valeur.toString().toInt())
                }
            }
        }
        return reponse
    }

    fun produitAdjacent(input: List<String>, x: Int, y: Int): Int {

        val laListe = listAdjacent(input, x, y)
        if ( laListe.size != 2) {
            return 0
        }
        return laListe[0] * laListe[1]
    }

    fun part2(input: List<String>): Long {

        var reponse = 0L
        val lesAsterisques = mutableListOf<Pair<Int, Int>>()

        for (y in 0 until input.size) {
            var x = input[y].indexOf('*')
            while (x != -1) {
                lesAsterisques.add(Pair(y, x))
                x = input[y].indexOf('*', x + 1)

            }

        }

        lesAsterisques.forEach { un ->
            reponse += produitAdjacent(input, un.second, un.first)
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 467835L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
