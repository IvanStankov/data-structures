package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.BaseBinaryTree
import com.ivan.datastructures.tree.Entry

class BinarySearchTree<K: Comparable<K>, V> : BaseBinaryTree<K, V>() {

    override fun createEntry(key: K, value: V):  Entry<K, V> {
        return Entry(key, value)
    }

    override fun insert(key: K, value: V) {
        insertNode(key, value)
    }

    override fun remove(key: K): V? {
        val nodeToRemove = findNode(key) ?: return null

        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            val successor = findSuccessor(nodeToRemove)
            if (successor.right != null) {
                // place successor's right node on the previous place of successor
                removeNode(successor, successor.right)
            } else {
                // successor can not have left node, so just remove it
                removeNode(successor)
            }

            removeNode(nodeToRemove, successor, true)

            // change successor's left and right children on the last step
            successor.left = nodeToRemove.left
            successor.left?.parent = successor

            successor.right = nodeToRemove.right
            successor.right?.parent = successor
        } else if (nodeToRemove.left != null) {
            removeNode(nodeToRemove, nodeToRemove.left, true)
        } else if (nodeToRemove.right != null) {
            removeNode(nodeToRemove, nodeToRemove.right, true)
        } else {
            // node to remove has no children
            removeNode(nodeToRemove, checkRoot = true)
        }

        return nodeToRemove.value
    }

    private fun findSuccessor(node: Entry<K, V>): Entry<K, V> {
        var successor = node.right!!
        while (successor.left != null) {
            successor = successor.left!!
        }
        return successor
    }

    private fun removeNode(nodeToRemove: Entry<K, V>, newNode: Entry<K, V>? = null, checkRoot: Boolean = false) {
        val parent = nodeToRemove.parent
        if (parent?.left?.key == nodeToRemove.key) {
            parent.left = newNode
        } else if (parent?.right?.key == nodeToRemove.key) {
            parent.right = newNode
        }
        newNode?.parent = parent

        if (checkRoot && root!!.key == nodeToRemove.key) {
            root = newNode
        }
    }
}