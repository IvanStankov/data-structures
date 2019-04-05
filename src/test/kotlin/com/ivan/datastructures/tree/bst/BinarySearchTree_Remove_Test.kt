package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Entry
import com.ivan.datastructures.tree.Tree
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class BinarySearchTree_Remove_Test {

    val bst: Tree<Int, String> = BinarySearchTree()

    //       10
    //      /  \
    //     /    \
    //    /      \
    //   6       20
    //  / \     /  \
    // 2   9  15    30
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
        bst.insert(15, "fifteen")
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
        assertNode(root, 15)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = true, hasRight = false)
        assertNode(root.left!!.right!!.left!!, 7, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 22, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.left!!.right!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForLeftLeaf_ShouldRemoveLeaf() {
        // When
        bst.remove(7)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 22, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.left!!.right!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForRightLeaf_ShouldRemoveLeaf() {
        // When
        bst.remove(24)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = true, hasRight = false)
        assertNode(root.left!!.right!!.left!!, 7, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 22, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForNodeWithOnlyLeftChild_ShouldPlaceLeftChildInsteadOfRemoved() {
        // When
        bst.remove(9)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 7, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 22, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.left!!.right!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForNodeWithOnlyRightChild_ShouldPlaceRightChildInsteadOfRemoved() {
        // When
        bst.remove(22)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = true, hasRight = false)
        assertNode(root.left!!.right!!.left!!, 7, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForNodeWith2ChildrenWhenSuccessorDoesNotHaveRightChild_ShouldPlaceSuccessorInsteadOfRemoved() {
        // When
        bst.remove(6)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 7)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 20, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 22, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.left!!.right!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    @Test
    fun remove_ForNodeWith2ChildrenWhenSuccessorHasRightChild_ShouldPlaceSuccessorInsteadOfRemovedAndRightChildInPlaceOfSuccessor() {
        // When
        bst.remove(20)

        // Then
        val root = bst.root()!!
        assertNode(root, 10)
        // left subtree
        assertNode(root.left!!, 6)
        assertNode(root.left!!.left!!, 2, hasLeft = false, hasRight = false)
        assertNode(root.left!!.right!!, 9, hasLeft = true, hasRight = false)
        assertNode(root.left!!.right!!.left!!, 7, hasLeft = false, hasRight = false)
        // right subtree
        assertNode(root.right!!, 22, hasLeft = true, hasRight = true)
        assertNode(root.right!!.left!!, 15, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!, 30)
        assertNode(root.right!!.right!!.left!!, 24, hasLeft = false, hasRight = false)
        assertNode(root.right!!.right!!.right!!, 40, hasLeft = false, hasRight = true)
        assertNode(root.right!!.right!!.right!!.right!!, 45, hasLeft = false, hasRight = false)
    }

    private fun assertNode(node: Entry<Int, String>, key: Int, hasLeft: Boolean = true, hasRight: Boolean = true) {
        assertEquals(key, node.key)
        if (hasLeft) {
            assertEquals(node, node.left!!.parent)
        } else {
            assertNull(node.left)
        }
        if (hasRight) {
            assertEquals(node, node.right!!.parent)
        } else {
            assertNull(node.right)
        }
    }
}