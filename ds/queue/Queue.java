/**
 * 队列实现
 */
public class Queue {
    private Node front;
    private Node rear;
    private int size;

    /**
     * 队列入队
     * @param val 待入队数据
     */
    public void offer(int val){
        if(front == null && rear == null){
            front = new Node(val);
            rear = front;
        } else {
            rear.next = new Node(val);
            rear = rear.next;
        }
        size++;
    }

    /**
     * 队列出队
     * @return 返回出队数据
     */
    public int poll(){
        if(front == null){
            System.out.println("队列为空");
            return -1;
        }
        Node temp = front;
        front = front.next;
        size--;
        return temp.val;
    }

    /**
     * 获取队列容量
     * @return 队列容量
     */
    public int size(){
        return size;
    }

    /**
     * 显示队列内容
     */
    public void show(){
        Node temp = front;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 节点类
     */
    private class Node{
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println("队列长度：" + queue.size());
        queue.show();
        queue.poll();
        queue.poll();
        queue.poll();
        queue.poll();
        System.out.println("队列长度：" + queue.size());
    }
}
