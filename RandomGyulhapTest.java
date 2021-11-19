import java.util.ArrayList;
import java.util.Random;

public class RandomGyulhapTest {
	public static void main(String[] args) {
		int hapSum = 0;
		double measure = 0.0;
		int gyul = 0;
		while (true) {
			int count = 0;
			ArrayList<Integer> background = new ArrayList<>();
			ArrayList<Integer> shape = new ArrayList<>();
			ArrayList<Integer> color = new ArrayList<>();
			Random rd = new Random();
			//�׸� �Ӽ�3���� 9���� ����
			while (count < 9) {
				/* �ߺ� ��� �׸� ����
				 background.add(rd.nextInt(3)); 
				 shape.add(rd.nextInt(3));
				 color.add(rd.nextInt(3));
				 */
				
				//�ߺ� ���X �׸� ����
				if (count == 0) {
					background.add(rd.nextInt(3));
					shape.add(rd.nextInt(3));
					color.add(rd.nextInt(3));
					System.out.printf("%d ", count);
					count++;
				} 
				else {	//3���� �Ӽ��� ��� ���� �׸��� �ִ��� Ȯ��
					int b = rd.nextInt(3), s = rd.nextInt(3), c = rd.nextInt(3);
					boolean duplicate = false;
					for (int check = 0; check < count; check++) {
						if (background.get(check) == b && shape.get(check) == s && color.get(check) == c) {
							duplicate = true;
						}
					}
					if (!duplicate) {
						background.add(b);
						shape.add(s);
						color.add(c);
						System.out.printf("%d ", count);
						count++;
					}
				}

			}
			System.out.println();
			// �׸� �Ӽ��� ���
			for (int irr : background) {
				System.out.printf("%d ", irr);
			}
			System.out.println();
			for (int irr : shape) {
				System.out.printf("%d ", irr);
			}
			System.out.println();
			for (int irr : color) {
				System.out.printf("%d ", irr);
			}
			System.out.println();
			
			//�׸��� �Ӽ��� �� ���ų� �� �ٸ��� ������ �� 3�� ��� 
			int hap = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = i + 1; j < 9; j++) {
					for (int k = j + 1; k < 9; k++) {
						if ((background.get(i) + background.get(j) + background.get(k)) % 3 == 0) {
							if ((shape.get(i) + shape.get(j) + shape.get(k)) % 3 == 0) {
								if ((color.get(i) + color.get(j) + color.get(k)) % 3 == 0) {
									System.out.printf("(%d, %d, %d)\n", i, j, k);
									hap++;
								}
							}

						}
					}
				}
			}
			//���� ���� ��� ���� ī��Ʈ
			if (hap == 0) {
				System.out.println("��");
				gyul++;
			} else {
				System.out.printf("���� ���� : %d\n", hap);
			}
			// ���Ǵ� ���� ���� ������ ī��Ʈ
			hapSum += hap;
			measure += 1.0;
			// measure������ �� �� �ݺ����� ����
			if (measure == 10000)
				break;
		}
		// �ݺ��� ������ ���� �� �Ǵ� ���� ������ ��հ� ���� �� ��� ���Դ���, Ȯ���� ���ۼ�Ʈ���� ���
		System.out.printf("�� ��� = %d / %f =  %.2f\n", hapSum, measure, hapSum / measure);
		System.out.printf("�� ���� Ƚ�� : %d (%.2f)\n", gyul, (gyul / measure) * 100);
	}
}
