package misc;

import java.util.Objects;

class TreeNode {

    private Integer id;
    private TreeNode left;
    private TreeNode right;

    private TreeNode(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static TreeNode createNode(Integer id) {
        return new TreeNode(id);
    }

    public static TreeNode createNode(Integer id, TreeNode left, TreeNode right) {
       TreeNode treeNode = new TreeNode(id);

       treeNode.left = left;
       treeNode.right = right;

       return treeNode;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;
        TreeNode node = (TreeNode) o;

        return Objects.equals(getId(), node.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}