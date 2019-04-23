package com.ivan.datastructures.tree

abstract class BaseBinaryTree<K : Comparable<K>, V> : Tree<K, V> {

    protected var root: Entry<K, V>? = null

    override fun root(): Entry<K, V>? {
        return root
    }

    override fun find(key: K): V? {
        return findNode(key)?.value
    }

    protected abstract fun createEntry(key: K, value: V): Entry<K, V>;

    protected fun findNode(key: K): Entry<K, V>? {
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

    protected fun insertNode(key: K, value: V): Entry<K, V> {
        if (root == null) {
            root = createEntry(key, value)
            return root!!
        }

        var parent = root
        while (parent != null) {
            val compareResult = key.compareTo(parent.key)

            when {
                compareResult < 0 -> {
                    if (parent.left == null) {
                        val newNode = createEntry(key, value)
                        parent.left = newNode
                        newNode.parent = parent
                        return newNode
                    }
                    parent = parent.left
                }
                compareResult > 0 -> {
                    if (parent.right == null) {
                        val newNode = createEntry(key, value)
                        parent.right = newNode
                        newNode.parent = parent
                        return newNode
                    }
                    parent = parent.right
                }
                else -> {
                    // key already exists in the tree, just substitute the value
                    parent.value = value
                    return parent
                }
            }
        }
        throw Exception("Unreachable situation")
    }

    protected fun removeNode(key: K): Entry<K, V>? {
        val nodeToRemove = findNode(key) ?: return null

        if (nodeToRemove.left != null && nodeToRemove.right != null) {
            val successor = findSuccessor(nodeToRemove)
            if (successor.right != null) {
                // place successor's right node on the previous place of successor
                doRemoveNode(successor, successor.right)
            } else {
                // successor can not have left node, so just remove it
                doRemoveNode(successor)
            }

            doRemoveNode(nodeToRemove, successor, true)

            // change successor's left and right children on the last step
            successor.left = nodeToRemove.left
            successor.left?.parent = successor

            successor.right = nodeToRemove.right
            successor.right?.parent = successor
        } else if (nodeToRemove.left != null) {
            doRemoveNode(nodeToRemove, nodeToRemove.left, true)
        } else if (nodeToRemove.right != null) {
            doRemoveNode(nodeToRemove, nodeToRemove.right, true)
        } else {
            // node to remove has no children
            doRemoveNode(nodeToRemove, checkRoot = true)
        }

        return nodeToRemove
    }

    private fun doRemoveNode(nodeToRemove: Entry<K, V>, newNode: Entry<K, V>? = null, checkRoot: Boolean = false) {
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

    private fun findSuccessor(node: Entry<K, V>): Entry<K, V> {
        var successor = node.right!!
        while (successor.left != null) {
            successor = successor.left!!
        }
        return successor
    }
}