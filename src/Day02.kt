fun main() {
    fun part1(input: List<String>): Int {
        val maxGreen = 13
        val maxBlue = 14
        val maxRed = 12
        var reponse = 0
        var index = 1

        input.forEach { uneLigne ->
            var nbBlue = 0
            var nbRed = 0
            var ndGreen = 0
            var possible  = true
            val lesGrosMorceaux = uneLigne.split(";")
            lesGrosMorceaux.forEach { unTirage ->

                val tirage = unTirage.substringAfterLast(":")
                val lesPetits = tirage.split(",")

                lesPetits.forEach { un ->

                    if (un.indexOf("blue") != -1) {
                        val pos = un.indexOf("blue")
                        nbBlue = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (un.indexOf("red") != -1) {
                        val pos = un.indexOf("red")
                        nbRed = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (un.indexOf("green") != -1) {
                        val pos = un.indexOf("green")
                        ndGreen = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (ndGreen > maxGreen || nbBlue > maxBlue || nbRed > maxRed) {
                       possible = false

                    }
                }

            }

            if (possible) {
                reponse += index
            }
            index++
        }
        println()
        return reponse
    }

    fun part2(input: List<String>): Long {

        var reponse = 0L

        input.forEach { uneLigne ->

            var maxGreen = 0
            var maxBlue = 0
            var maxRed = 0
            val lesGrosMorceaux = uneLigne.split(";")
            lesGrosMorceaux.forEach { unTirage ->
                var nbBlue = 0
                var nbRed = 0
                var ndGreen = 0

                val tirage = unTirage.substringAfterLast(":")
                val lesPetits = tirage.split(",")

                lesPetits.forEach { un ->

                    if (un.indexOf("blue") != -1) {
                        val pos = un.indexOf("blue")
                        nbBlue = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (un.indexOf("red") != -1) {
                        val pos = un.indexOf("red")
                        nbRed = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (un.indexOf("green") != -1) {
                        val pos = un.indexOf("green")
                        ndGreen = un.subSequence(1, pos - 1).toString().toInt()
                    }
                    if (ndGreen > maxGreen) {
                        maxGreen = ndGreen
                    }
                    if (nbRed > maxRed) {
                        maxRed = nbRed
                    }
                    if (nbBlue > maxBlue) {
                        maxBlue = nbBlue
                    }
                }

            }

            reponse += maxRed*maxBlue*maxGreen

        }
        println()
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 2286L)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
