fun main() {
    val output = mutableListOf<String>()
    data class Galaxie(val x: Int, val y: Int, val numero: Int)
    data class Distance(val un : Galaxie, val deux :Galaxie, var distance : Int )

    fun calculeDistance(un: Galaxie, deux: Galaxie): Int {

        return 0
    }
    fun part1(input: List<String>): Int {
        var reponse = 0

        val ciel = mutableListOf<Galaxie>()
        val lesDistance = mutableListOf<Distance>()
        input.forEach { it ->
            output.add(it)
            if (it.none { it == '#' }) {
                output.add(it)
            }
        }
        var x = 0
        while (x < output[0].length) {
            var trouve = false
            for (y in output.indices) {
                if (output[y][x] == '#') {
                    trouve = true
                }
            }

            if (!trouve) {
                for (y in output.indices) {
                    output[y] = output[y].substring(0, x - 1) + '.' + output[y].substring(x - 1)

                }
                x++
            }
            x++
        }
        output.forEach {
            println(it)
        }

        var numero = 1
        for (y in output.indices) {
            for (x in output[y].indices) {
                if (output[y][x] == '#') {
                    ciel.add(Galaxie(x,y,numero++))
                }

            }
        }
        ciel.forEach { uneGalaxie ->
            ciel.filter { it.numero > uneGalaxie.numero }.forEach { uneTrouvee ->
                lesDistance.add(Distance(uneGalaxie, uneTrouvee, -1))

            }
        }
        lesDistance.forEach { une ->
            une.distance = calculeDistance(une.un, une.deux)

        }
        reponse = lesDistance.sumOf { it.distance }
        return reponse
    }

    fun part2(input: List<String>): Int {
        var reponse = 0

        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day11_test")
    check(part1(testInput) == 374)

//    testInput = readInput("Day01_test2")
//    check(part2(testInput) == 281)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}
