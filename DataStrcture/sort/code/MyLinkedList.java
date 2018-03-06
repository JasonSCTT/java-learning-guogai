public class MyLinkedList {
	protected Node first;	// ����ĵ�һ���ڵ�
	protected Node last;	// ��������һ���ڵ�
	private int size;	// �ڵ������

	// �����е�ÿһ���ڵ�
	public class Node {
		public Node(Object ele) { 	
			this.ele = ele;
		}

		Node prev;				// ��һ���ڵ����
		Node next;				// ��һ���ڵ����
		public Object ele; // ��ǰ�ڵ��д洢������
	}

	public void addFirst(Object ele) {
		Node node = new Node(ele);      //��Ҫ����Ľڵ����
		//����һ���ڵ㣬���Ϊ�յĻ������ɶ�ʱ��һ����Ҳ�����һ��
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			node.next = this.first;				// ��֮ǰ��һ����Ϊ�����ڵ����һ���ڵ㣬(����һ������ǰ��ֻ�ܵ��϶��ˡ�)
			this.first.prev = node;				// �������ڵ���Ϊ֮ǰ��һ���ڵ����һ���ڵ�
			this.first = node;					// �������Ľڵ���Ϊ��һ���ڵ�
		}
		size++;    //�������Ҫ��������
	}	
	public void addLast(Object ele) {
		Node node = new Node(ele);
		if (size == 0) {
			this.first = node;
			this.last = node;
		}else {
			this.last.next = node;			// �����ڵ���Ϊ֮ǰ���һ���ڵ����һ���ڵ�(��Ϊ�Ǽ��ں��棬���Ե�ǰ�ڵ����һ������ �����ڵ�)
			node.prev = this.last;			// ֮ǰ���һ���ڵ���Ϊ�����ڵ����һ���ڵ�
			this.last = node;				// �������Ľڵ���Ϊ���һ���ڵ�
		}
	}
	//ԭ���Ҹ�����
	public void remove(Object ele) {
		// �ҵ���ɾ���Ľڵ�
		Node current = this.first;// ȷ��Ϊ��һ���ڵ�,��ͷ��β��ʼ��
		for (int i = 0; i < size; i++) {
			if (!current.ele.equals(ele)) {// ��ǰΪtrue !true Ϊfalse ,˵���ҵ���ǰele,���
				if (current.next == null) { // ����: ���falseȡ��Ϊtrue, �ж��Ƿ����һ��,
					return;
				}
				current = current.next;
			}
		}
		//ɾ���ڵ�
		if(current==first){
			this.first = current.next; //��ǰ����һ����Ϊ��һ��
			this.first.prev = null; //��ǰ��һ������һ������������Ϊnull
			
		}else if(current==last){
			this.last = current.prev;
			this.last.next = null;			
		}else{
			//��ɾ����ǰ�ڵ����һ���ڵ���Ϊɾ���ڵ����һ���ڵ��next
			current.prev.next =current.next;
			//��ɾ���ڵ����һ���ڵ���Ϊɾ���ڵ���һ���ڵ��prev
			current.next.prev = current.prev;
			
		}
		size--;
		//System.out.println("current =" + current.ele);
	}

	public String toString() {
		if (size == 0) {
			return "[]";
		}
		StringBuilder sb = new StringBuilder(size * 2 + 1);
		Node current = this.first;// ��һ���ڵ�
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(current.ele);
			if (i != size - 1) {
				sb.append(",");
			} else {
				sb.append("]");
			}
			current = current.next; // ��ȡ�Լ�����һ���ڵ�
		}
		return sb.toString();
	}
	
	
}