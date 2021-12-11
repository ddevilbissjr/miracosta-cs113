package HW7_MorseCodeTree;

import java.io.*;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class MorseCodeTree extends BinaryTree<Character> {

    public Node<Character> root;

    public MorseCodeTree() {
        root = new Node<>('n');
        createTreeFromFile();
    }

    public void createTreeFromFile() {
        File file = new File("src/main/java/HW7_MorseCodeTree/Letters.txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String morseCode;
            while ((morseCode = br.readLine()) != null) {
                insertNodeIntoTree(morseCode);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertNodeIntoTree(String nextLine) {
        String[] arr = nextLine.split("");
        Node<Character> temp = root;

        for (int i = 2; i < arr.length - 1; i++) {
            if (arr[i].equals("*")) {
                temp = temp.left;
            } else if (arr[i].equals("-")) {
                temp = temp.right;
            }
        }
        if (arr[arr.length - 1].equals("*")) {
            Node<Character> node = new Node<Character>(arr[0].charAt(0));
            temp.left = node;
        } else if (arr[arr.length - 1].equals("-")) {
            Node<Character> node = new Node<Character>(arr[0].charAt(0));
            temp.right = node;
        }
    }

    private char decodeCharacter(String message, Node<Character> node) {
        char current;
        if (message.length() > 0) {
            current = message.charAt(0);
            message = message.substring(1);
        } else {
            if (node == null) {
                return '!';
            }
            return node.data;
        }
        if (current == '*') {
            if (node == null) {
                return '!';
            }
            node = node.left;
            return decodeCharacter(message, node);
        } else if (current == '-') {
            if (node == null) {
                return '!';
            }
            node = node.right;
            return decodeCharacter(message, node);
        }
        return node.data;
    }

    public char decodeCharacter(String message) {
        return decodeCharacter(message, root);
    }

    public String translateFromMorseCode(String morseCode) {
        for (int i = 0; i < morseCode.length(); i++) {
            if ((morseCode.charAt(i) != '-') && (morseCode.charAt(i) != '*') && (morseCode.charAt(i) != ' ')) {
                throw new InputMismatchException();
            }
        }

        StringTokenizer morseCodeArr = new StringTokenizer(morseCode, " ");
        String message = "";
        while (morseCodeArr.hasMoreTokens()) {
            char tmp = decodeCharacter(morseCodeArr.nextToken());
            if (tmp == '!') {
                throw new InputMismatchException();
            }
            message += tmp;
        }
        return message;
    }
}