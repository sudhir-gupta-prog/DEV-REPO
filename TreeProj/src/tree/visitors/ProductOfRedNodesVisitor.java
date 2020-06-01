package tree.visitors;

public class ProductOfRedNodesVisitor extends TreeVis
{
	private long		result	= 1;

	private final int	M		= 1000000007;

	public int getResult()
	{
		return (int) result;
	}

	public void visitNode(TreeNode node)
	{
		if (node.getColor() == Color.RED)
		{
			result = (result * node.getValue()) % M;
		}
	}

	public void visitLeaf(TreeLeaf leaf)
	{
		if (leaf.getColor() == Color.RED)
		{
			result = (result * leaf.getValue()) % M;
		}
	}
}
