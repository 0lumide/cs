public class Tree{
	int value;
	private Tree leftNode;
	private Tree rightNode;
	public Tree(){
		this(0);
	}
	public Tree(int value){
		this.value = value;
		this.leftNode = null;
		this.rightNode = null;
	}
	public void setRight(Tree tree){
		rightNode = tree;
	}
	public void setLeft(Tree tree){
		leftNode = tree;
	}
	private static Tree initTree(int currentNode, int[] values){
		Tree node;
		if(values[currentNode] != -1){
			node = new Tree(values[currentNode]);
		}
		else
			return null;
		int left = 2*(currentNode)+1;
		int right = left + 1;
		if((left < values.length)&&(values[left]!= -1))
			node.setLeft(initTree(left, values));
		if((right < values.length)&&(values[right]!= -1))
			node.setRight(initTree(right, values));
		return node;
	}
	public void printInorder(){
		if(this.leftNode != null)
			this.leftNode.printInorder();
		System.out.printf("%d, ",this.value);
		if(this.rightNode != null)
			this.rightNode.printInorder();
	}

	public void printPreorder(){
		System.out.printf("%d, ",this.value);
		if(this.leftNode != null)
			this.leftNode.printPreorder();
		if(this.rightNode != null)
			this.rightNode.printPreorder();
	}
	public void printPostorder(){
		if(this.leftNode != null)
			this.leftNode.printPostorder();
		if(this.rightNode != null)
			this.rightNode.printPostorder();	
		System.out.printf("%d, ",this.value);
	}
	public Tree mirrorTree(){
		Tree temp = this.rightNode;
		this.rightNode = this.leftNode;
		this.leftNode = temp;
		if(this.rightNode != null)
			this.rightNode.mirrorTree();
		if(this.leftNode != null)
			this.leftNode.mirrorTree();
		return this;
	}
	public static void main(String[] args){
		int[] treeHeap = new int[]{1, 2, 3, -1, -1, 4, 5};
		Tree tree = initTree(0, treeHeap);
		System.out.println("Inorder:");
		tree.printInorder();
		System.out.println("");
		System.out.println("Preorder:");
		tree.printPreorder();
		System.out.println("");
		System.out.println("Postorder");
		tree.printPostorder();
		System.out.println("");
		Tree treeMirror = tree.mirrorTree();
		System.out.println("Inorder:");
		tree.printInorder();
		System.out.println("");
		System.out.println("Preorder:");
		tree.printPreorder();
		System.out.println("");
		System.out.println("Postorder");
		tree.printPostorder();
		System.out.println("");

	}
}
