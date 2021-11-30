
/**
 * A_PARENTHESIS
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class A_PARENTHESIS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split("");
        Stack<String> stacks = new Stack<>();
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("(")) {
                stacks.push("(");
            } else if (arr[i].equals("[")) {
                stacks.push("[");
            } else if (arr[i].equals("{")) {
                stacks.push("{");
            } else if (arr[i].equals(")")) {
                if (stacks.empty()) {
                    flag = false;
                    break;
                }
                if (stacks.peek().equals("(")) {
                    stacks.pop();
                }
            } else if (arr[i].equals("]")) {
                if (stacks.empty()) {
                    flag = false;
                    break;
                }
                if (stacks.peek().equals("[")) {
                    stacks.pop();
                }
            } else if (arr[i].equals("}")) {
                if (stacks.empty()) {
                    flag = false;
                    break;
                }
                if (stacks.peek().equals("{")) {
                    stacks.pop();
                }
            }
        }

        if (stacks.empty() && flag)
            System.out.println("1");
        else
            System.out.println("0");
    }
}