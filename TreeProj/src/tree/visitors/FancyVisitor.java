package tree.visitors;

class FancyVisitor extends TreeVis
{
	private int	nonLeafEvenDepthSum	= 0;

	private int	greenLeavesSum		= 0;

	public int getResult()
	{
		return Math.abs(nonLeafEvenDepthSum - greenLeavesSum);
	}

	public void visitNode(TreeNode node)
	{
		if (node.getDepth() % 2 == 0)
		{
			nonLeafEvenDepthSum += node.getValue();
		}
	}

	public void visitLeaf(TreeLeaf leaf)
	{
		if (leaf.getColor() == Color.GREEN)
		{
			greenLeavesSum += leaf.getValue();
		}
	}
}
