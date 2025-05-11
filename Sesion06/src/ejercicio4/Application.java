package ejercicio4;

import actividad1.ExceptionIsEmpty;
import actividad1.Stack;
import ejercicio1.StackLink;

public class Application {
    public static boolean symbolBalancing(String s) {
        Stack<Character> stack = new StackLink<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                try {
                    if (stack.isEmpty()) return false;
                    char top = stack.pop();
                    if ((ch == ')' && top != '(') ||
                        (ch == ']' && top != '[') ||
                        (ch == '}' && top != '{')) {
                        return false;
                    }
                } catch (ExceptionIsEmpty e) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
