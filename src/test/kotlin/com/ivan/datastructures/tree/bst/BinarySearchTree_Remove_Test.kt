package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Entry
import com.ivan.datastructures.tree.Tree
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class BinarySearchTree_Remove_Test {

    val bst: Tree<Int, String> = BinarySearchTree()

    //       10
    //      /  \
    //     /    \
    //    /      \
    //   6       20
    //  / \        \
    // 2   9        30
    //    /        /  \
    //   7       22    40
    //            \     \
    //             24    45
    @BeforeTest
    fun beforeTest() {
        bst.insert(10, "ten")
        bst.insert(6, "six")
        bst.insert(20, "twenty")
        bst.insert(2, "two")
        bst.insert(9, "nine")
        bst.insert(30, "thirty")
        bst.insert(7, "seven")
        bst.insert(22, "twenty two")
        bst.insert(40, "forty")
        bst.insert(24, "twenty four")
        bst.insert(45, "forty five")
    }

    @Test
    fun remove_ForRoot_ShouldRemoveRootAndPut20AsNewRoot() {
        // When
        bst.remove(10)

        // Then
        val root = bst.root()!!
        // left subtree
        assertNode(root, 20)
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2)
        assertNode(root.left!!.right!!, 9)
        assertNode(root.left!!.right!!.left!!, 7)

        // right subtree
        assertNode(root.right!!, 30)
        assertNode(root.right!!.left!!, 22)
        assertNode(root.right!!.left!!.right!!, 24)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.right!!, 45)
    }

    private fun assertNode(node: Entry<Int, String>, key: Int) {
        assertEquals(key, node.key)
        assertEquals(node, node.left?.parent)
    }
}