fun main() {


    fun part1(input: List<String>): Int {

        var reponse = 0
        val game = mutableListOf<main>()

        input.forEach { ligne ->
            val l = ligne.split(" ")
            game.add(main(l[0], l[1].toInt(), -1, -1))
        }

        game.forEach { unMain ->
            val lejeu = mutableMapOf<Char, Int>()
            unMain.main.forEach { carac ->
                if (lejeu[carac] == null) {
                    lejeu[carac] = 1
                } else {
                    lejeu[carac] = lejeu[carac]!! + 1
                }
            }


            if (lejeu.size == 1) {
                unMain.type = 7
            }


            if (lejeu.size == 2) {
                if (lejeu.filterValues { it == 4 }.isNotEmpty()) {
                    unMain.type = 6
                } else {
                    unMain.type = 5
                }
            }


            if (lejeu.size == 3) {

                if (lejeu.filterValues { it == 2 }.isEmpty()) {
                    unMain.type = 4
                } else {
                    unMain.type = 3
                }
            }

            if (lejeu.size == 4) {

                unMain.type = 2

            }
            if (lejeu.size == 5) {
                unMain.type = 1
            }
        }
        game.sortWith<main>(object : Comparator<main> {
            override fun compare(m1 : main, m2 : main): Int {
                if (m1.type > m2.type) {
                    return 1
                }
                if (m1.type < m2.type) {
                    return -1
                }
                val valeurs = mapOf('2' to 1, '3' to 2 , '4' to 3, '5' to 4 , '6' to 5,'7' to 6, '8' to 7, '9' to 8, 'T' to 9, 'J' to 10, 'Q' to 11, 'K' to 12 , 'A' to 13)
                var index = 0
                while (index < m1.main.length && m1.main[index] == m2.main[index]) {
                    index ++
                }
                if (valeurs[m1.main[index]]!! > valeurs[m2.main[index]]!!) {
                    return 1
                }

                return -1
            }
        })
        game.forEachIndexed { index, main ->
            reponse += main.pari * (index + 1)

        }
        return reponse
    }


    fun part2(input: List<String>): Long {

        var reponse = 0L
        val game = mutableListOf<main>()

        input.forEach { ligne ->
            val l = ligne.split(" ")
            game.add(main(l[0], l[1].toInt(), -1, -1))
        }
        var maxJ = 0
        game.forEach { unMain ->
            val lejeu = mutableMapOf<Char, Int>()
            unMain.main.forEach { carac ->
                if (lejeu[carac] == null) {
                    lejeu[carac] = 1
                } else {
                    lejeu[carac] = lejeu[carac]!! + 1
                }
            }

            val nbJ = if (lejeu['J'] == null) 0 else lejeu['J']
            if (nbJ != null) {
                unMain.nbJ = nbJ
            }
            when (nbJ) {
                0 -> {
                    if (lejeu.size == 1) {
                        unMain.type = 7
                    }
                    if (lejeu.size == 2) {
                        if (lejeu.filterValues { it == 4 }.isNotEmpty()) {
                            unMain.type = 6
                        } else {
                            unMain.type = 5
                        }
                    }

                    if (lejeu.size == 3) {

                        if (lejeu.filterValues { it == 2 }.isEmpty()) {
                            unMain.type = 4
                        } else {
                            unMain.type = 3
                        }
                    }

                    if (lejeu.size == 4) {

                        unMain.type = 2

                    }
                    if (lejeu.size == 5) {
                        unMain.type = 1
                    }
                }
                1 -> {
                    if (lejeu.size == 5) {
                        unMain.type = 2
                    }
                    if (lejeu.size == 4) {
                        unMain.type = 4
                    }

                    if (lejeu.size == 3 && lejeu.filterValues { it == 3 }.isNotEmpty()) {
                        unMain.type = 6
                    }
                    if (lejeu.size == 3 && lejeu.filterValues { it == 3 }.isEmpty()) {
                        unMain.type = 5
                    }

                    if (lejeu.size == 2) {
                        unMain.type = 7
                    }
                }
                2 -> {
                    if (lejeu.size == 2) {
                        unMain.type = 7
                    }
                    if (lejeu.size == 3) {
                        unMain.type = 6
                    }
                    if (lejeu.size == 4) {
                        unMain.type = 4
                    }
                }
                3 -> {
                    if (lejeu.size == 3) {
                        unMain.type = 6
                    } else if (lejeu.size == 2) {
                        unMain.type = 7
                    }
                }
                4 -> {
                    unMain.type = 7
                }
                5 -> {
                    unMain.type = 7
                }
            }

        }

        game.sortWith<main>(object : Comparator<main> {
            override fun compare(m1 : main, m2 : main): Int {
                if (m1.type > m2.type) {
                    return 1
                }
                if (m1.type < m2.type) {
                    return -1
                }
                val valeurs = mapOf('J' to 1,'2' to 2, '3' to 3 , '4' to 4, '5' to 5 , '6' to 6,'7' to 7, '8' to 8, '9' to 9, 'T' to 10,  'Q' to 11, 'K' to 12 , 'A' to 13)
                var index = 0
                while (index < m1.main.length && m1.main[index] == m2.main[index]) {
                    index ++
                }
                if (valeurs[m1.main[index]]!! > valeurs[m2.main[index]]!!) {
                    return 1
                }

                return -1
            }
        })
        game.forEachIndexed { index, main ->
            reponse += main.pari * (index + 1)

        }

        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)

//    testInput = readInput("Day01_test2")
    check(part2(testInput) == 5905L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

data class main(val main: String, val pari: Int, var nbJ: Int, var type: Int)