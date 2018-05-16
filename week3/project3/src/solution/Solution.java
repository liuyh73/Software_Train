package solution;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import jigsaw.Jigsaw;
import jigsaw.JigsawNode;

/**
 * �ڴ���������㷨�������ƴͼ��Ϸ��N-�������⣩
 */
public class Solution extends Jigsaw {

    private int searchedNodesNum;           // �ѷ��ʽڵ��������Լ�¼���з��ʹ��Ľڵ������

    private Queue<JigsawNode> exploreList;  // ���Ա����ѷ��ֵ�δ���ʵĽڵ�
    private Set<JigsawNode> visitedList;    // ���Ա����ѷ��ֵĽڵ�
    /**
     * ƴͼ���캯��
     */
    public Solution() {
    	this.searchedNodesNum=0;
    	this.exploreList=null;
    	this.visitedList=null;
    }

    /**
     * ƴͼ���캯��
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *��ʵ��һ��������������㷨����ָ��5*5ƴͼ��24-�������⣩�����Ž�
     * ���˺���������Solution���������������������
     * @param bNode - ��ʼ״̬�ڵ�
     * @param eNode - Ŀ��״̬�ڵ�
     * @return �����ɹ�ʱΪtrue,ʧ��Ϊfalse
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	this.visitedList=new HashSet<>(1000);
    	this.exploreList = new LinkedList<>();
    	this.beginJNode = new JigsawNode(bNode);
        this.endJNode = new JigsawNode(eNode);
        this.currentJNode = null;
        // ���ʽڵ�������29000������Ϊ����ʧ��
        final int MAX_NODE_NUM = 29000;
        final int DIRS = 4;

        // ���������
        this.searchedNodesNum = 0;

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
                    this.visitedList.add(nextNodes[i]);
                    this.exploreList.add(nextNodes[i]);
                }
            }
        }
        return this.isCompleted();
    }


    /**
     *��Demo+ʵ��������㲢�޸�״̬�ڵ�jNode�Ĵ��۹���ֵ:f(n)
     * �� f(n) = s(n). s(n)��������ڵ㲻��ȷ���������
     * �˺�����ı�ýڵ��estimatedValue����ֵ
     * �޸Ĵ˺���������Solution���������������������
     * @param jNode - Ҫ������۹���ֵ�Ľڵ�
     */
    public void estimateValue(JigsawNode jNode) {
        int s = 0; // �����ڵ㲻��ȷ���������
        int dimension = JigsawNode.getDimension();
        double manhatten=0, geometric=0;
        for (int index = 1; index < dimension * dimension; index++) {
            if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
                s++;
            }
        }
        
        for(int i=1;i<jNode.getNodesState().length;i++) {
        	int curR=(i-1)/dimension+1;
        	int curC=i%dimension==0 ? dimension : i%dimension;
        	int desR=(jNode.getNodesState()[i]-1)/dimension+1;
        	int desC=jNode.getNodesState()[i]%dimension==0 ? dimension : jNode.getNodesState()[i]%dimension;
        	double dR=Math.abs(curR-desR);
        	double dC=Math.abs(curC-desC);
        	manhatten+=dR+dC;
        	geometric+=Math.sqrt(dR*dR+dC*dC);
        }
        s=(int)((s+manhatten+geometric));
        jNode.setEstimatedValue(s);
    }
}
