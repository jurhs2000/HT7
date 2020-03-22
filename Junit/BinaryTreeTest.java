/**
 * @author Julio Herrera
 * 
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest {

    /**
     * Con esta prueba verificaremos que jUnit este funcionando
     */
    @Test
    public void pruebaJUnit() {
        assertTrue(true);
    }

    @Test
    public void containsTest1() {
        BinaryTree<Association<String, String>> binaryTree = new BinaryTree<>();
        Association<String, String> node = new Association<>("llave1", "valor1");
        binaryTree.addNode(node);
        node = new Association<>("llave2", "valor2");
        binaryTree.addNode(node);
        node = new Association<>("llave3", "valor3");
        binaryTree.addNode(node);
        assertTrue(binaryTree.contains(new Association<>("llave3", "valor3")));
    }

    @Test
    public void containsTest2() {
        BinaryTree<Association<String, String>> binaryTree = new BinaryTree<>();
        Association<String, String> node = new Association<>("llave1", "valor1");
        binaryTree.addNode(node);
        node = new Association<>("llave2", "valor2");
        binaryTree.addNode(node);
        node = new Association<>("llave3", "valor3");
        binaryTree.addNode(node);
        assertFalse(binaryTree.contains(new Association<>("llave4", "valor4")));
    }

    @Test
    public void getNodeDataTest() {
        BinaryTree<Association<String, String>> binaryTree = new BinaryTree<>();
        Association<String, String> node = new Association<>("llave1", "valor1");
        binaryTree.addNode(node);
        node = new Association<>("llave2", "valor2");
        binaryTree.addNode(node);
        node = new Association<>("llave3", "valor3");
        binaryTree.addNode(node);
        assertEquals(new Association<>("llave3", "valor3"), binaryTree.contains(new Association<>("llave3", "Otro valor")));
    }


}