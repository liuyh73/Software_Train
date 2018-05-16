package Runners;

import java.io.IOException;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import solution.Solution;

public class RunnerDemo {

    /**
     * ��ʾ�ű�������ʽ����-ʾ���㷨-������3*3ƴͼ��8-�������⣩
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // ���ڵ�ά���Ƿ�Ϊ3
        if (JigsawNode.getDimension() != 3) {
            System.out.print("�ڵ�ά������ȷ���뽫JigsawNode���ά��dimension��Ϊ3");
            return;
        }

        // ����Ŀ��״̬����destNode: {9,1,2,3,4,5,6,7,8,0}
        JigsawNode destNode = new JigsawNode(new int[]{9,1,2,3,4,5,6,7,8,0});

        // ���������ʼ״̬����startNode����Ŀ��״̬��ɢ�����ɿɽ�������ʼ״̬
        // JigsawNode startNode = Jigsaw.scatter(destNode, 1000);
        JigsawNode startNode = new JigsawNode(new int[]{5,1,5,2,7,0,4,6,3,8});
        // ����jigsaw�������ó�ʼ״̬�ڵ�startNode��Ŀ��״̬�ڵ�destNode
        Jigsaw jigsaw = new Solution();

        // ִ������ʽ����ʾ���㷨
        jigsaw.ASearch(startNode, destNode);
    }

}
