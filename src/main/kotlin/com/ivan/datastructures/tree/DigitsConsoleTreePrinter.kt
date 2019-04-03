package com.ivan.datastructures.tree

import kotlin.math.pow

class DigitsConsoleTreePrinter : TreePrinter {

    override fun print(tree: Tree<out Any, out Any>) {
        val maxLevel = maxLevel(tree.root())
        printNodes(listOf(tree.root()), 1, maxLevel)
    }

    private fun printNodes(nodes: List<Entry<out Any, out Any>?>, level: Int, maxLevel: Int) {
        if (nodes.isEmpty() || isAllElemsNull(nodes)) {
            return
        }

        val floor = maxLevel - level
        val edgeLines = 2.0.pow(maxOf(floor - 1, 0)).toInt()
        val firstSpaces = 2.0.pow(floor).toInt() - 1
        val betweenSpaces = 2.0.pow(floor + 1).toInt() - 1

        printWhitespaces(firstSpaces)

        val newNodes: MutableList<Entry<out Any, out Any>?> = mutableListOf()
        nodes.forEach { node: Entry<out Any, out Any>? ->
            if (node != null) {
                print(node.key)
                newNodes.add(node.left)
                newNodes.add(node.right)
            } else {
                newNodes.add(null)
                newNodes.add(null)
                print(" ")
            }

            printWhitespaces(betweenSpaces)
        }
        println()

        for (i in 1..edgeLines) {
            for (j in 0 until nodes.size) {
                printWhitespaces(firstSpaces - i)

                if (nodes[j] == null) {
                    printWhitespaces(edgeLines * 2 + i + 1)
                    continue
                }

                if (nodes[j]?.left != null) {
                    print("/")
                } else {
                    printWhitespaces(1)
                }

                printWhitespaces(i * 2 - 1)

                if (nodes[j]?.right != null) {
                    print("\\")
                } else {
                    printWhitespaces(1)
                }

                printWhitespaces(edgeLines * 2 - i)
            }
            println()
        }

        printNodes(newNodes, level + 1, maxLevel)
    }

    private fun maxLevel(root: Entry<out Any, out Any>?): Int {
        if (root == null) {
            return 0
        }

        val leftLevel = maxLevel(root.left)
        val rightLevel = maxLevel(root.right)
        return maxOf(leftLevel, rightLevel) + 1
    }

    private fun isAllElemsNull(nodes: List<Entry<out Any, out Any>?>): Boolean {
        return !nodes.any {
            it != null
        }
    }

    private fun printWhitespaces(whitespacesNumber: Int) {
        repeat(whitespacesNumber) { print(" ") }
    }
}