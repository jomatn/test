package com.example.test

import java.io.File

class Words {

    fun makeWord() {
        val letters = listOf('К', 'о', 'т', 'л', 'и', 'н')
        val words = mutableSetOf<String>()
        val dictionary = File("dictionary.txt").readLines().toSet()

        fun make(line: String, list: List<Char>) {
            if (line.isNotEmpty() && line in dictionary) {
                words.add(line)
            }
            if (list.isNotEmpty()) {
                for (i in list.indices) {
                    make(line + list[i], list.subList(0, i) + list.subList(i + 1, list.size))
                }
            }
        }

        make("", letters)

        println("Найденные слова:")
        words.forEach {
            println(it)
        }
    }

    fun evenOdds(numbers: IntArray): Pair<List<Int>, List<Int>> {
        val evenNumbers = numbers.filter { it % 2 == 0 }
        val oddNumbers = numbers.filter { it % 2 != 0 }
        return Pair(evenNumbers, oddNumbers)
    }

    fun findSecondLargest(numbers: IntArray): Int? {
        if (numbers.size < 2) return null

        var largest = Int.MIN_VALUE
        var secondLargest = Int.MIN_VALUE

        for (number in numbers) {
            when {
                number > largest -> {
                    secondLargest = largest
                    largest = number
                }
                number > secondLargest && number != largest -> {
                    secondLargest = number
                }
            }
        }

        return if (secondLargest == Int.MIN_VALUE) null else secondLargest
    }
}

fun main() {
    val words = Words()

    words.makeWord()

    val numbers = intArrayOf(12, 34, 45, 9, 8, 90, 3)
    val (evenNumbers, oddNumbers) = words.evenOdds(numbers)
    println("Четные числа: $evenNumbers")
    println("Нечетные числа: $oddNumbers")

    val secondLargest = words.findSecondLargest(numbers)
    println("Второе по величине число: $secondLargest")
}
