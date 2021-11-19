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
			//그림 속성3가지 9개씩 생성
			while (count < 9) {
				/* 중복 허용 그림 생성
				 background.add(rd.nextInt(3)); 
				 shape.add(rd.nextInt(3));
				 color.add(rd.nextInt(3));
				 */
				
				//중복 허용X 그림 생성
				if (count == 0) {
					background.add(rd.nextInt(3));
					shape.add(rd.nextInt(3));
					color.add(rd.nextInt(3));
					System.out.printf("%d ", count);
					count++;
				} 
				else {	//3가지 속성이 모두 같은 그림이 있는지 확인
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
			// 그림 속성들 출력
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
			
			//그림의 속성이 다 같거나 다 다르면 합했을 때 3의 배수 
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
			//합이 없는 경우 결을 카운트
			if (hap == 0) {
				System.out.println("결");
				gyul++;
			} else {
				System.out.printf("합의 개수 : %d\n", hap);
			}
			// 한판당 나온 합의 개수를 카운트
			hapSum += hap;
			measure += 1.0;
			// measure변수로 몇 번 반복할지 결정
			if (measure == 10000)
				break;
		}
		// 반복이 끝나면 결이 한 판당 합의 개수의 평균과 결이 총 몇번 나왔는지, 확률은 몇퍼센트인지 출력
		System.out.printf("합 평균 = %d / %f =  %.2f\n", hapSum, measure, hapSum / measure);
		System.out.printf("결 나온 횟수 : %d (%.2f)\n", gyul, (gyul / measure) * 100);
	}
}
