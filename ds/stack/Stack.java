public class Stack {
    private int[] stack;
    private int top;

    public Stack(int length){
        stack = new int[length];
    }

    public void push(int val){
        if(top == stack.length){
            System.out.println("栈满");
        } else {
            stack[top++] = val;
        }
    }

    public int pop(){
        if(0 == top){
            System.out.println("栈空");
            return -1;
        }
        return stack[--top];
    }

    public int peek(){
        if(0 == top){
            System.out.println("栈空");
            return -1;
        }
        return stack[top - 1];
    }

    public int size(){
        return top - 0;
    }

    public void increment(){
        int[] newStack = new int[stack.length + stack.length >> 1];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    public void reset(){
        top = 0;
    }

    public void show(){
        System.out.println("栈中元素");
        for (int i = top - 1; i >= 0; i--) {
            System.out.printf("%d \r\n", stack[i]);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.show();
        System.out.printf("栈顶：%d, 栈空间：%d \r\n", stack.peek(), stack.size());
        stack.pop();
        stack.pop();
        stack.pop();
        stack.show();
        System.out.printf("栈顶：%d, 栈空间：%d \r\n", stack.peek(), stack.size());
        stack.pop();
    }
}
