package tree.visitors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution
{

	private static int[]								values;

	private static Color[]								colors;

	private static HashMap<Integer, HashSet<Integer>>	map;

	public static Tree solve()
	{
		Scanner scan = new Scanner(System.in);
		int numNodes = scan.nextInt();

		values = new int[numNodes];
		colors = new Color[numNodes];
		map = new HashMap<>(numNodes);
		
		for (int i = 0; i < numNodes; i++)
		{
			values[i] = scan.nextInt();
		}
		
		for (int i = 0; i < numNodes; i++)
		{
			colors[i] = scan.nextInt() == 0 ? Color.RED : Color.GREEN;
		}

		for (int i = 0; i < numNodes - 1; i++)
		{
			int u = scan.nextInt();
			int v = scan.nextInt();

			HashSet<Integer> uNeighbors = map.get(u);
			if (uNeighbors == null)
			{
				uNeighbors = new HashSet<>();
				map.put(u, uNeighbors);
			}
			uNeighbors.add(v);

			HashSet<Integer> vNeighbors = map.get(v);
			if (vNeighbors == null)
			{
				vNeighbors = new HashSet<>();
				map.put(v, vNeighbors);
			}
			vNeighbors.add(u);
		}
		scan.close();

		if (numNodes == 1)
		{
			return new TreeLeaf(values[0], colors[0], 0);
		}

		TreeNode root = new TreeNode(values[0], colors[0], 0);
		addChildren(root, 1);
		return root;
	}

	private static void addChildren(TreeNode parent, Integer parentNum)
	{
		for (Integer treeNum : map.get(parentNum))
		{
			map.get(treeNum).remove(parentNum); 

			HashSet<Integer> grandChildren = map.get(treeNum);
			boolean childHasChild = (grandChildren != null && !grandChildren.isEmpty());
			Tree tree;
			if (childHasChild)
			{
				tree = new TreeNode(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			}
			else
			{
				tree = new TreeLeaf(values[treeNum - 1], colors[treeNum - 1], parent.getDepth() + 1);
			}
			parent.addChild(tree);

			if (tree instanceof TreeNode)
			{
				addChildren((TreeNode) tree, treeNum);
			}
		}
	}

	public static void main(String[] args)
	{
		Tree root = solve();

		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
		ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
		FancyVisitor vis3 = new FancyVisitor();

		root.accept(vis1);
		root.accept(vis2);
		root.accept(vis3);

		int res1 = vis1.getResult();
		int res2 = vis2.getResult();
		int res3 = vis3.getResult();

		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
	}

}
