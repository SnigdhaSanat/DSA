package Practice;

import java.util.*;

public class AirBnB {
    static class BSTNode {
        public int value;
        public BSTNode left;
        public BSTNode right;

        public BSTNode(int value, BSTNode left, BSTNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static int findRoot(List<List<Integer>> nodes) {
        Map<Integer, BSTNode> nodeHm = new HashMap<>();
        Map<Integer, Integer> parentsHm = new HashMap<>();

        for(List<Integer> nodeList : nodes) {
            BSTNode left=new BSTNode(nodeList.get(1),null,null);
            BSTNode right=new BSTNode(nodeList.get(2),null,null);
            nodeHm.put(nodeList.get(0), new BSTNode(nodeList.get(0),left,right));

            if(nodeList.get(1) != -1)
                parentsHm.put(nodeList.get(1), nodeList.get(0));
            if(nodeList.get(2) != -1)
                parentsHm.put(nodeList.get(2), nodeList.get(0));
        }

        for(List<Integer> node: nodes) {
            BSTNode n = nodeHm.get(node.get(0));
            n.left = nodeHm.get(node.get(1));
            n.right = nodeHm.get(node.get(2));
        }

        if(parentsHm.size() >= nodeHm.size())
            return -1;

        Set<Integer> headsSet = new HashSet<>();
        int result = -1;
        for(Integer n: nodeHm.keySet()) {
            if(headsSet.contains(n))
                headsSet.remove(n);

            if(parentsHm.containsKey(n)){
                int head = parentsHm.get(n);
                headsSet.add(head);
                result = head;
            }
        }
        Set<Integer> nodeSet = nodeHm.keySet();
        if(!checkBST(nodeHm.get(result), nodeSet) || nodeSet.size() > 0)
            return -1;
        return result;
    }//findRoot

    public static boolean checkBST(BSTNode head, Set<Integer> nodeSet) {
        if(head == null)
            return true;
        nodeSet.remove(head.value);
        if(head.left != null && head.right != null) {
            return (head.value > head.left.value && (checkBST(head.left, nodeSet)) && (head.value < head.right.value && checkBST(head.right, nodeSet)));
        }
        if(head.left != null) {
            return (head.value > head.left.value && checkBST(head.left, nodeSet));
        }
        if(head.right != null) {
            return (head.value < head.right.value && checkBST(head.right, nodeSet));
        }
        return true;
    }//checkBST
}//AirBnB