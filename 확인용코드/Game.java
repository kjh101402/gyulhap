import java.util.*;

public class Game {
	public static void main(String[] args) {
		for(int i = 0; i < 9; i++) {
			System.out.printf("%d ", i);
		}
		System.out.println();
		System.out.println("=======================================");
		
		Gyulhap game = new Gyulhap();
		
		for (int irr : game.background) {
			System.out.printf("%d ", irr);
		}
		System.out.println();
		for (int irr : game.color) {
			System.out.printf("%d ", irr);
		}
		System.out.println();
		for (int irr : game.shape) {
			System.out.printf("%d ", irr);
		}
		System.out.println();
		game.printAnswer();
	}
	
}
// 결합 기본 클래스
class Gyulhap{
	final ArrayList<Integer> background;
	final ArrayList<Integer> color;
	final ArrayList<Integer> shape;
	private ArrayList<Set<Integer>> answers = new ArrayList<>();
	
	//합 목록 출력 함수
	public void printAnswer() {
		for(Set<Integer> irr : answers) {
			System.out.println(irr.toString());
		}
	}
	
	//생성자
	public Gyulhap() {
		ArrayList<ArrayList<Integer>> attrs = makeRandomList();
		this.background = attrs.get(0);
		this.color = attrs.get(1);
		this.shape = attrs.get(2);
		this.answers = makeAnswers(this.background, this.color, this.shape);
		
	}
	
	//속성 생성 함수
	private ArrayList<ArrayList<Integer>> makeRandomList(){
		ArrayList<ArrayList<Integer>> attr = new ArrayList<>();
		ArrayList<Integer> back = new ArrayList<>();;
		ArrayList<Integer> col = new ArrayList<>();;
		ArrayList<Integer> shp = new ArrayList<>();;
		Random rd = new Random();
		int count = 0;
		
		while(count < 9) {
			if(count == 0) {
				back.add(rd.nextInt(3));
				shp.add(rd.nextInt(3));
				col.add(rd.nextInt(3));
				count++;
			}
			else {
				int b = rd.nextInt(3), s = rd.nextInt(3), c = rd.nextInt(3);
				boolean duplicate = false;
				for(int check = 0; check < count; check++) {
					if(back.get(check) == b && col.get(check) == c && shp.get(check) == s) {
						duplicate = true;
						break;
					}
				}
				if(!duplicate) {
					back.add(b);
					shp.add(s);
					col.add(c);
					count++;
				}
			}
		}
		
		attr.add(back);
		attr.add(col);
		attr.add(shp);
		return attr;
	}
	
	//만들어진 속성을 바탕으로 합을 찾아내는 함수
	private ArrayList<Set<Integer>> makeAnswers(ArrayList<Integer> back, ArrayList<Integer> col, ArrayList<Integer> shp){
		ArrayList<Set<Integer>> answers = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			for(int j = i + 1; j < 9; j++) {
				for(int k = j + 1; k < 9; k++) {
					if((back.get(i) + back.get(j) + back.get(k)) % 3 == 0) {
						if((col.get(i) + col.get(j) + col.get(k)) % 3 == 0) {
							if((shp.get(i) + shp.get(j) + shp.get(k)) % 3 == 0) {
								Set<Integer> answer = new TreeSet<>();
								answer.add(i);
								answer.add(j);
								answer.add(k);
								answers.add(answer);
							}
						}
					}
				}
			}
		}
		return answers;
	}
}