package tree.visitors;

public class SumInLeavesVisitor extends TreeVis
{
	private int result = 0;

	public int getResult()
	{
		return result;
	}

	public void visitNode(TreeNode node)
	{
	}

	public void visitLeaf(TreeLeaf leaf)
	{
		result += leaf.getValue();
	}
}
