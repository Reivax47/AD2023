import kotlin.math.max
import kotlin.math.min

fun main() {

    fun chercheLycos(laListe: MutableList<lineMap>, value: Long): Long {
        var reponse = value
        val laLine = laListe.find { it.source <= value }
        if (laLine != null && laLine.source + laLine.range - 1 >= value) {
            reponse = laLine.destination + (value - laLine.source)
        }

        return reponse
    }

    fun part1(input: List<String>): Long {

        val seedToSoil = mutableListOf<lineMap>()
        val soilToFertilazer = mutableListOf<lineMap>()
        val fertilizerToWater = mutableListOf<lineMap>()
        val waterToLight = mutableListOf<lineMap>()
        val lightToTemperature = mutableListOf<lineMap>()
        val temperatureToHumidity = mutableListOf<lineMap>()
        val humidityToLocation = mutableListOf<lineMap>()

        val seeds = input[0].substringAfter("seeds: ").split(" ").map { it.toLong() }

        var i = 3
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            seedToSoil.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            soilToFertilazer.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            fertilizerToWater.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            waterToLight.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            lightToTemperature.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            temperatureToHumidity.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (i < input.size && input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            humidityToLocation.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }

        seedToSoil.sortByDescending { it.source }
        soilToFertilazer.sortByDescending { it.source }
        fertilizerToWater.sortByDescending { it.source }
        waterToLight.sortByDescending { it.source }
        lightToTemperature.sortByDescending { it.source }
        temperatureToHumidity.sortByDescending { it.source }
        humidityToLocation.sortByDescending { it.source }
        val location = seeds.map { itSeeds ->
            (
                    chercheLycos(
                        humidityToLocation,
                        chercheLycos(
                            temperatureToHumidity,
                            chercheLycos(
                                lightToTemperature,
                                chercheLycos(
                                    waterToLight,
                                    chercheLycos(
                                        fertilizerToWater,
                                        chercheLycos(soilToFertilazer, chercheLycos(seedToSoil, itSeeds))
                                    )
                                )
                            )
                        )
                    )
                    )
        }
        return location.min()

    }

    fun part2(input: List<String>): Long {

        val lesMaps = mutableListOf<MutableList<lineMap>>()
        val seedToSoil = mutableListOf<lineMap>()
        val soilToFertilazer = mutableListOf<lineMap>()
        val fertilizerToWater = mutableListOf<lineMap>()
        val waterToLight = mutableListOf<lineMap>()
        val lightToTemperature = mutableListOf<lineMap>()
        val temperatureToHumidity = mutableListOf<lineMap>()
        val humidityToLocation = mutableListOf<lineMap>()

        val seedsGenerator = input[0].substringAfter("seeds: ").split(" ").map { it.toLong() }
        var j = 0
        val lesSeeds = mutableListOf<unRange>()
        while (j < seedsGenerator.size - 1) {
            lesSeeds.add(unRange(seedsGenerator[j], seedsGenerator[j] + seedsGenerator[j + 1] - 1))
            j += 2
        }

        var i = 3
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            seedToSoil.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            soilToFertilazer.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            fertilizerToWater.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            waterToLight.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            lightToTemperature.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            temperatureToHumidity.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }
        i += 2
        while (i < input.size && input[i] != "") {
            val chiffres = input[i++].split(" ").map { it.toLong() }
            humidityToLocation.add(lineMap(chiffres[0], chiffres[1], chiffres[2]))
        }

        lesMaps.add(seedToSoil)
        lesMaps.add(soilToFertilazer)
        lesMaps.add(fertilizerToWater)
        lesMaps.add(waterToLight)
        lesMaps.add(lightToTemperature)
        lesMaps.add(temperatureToHumidity)
        lesMaps.add(humidityToLocation)

        lesMaps.forEach { uneMap ->
            val new = mutableListOf<unRange>()
            while (lesSeeds.isNotEmpty()) {
                val unSeed = lesSeeds[0]
                lesSeeds.removeAt(0)
                var indexFonction = 0
                var onSort = false
                while (!onSort && indexFonction < uneMap.size) {
                    val uneFunction = uneMap[indexFonction++]
                    val os = max(unSeed.debut, uneFunction.source)
                    val oe = min(unSeed.fin, uneFunction.source + uneFunction.range )

                    if (os < oe) {
                        new.add(
                            unRange(
                                os + uneFunction.destination - uneFunction.source,
                                oe + uneFunction.destination - uneFunction.source

                            )
                        )
                        if (os > unSeed.debut) {
                            lesSeeds.add(unRange(unSeed.debut, os))
                        }

                        if (unSeed.fin > oe) {
                            lesSeeds.add(unRange(oe, unSeed.fin))
                        }
                        onSort = true

                    }
                }
                if (!onSort) {
                    new.add(unSeed)

                }
            }

            lesSeeds.addAll(new)



        }


        lesSeeds.sortBy { it.debut }
        return lesSeeds[0].debut
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 46L)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

data class lineMap(val destination: Long, val source: Long, val range: Long)
data class unRange(var debut: Long, var fin: Long)