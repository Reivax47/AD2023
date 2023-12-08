fun main() {


    fun chercheLeZ(
        destinationFinale: String,
        directions: List<String>,
        instructions: MutableMap<String, Map<String, String>>,
        reponse: Int
    ): Pair<Int, String> {
        var destinationFinale1 = destinationFinale
        var reponse1 = reponse
        var indexDextination = 0
        while (destinationFinale1[2] != 'Z') {
            var oussa = directions[indexDextination++]
            if (indexDextination >= directions.size) {
                indexDextination = 0
            }
            destinationFinale1 = instructions[destinationFinale1]?.get(oussa) ?: ""
            reponse1++
        }
        return Pair(reponse1, destinationFinale1)

    }

    fun part1(input: List<String>): Int {
        val directions = input[0].chunked(1)
        val instructions = mutableMapOf<String, Map<String, String>>()
        var index = 2
        while (index < input.size) {
            val line =
                input[index++].split("=").map { it.trim() }.map { it.replace("(", "") }.map { it.replace(")", "") }
            val destinations = line[1].split(",").map { it.trim() }
            instructions[line[0]] = mapOf("L" to destinations[0], "R" to destinations[1])
        }

        var destinationFinale = "AAA"
        return chercheLeZ(destinationFinale, directions, instructions, 0).first
    }

    fun gcd(a: Long, b: Long): Long {
        if (a == 0L)
            return b;
        return gcd(b % a, a);
    }

    fun lcm(a: Long, b: Long): Long {
        return (a / gcd(a,b))*b
    }
    fun part2(input: List<String>): Long {

        var reponse = 0L
        val directions = input[0].chunked(1)
        val instructions = mutableMapOf<String, Map<String, String>>()
        val lesDeparts = mutableListOf<String>()
        val lesArrivees = mutableListOf<Long>()
        var index = 2
        while (index < input.size) {
            val line =
                input[index++].split("=").map { it.trim() }.map { it.replace("(", "") }.map { it.replace(")", "") }
            val destinations = line[1].split(",").map { it.trim() }
            instructions[line[0]] = mapOf("L" to destinations[0], "R" to destinations[1])
            lesDeparts.add(line[0])

        }
        lesDeparts.removeIf { it[2] != 'A' }
        lesDeparts.forEach { unDepart ->
            lesArrivees.add(chercheLeZ(unDepart, directions, instructions, 0).first.toLong())
        }
        reponse = lesArrivees.reduce{un, deux -> lcm(un.toLong(), deux.toLong())}.toLong()
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    var testInput = readInput("Day08_test")
    check(part1(testInput) == 6)

    testInput = readInput("Day08_test2")
    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}

