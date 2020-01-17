/**
 * 链表实现
 */
public class LinkedList {
    // 根节点
    private Node root;
    // 单链表长度
    private int size;

    /**
     * 获取链表长度
     * @return 链表长度
     */
    public int getSize() {
        return size;
    }

    // 头插法
    /* public void add(Integer val) {
        if (root == null) {
            root = new Node(val);
        } else {
            Node tmp = root;
            root = new Node(val);
            root.next = tmp;
        }
        size++;
    }*/

    /**
     * 尾插法实现链表添加
     * @param val 待添加数据
     */
    public void add(Integer val) {
        if(root == null){
            root = new Node(val);
        } else {
            Node tmp = root;
            // 遍历到最后一个不为null的节点
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(val);
        }
    }

    /**
     * 删除节点
     * @param val 待删除节点的值
     * @return
     */
    public Integer delete(Integer val) {
        if (root == null) {
            return 0;
        }
        Node tmp = root;
        if (tmp.val == val) {
            root = root.next;
        } else {
            // 判断下一个节点的值
            while (tmp.next != null && tmp.next.val != val) {
                tmp = tmp.next;
            }
            if (tmp.next == null) {
                return 0;
            } else {
                tmp.next = tmp.next.next;
            }
        }
        return val;
    }

    // 新增节点
    /*public void reverse(){
        if (size < 2) {
                return;
        }
        Node tmp = null;
        Node newNode = null;
        while(root != null){
            tmp = newNode;
            newNode = new Node(root.val);
            root = root.next;
            newNode.next = tmp;
        }
        root = newNode;
    }*/

    /**
     * 不用新增节点实现链表逆序
     */
    public void reverse() {
        if (size < 2) {
            return;
        }
        Node tmp = null;
        Node newNode = null;
        while(root != null){
            // 记录链表的下一个位置
            tmp = root.next;
            // 反转指针指向
            root.next = newNode;
            newNode = root;
            root = tmp;
        }
        root = newNode;
    }

    /**
     * 递归方式实现链表逆序
     * @param node 头节点
     * @return
     */
    public Node reverse(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        // 将每一个节点分离
        Node next = node.next;
        node.next = null;
        Node newNode = reverse(next);
        // 反转指针指向
        next.next = node;
        return newNode;
    }

    // 使用栈
    /* public void reverse() {
        if (size < 2) {
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        while (root.next != null) {
            stack.push(root);
            root = root.next;
        }
        Node newNode = root;
        while(!stack.isEmpty()){
            newNode.next = stack.pop();
            newNode = newNode.next;
        }
        newNode.next = null;
    }*/

    /**
     * 显示链表数据
     */
    public void show() {
        if (root == null) {
            System.out.println("链表为null");
        } else {
            Node tmp = root;
            while (tmp != null) {
                System.out.println(tmp);
                tmp = tmp.next;
            }
        }
    }

    /**
     * 节点类
     */
    public class Node {
        Integer val;
        Node next;

        public Node(Integer val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    "}";
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        System.out.println("添加链表");
        linkedList.show();
        System.out.println("链表长度：" + linkedList.getSize());
        System.out.println("反转链表");
        linkedList.reverse();
        linkedList.show();

    }
}
