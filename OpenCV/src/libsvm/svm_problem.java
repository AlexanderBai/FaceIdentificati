package libsvm;
public class svm_problem implements java.io.Serializable
{
	/**
	 * ѵ�������У������ı�ǩ������l��ѵ������
	 */
	public int l;
	/**
	 * ѵ��������Ŀ�����Y
	 */
	public double[] y;
	/**
	 * ѵ���������Ա���X
	 */
	public svm_node[][] x;
}
