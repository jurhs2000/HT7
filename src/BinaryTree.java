/**
 * Clase de arbol binario que guarda por nodos e implementa su busqueda
 * 
 * Referencia de: Data Structures: Trees. HackerRank. (2016)
 * extraido de: https://www.youtube.com/watch?v=oSWTXtMglKE&t=215s
 * 
 * @author Julio Herrera
 * @param <E> Se compromete a que entre un tipo comparable
 * Esto para que el nodo pueda comparar los valores
 */
public class BinaryTree<E extends Comparable<E>> {

    /**
     * El nodo del arbol binario contiene su valor, un nodo hijo derecho
     * y un nodo hijo izquierdo
     * @param <E> Se compromete a que entre un tipo comparable
     */
    class Node<E extends Comparable<E>> {

        /**
         * Nodos izquierdo y derecho
         */
        Node<E> left, right;
        E data;

        /**
         * Constructor
         */
        public Node(E data) {
            this.data = data;
        }

        /**
         * inserta un nodo ya sea al lado izquierdo o derecho
         * comprobando si es mayor o menor con el compareTo
         * @param value de tipo del generico
         */
        public void insert(E value) {
            if (data.compareTo(value) < 0) {
                if (left == null) {
                    left = new Node<E>(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new Node<E>(value);
                } else {
                    right.insert(value);
                }
            }
        }

        /**
         * Comprueba si existe el valor siguiendo el camino corto
         * @param value de tipo del generico
         * @return true si existe el valor y false si no existe
         */
        public boolean contains(E value) {
            if (data.compareTo(value) == 0) {
                return true;
            }
            if (data.compareTo(value) < 0) {
                if (left != null) {
                    return left.contains(value);
                }
            } else if (data.compareTo(value) > 0) {
                if (right != null) {
                    return right.contains(value);
                }
            }
            return false;
        }

        /**
         * Obtiene el valor de un nodo
         * @param value de tipo del generico
         * @return No devuelve el nodo, solo el valor del nodo
         * si no lo encuentra devuelve null
         */
        public E getNodeData(E value) {
            if (data.compareTo(value) == 0) {
                return data;
            }
            if (data.compareTo(value) < 0) {
                if (left != null) {
                    return left.getNodeData(value);
                }
            } else if (data.compareTo(value) > 0) {
                if (right != null) {
                    return right.getNodeData(value);
                }
            }
            return null;
        }

        /**
         * Imprime el arbol binario en In-Order
         */
        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }
            if (right != null) {
                right.printInOrder();
            }
        }
    }

    /**
     * Al principio el nodo root es nul hasta que se agregue un nodo
     */
    Node<E> root = null;

    /**
     * Si el nodo root es nulo se agrega la raiz
     * si no se ve si se agrega a un hijo
     * @param value Un valor del tipo generico para agregar al nodo
     */
    public void addNode(E value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            root.insert(value);
        }
    }

    /**
     * llama al metodo printInOrder de la raiz
     */
    public void printInOrder() {
        root.printInOrder();
    }

    /**
     * Comprueba si existe un valor en los nodos
     * llamando al metodo contains del root
     */
    public boolean contains(E value) {
        return root.contains(value);
    }

    /**
     * Devuelve un valor del tipo generico a partir de un nodo
     * llamando al metodo getNodeData de la raiz
     */
    public E getNodeData(E value) {
        return root.getNodeData(value);
    }
}