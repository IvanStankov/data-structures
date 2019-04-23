package com.ivan.datastructures.tree.avl

import com.ivan.datastructures.tree.DigitsConsoleTreePrinter
import com.ivan.datastructures.tree.Tree
import com.ivan.datastructures.tree.TreePrinter
import kotlin.test.Test

internal class AvlTreeVisualTest {

    private val treePrinter: TreePrinter = DigitsConsoleTreePrinter()

    @Test
    fun createTreeAsc() {
        val avl: Tree<String, Int> = AvlTree()

        avl.insert("1", 1)
        avl.insert("2", 2)
        avl.insert("3", 3)
        treePrinter.print(avl)
        avl.insert("4", 4)
        treePrinter.print(avl)
        println("Add 5")
        avl.insert("5", 5)
        treePrinter.print(avl)
        avl.insert("6", 6)
        println("Add 7")
        avl.insert("7", 7)
        treePrinter.print(avl)
        avl.insert("8", 8)
        println("Add 9")
        avl.insert("9", 9)
        treePrinter.print(avl)
    }

    @Test
    fun createTreeDesc() {
        val avl: Tree<String, Int> = AvlTree()

        avl.insert("9", 9)
        avl.insert("8", 8)
        avl.insert("7", 7)
        treePrinter.print(avl)
        avl.insert("6", 6)
        treePrinter.print(avl)
        println("Add 5")
        avl.insert("5", 5)
        treePrinter.print(avl)
        avl.insert("4", 4)
        println("Add 3")
        avl.insert("3", 3)
        treePrinter.print(avl)
        avl.insert("2", 8)
        println("Add 1")
        avl.insert("1", 1)
        treePrinter.print(avl)
    }

    @Test
    fun createWithRightKink() {
        val avl: Tree<String, Int> = AvlTree()

        avl.insert("2", 2)
        avl.insert("4", 4)
        avl.insert("3", 3)
        treePrinter.print(avl)
        avl.insert("6", 6)
        avl.insert("5", 5)
        println("After inserting 5")
        treePrinter.print(avl)
        avl.insert("8", 8)
        println("After inserting 8")
        treePrinter.print(avl)
        avl.insert("7", 7)
        avl.insert("9", 9)
        treePrinter.print(avl)
    }

    @Test
    fun createWithLeftKink() {
        val avl: Tree<String, Int> = AvlTree()

        avl.insert("9", 9)
        avl.insert("7", 7)
        avl.insert("8", 6)
        treePrinter.print(avl)
        avl.insert("5", 5)
        avl.insert("6", 6)
        println("After inserting 6")
        treePrinter.print(avl)
    }

    @Test
    fun createWithLongLeftKink() {
        val avl: Tree<String, Int> = AvlTree()

        avl.insert("8", 6)
        avl.insert("9", 9)
        avl.insert("4", 4)
        avl.insert("1", 1)
        treePrinter.print(avl)
        avl.insert("5", 5)
        treePrinter.print(avl)
    }
}