package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Entry
import com.ivan.datastructures.tree.Tree

class BinarySearchTree<K: Comparable<K>, V> : Tree<K, V> {

    var root: Entry<K, V>? = null

    override fun root(): Entry<K, V>? {
        return root
    }

    override fun find(key: K): V? {
        return findNode(key)?.value
    }

    override fun insert(key: K, value: V) {
        if (root == null) {
            root = Entry(key, value)
            return
        }

        var parent = root
        while (parent != null) {
            val compareResult = key.compareTo(parent.key)

            when {
                compareResult < 0 -> {
                    if (parent.left == null) {
                        parent.left = Entry(key, value, parent)
                        return
                    }
                    parent = parent.left
                }
                compareResult > 0 -> {
                    if (parent.right == null) {
                        parent.right = Entry(key, value, parent)
                    }
                    parent = parent.right
                }
                else -> {
                    // key already exists in the tree, just substitute the value
                    parent.value = value
                    return
                }
            }
        }
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

    private fun findNode(key: K): Entry<K, V>? {
        if (root == null) {
            return null
        }

        var tmp = root
        while (tmp != null) {
            val compareResult = key.compareTo(tmp.key)

            if (compareResult < 0) {
                tmp = tmp.left
            } else if (compareResult > 0) {
                tmp = tmp.right
            } else {
                // key is found
                return tmp
            }
        }
        // key does not exist in the tree
        return null
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