package jigsaw;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.function.Predicate;

import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


/**
 * ��ƴͼ��Ϸ��N-�������⣩
 * @author abe
 *
 */
public abstract class Jigsaw {
    protected JigsawNode beginJNode;        // ƴͼ����ʼ״̬�ڵ�
    protected JigsawNode endJNode;          // ƴͼ��Ŀ��״̬�ڵ�
    protected JigsawNode currentJNode;      // ƴͼ�ĵ�ǰ״̬�ڵ�

    private List<JigsawNode> solutionPath;  // ��·�������Ա������ʼ״̬����Ŀ��״̬���ƶ�·���е�ÿһ��״̬�ڵ�
    private int searchedNodesNum;           // �ѷ��ʽڵ��������Լ�¼���з��ʹ��Ľڵ������

    private Queue<JigsawNode> exploreList;  // ���Ա����ѷ��ֵ�δ���ʵĽڵ�
    private Set<JigsawNode> visitedList;    // ���Ա����ѷ��ֵĽڵ�

    /**
     * ƴͼ���캯��
     */
    public Jigsaw() {
        this.beginJNode = null;
        this.endJNode = null;
        this.currentJNode = null;
        this.solutionPath = null;
        this.exploreList = null;
        this.visitedList = null;
        this.searchedNodesNum = 0;
    }

    /**
     * ƴͼ���캯��
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     */
    public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = new JigsawNode(bNode);
        this.solutionPath = null;
        this.exploreList = null;
        this.visitedList = null;
        this.searchedNodesNum = 0;
    }

    /**
     * �˺������ڴ�ɢƴͼ��������ĳ�ʼ״̬�ڵ�jNode����ƶ�len�����������ɢ���״̬�ڵ�
     * @param jNode - ��ʼ״̬�ڵ�
     * @param len - ����ƶ��Ĳ���
     * @return ��ɢ���״̬�ڵ�
     */
    public static final JigsawNode scatter(JigsawNode jNode, int len) {
        int randomDirection;
        len += (int) (Math.random() * 2);
        JigsawNode jigsawNode = new JigsawNode(jNode);
        for (int t = 0; t < len; t++) {
            int[] movable = jigsawNode.canMove();
            do {
                randomDirection = (int) (Math.random() * 4);
            } while (0 == movable[randomDirection]);
            jigsawNode.move(randomDirection);
        }
        jigsawNode.setInitial();
        return jigsawNode;
    }

    /**
     * Returns true if the path with initial state and target state is valid.
     * @param path - a path which record every step from the initial state to the target state
     * @param startNode - a jigsaw node which indicate initial state
     * @param destNode - a jigsaw node which indicate target state
     * @return true if the path is valid
     */
    public static final boolean isValidPath(List<JigsawNode> path, JigsawNode startNode, JigsawNode destNode) {
        if (path == null || path.isEmpty() || path.contains(null)) {
            return false;
        }

        int len = path.size();
        if (!path.get(0).equals(destNode) ||
            !path.get(len - 1).equals(startNode)) {
            return false;
        }

        JigsawNode jNode = new JigsawNode(path.get(0));
        for (int i = 1; i < len; i++) {
            JigsawNode prev = path.get(i);
            if (!prev.isValid()) {
                return false;
            }
            int direction = prev.getNodesState()[0] - jNode.getNodesState()[0];
            if (direction == -1) {
                if (!jNode.moveEmptyLeft() || !jNode.equals(prev)) {
                    return false;
                }
            } else if (direction == 1) {
                if (!jNode.moveEmptyRight() || !jNode.equals(prev)) {
                    return false;
                }
            } else if (direction < 0) {
                if (!jNode.moveEmptyUp() || !jNode.equals(prev)) {
                    return false;
                }
            } else if (direction > 0) {
                if (!jNode.moveEmptyDown() || !jNode.equals(prev)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * ��ȡƴͼ�ĵ�ǰ״̬�ڵ�
     * @return currentJNode -  ƴͼ�ĵ�ǰ״̬�ڵ�
     */
    public final JigsawNode getCurrentJNode() {
        return currentJNode;
    }

    /**
     * ����ƴͼ�ĳ�ʼ״̬�ڵ�
     * @param jNode - ƴͼ�ĳ�ʼ״̬�ڵ�
     */
    public final void setBeginJNode(JigsawNode jNode) {
        beginJNode = jNode;
    }

    /**
     * ��ȡƴͼ�ĳ�ʼ״̬�ڵ�
     * @return beginJNode - ƴͼ�ĳ�ʼ״̬�ڵ�
     */
    public final JigsawNode getBeginJNode() {
        return beginJNode;
    }

    /**
     * ����ƴͼ��Ŀ��״̬�ڵ�
     * @param jNode - ƴͼ��Ŀ��״̬�ڵ�
     */
    public final void setEndJNode(JigsawNode jNode) {
        this.endJNode = jNode;
    }

    /**
     * ��ȡƴͼ��Ŀ��״̬�ڵ�
     * @return endJNode - ƴͼ��Ŀ��״̬�ڵ�
     */
    public final JigsawNode getEndJNode() {
        return endJNode;
    }

    /**
     * ��ȡƴͼ�����״̬
     * @return isCompleted - ƴͼ�ѽ�Ϊtrue��ƴͼδ��Ϊfalse
     */
    public final boolean isCompleted() {
        return this.solutionPath != null;
    }

    /**
     * ����ƴͼ�����״̬
     */
    public final void reset() {
        this.solutionPath = null;
    }

    /**
     * ��ȡ��·���ı�
     * @return ��·��solutionPath���ַ��������н⣬����м�¼�ӳ�ʼ״̬����Ŀ��״̬���ƶ�·���е�ÿһ��״̬�ڵ㣻
     * ��δ����޽⣬�򷵻���ʾ��Ϣ��
     */
    public final String getSolutionPath() {
        String str = new String();
        str += "Begin->";
        if (isCompleted()) {
            for (int i = solutionPath.size() - 1; i >= 0; i--) {
                str += solutionPath.get(i).toString() + "->";
            }
            str += "End";
        } else {
            str = "Jigsaw Not Completed.";
        }
        return str;
    }

    /**
     * Get the solution path.
     * @return solution path
     */
    public final List<JigsawNode> getPath() {
        if (this.solutionPath == null && this.currentJNode != null) {
            this.solutionPath = new ArrayList<JigsawNode>(this.currentJNode.getNodeDepth() + 1);
            JigsawNode jNode = this.currentJNode;
            while (jNode != null) {
                solutionPath.add(jNode);
                jNode = jNode.getParent();
            }
        }
        return this.solutionPath;
    }

    /**
     * ��ȡ���ʹ��Ľڵ���searchedNodesNum
     * @return ���������ѷ��ʹ��Ľڵ�����
     */
    public final int getSearchedNodesNum() {
        return searchedNodesNum;
    }

    /**
     * ���������д���ļ��У�ͬʱ��ʾ�ڿ���̨
     * ������ʧ�ܣ�����ʾ�����޽⣬����ѷ��ʽڵ�����
     * �������ɹ����������ʼ״̬beginJnode��Ŀ��״̬endJNode���ѷ��ʽڵ���searchedNodesNum��·�����nodeDepth�ͽ�·��solutionPath��
     * @param pw - �ļ����PrintWriter��������pwΪnull����д�뵽D://Result.txt
     * @throws IOException
     */
    public final void printResult(PrintWriter pw) throws IOException{
        boolean flag = false;
        if (pw == null) {
            pw = new PrintWriter(new FileWriter("Result.txt"));// ����������д��D://BFSearchDialog.txt
            flag = true;
        }
        if (this.isCompleted() == true) {
            // д���ļ�
            pw.println("Jigsaw Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Solution Path: ");
            pw.println(this.getSolutionPath());
            pw.println("Total number of searched nodes:" + this.getSearchedNodesNum());
            pw.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());


            // ���������̨
            System.out.println("Jigsaw Completed");
            System.out.println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Solution Path: ");
            System.out.println(this.getSolutionPath());
            System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
            System.out.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());


        } else {
            // д���ļ�
            pw.println("No solution. Jigsaw Not Completed");
            pw.println("Begin state:" + this.getBeginJNode().toString());
            pw.println("End state:" + this.getEndJNode().toString());
            pw.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());

            // ���������̨
            System.out.println("No solution. Jigsaw Not Completed");
            System.out.println("Begin state:" + this.getBeginJNode().toString());
            System.out.println("End state:" + this.getEndJNode().toString());
            System.out.println("Total number of searched nodes:"
                    + this.getSearchedNodesNum());
        }
        if (flag) {
            pw.close();
        }
    }


    /**
     * Removes all of the elements.
     */
    public final void prune() {
        this.exploreList.clear();
    }

    /**
     * Removes all of the elements of {@code exploreList} that satisfy the given predicate.
     * @param filter - a predicate which returns true for elements to be removed
     * @return true if any elements were removed
     */
    public final boolean prune(Predicate<JigsawNode> filter) {
        return this.exploreList.removeIf(i -> filter.test(new JigsawNode(i)));
    }



    // ****************************************************************
    // *************************ʵ������*******************************
    /**
     * ʵ������һ��������������㷨����ָ��5*5ƴͼ��24-�������⣩�����Ž�
     * Ҫ����ɹ�����������㷨BFSearch()
     * ��Ҫ�漰������BFSearch()
     */
    /**
     * ʵ�������������ʽ�����㷨��������5*5ƴͼ��24-�������⣩
     * Ҫ��1.��ɴ��۹��ƺ���estimateValue()
     *      2.���ʽڵ�����������29000��
     * ��Ҫ�漰������ASearch()��estimateValue()
     */
    // ****************************************************************

    /**
     *��ʵ��һ��������������㷨����ָ��5*5ƴͼ��24-�������⣩�����Ž⡣
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     * @return �����ɹ�ʱΪtrue,ʧ��Ϊfalse
     */
    public abstract boolean BFSearch(JigsawNode bNode, JigsawNode eNode);

    /**
     *��Demo+ʵ���������ʽ���������ʽڵ�������29000������Ϊ����ʧ�ܡ�
     * ����������searchedNodesNum��¼�˷��ʹ��Ľڵ�����
     *           solutionPath��¼�˽�·����
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     * @return �����ɹ�����true,ʧ�ܷ���false
     */
    public final boolean ASearch(JigsawNode bNode, JigsawNode eNode) {

        this.visitedList = new HashSet<>(1000);
        this.exploreList = new PriorityQueue<>(500, new Comparator<JigsawNode>() {
            @Override
            public int compare(JigsawNode a, JigsawNode b) {
                if (a.getEstimatedValue() < b.getEstimatedValue()) {
                    return -1;
                } else if (a.getEstimatedValue() > b.getEstimatedValue()) {
                    return 1;
                } else if (a.getNodeDepth() < b.getNodeDepth()) {
                    return -1;
                } else if (a.getNodeDepth() > b.getNodeDepth()) {
                    return 1;
                }
                return 0;
            }
        });

        this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;

        // ���ʽڵ�������29000������Ϊ����ʧ��
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;

        // ���������
        this.searchedNodesNum = 0;
        this.solutionPath = null;

        // (1)����ʼ�ڵ����exploreList��
        this.visitedList.add(this.beginJNode);
        this.exploreList.add(this.beginJNode);

        // (2) ���exploreListΪ�գ����߷��ʽڵ�������MAX_NODE_NUM����������ʧ�ܣ������޽�;����ѭ��ֱ�����ɹ�
        while (this.searchedNodesNum < MAX_NODE_NUM && !this.exploreList.isEmpty()) {
            this.searchedNodesNum++;

            // (2-1)ȡ��exploreList�ĵ�һ���ڵ�N����Ϊ��ǰ�ڵ�currentJNode
            //      ��currentJNodeΪĿ��ڵ㣬�������ɹ��������·�����˳�
            this.currentJNode = this.exploreList.poll();
            if (this.currentJNode.equals(eNode)) {
                this.getPath();
                break;
            }

            // ��¼����ʾ��������
            // System.out.println("Searching.....Number of searched nodes:" + searchedNodesNum +
            //     "    Est:" + this.currentJNode.getEstimatedValue() +
            //     "    Current state:" + this.currentJNode.toString());

            JigsawNode[] nextNodes = new JigsawNode[]{
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            // (2-2)Ѱ��������currentJNode�ڽ���δ�������ֵĽڵ㣬�����ǰ����۹�ֵ��С�����������exploreList��
            //         ������visitedList�У���ʾ�ѷ���
            for (int i = 0; i < DIRS; i++) {
                if (nextNodes[i].move(i) && !this.visitedList.contains(nextNodes[i])) {
                    JigsawNode tempJNode = new JigsawNode(nextNodes[i]);
                    this.estimateValue(tempJNode);
                    nextNodes[i].setEstimatedValue(tempJNode.getEstimatedValue());
                    this.visitedList.add(nextNodes[i]);
                    this.exploreList.add(nextNodes[i]);
                }
            }
        }

        System.out.println("Jigsaw AStar Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        // System.out.println("Solution Path: ");
        // System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return this.isCompleted();
    }

    /**
     *��Demo+ʵ��������㲢�޸�״̬�ڵ�jNode�Ĵ��۹���ֵ:f(n)��
     * �� f(n) = s(n). s(n)��������ڵ㲻��ȷ���������
     * @param jNode - Ҫ������۹���ֵ�Ľڵ㣻�˺�����ı�ýڵ��estimatedValue����ֵ��
     */
    public abstract void estimateValue(JigsawNode jNode);

}
