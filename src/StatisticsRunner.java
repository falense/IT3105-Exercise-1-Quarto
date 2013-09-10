import java.util.ArrayList;

import players.BasePlayer;
import players.ai.NoviceAI;
import players.ai.RandomAI;
import players.ai.RecursiveAI;


public class StatisticsRunner {
	BasePlayer p1, p2;
	int numMatches;
	public StatisticsRunner(BasePlayer p1, BasePlayer p2, int numMatches){
		this.p1 = p1;
		this.p2 = p2;
		this.numMatches = numMatches;
	}
	public void run(){
		ArrayList<GM> gameMasters = new ArrayList<GM>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < numMatches; i++){
			GM g = new GM(false,false,0,p1,p2);
			Thread t = new Thread(g, "Quarto " + i);
			t.start();
			gameMasters.add(g);
			threads.add(t);
		}

		int results[] = new int[3];
		for (int i = 0; i < 3; i++) results[i] = 0;
		for (int i = 0; i < numMatches; i++){
			
			try {
				threads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int index = gameMasters.get(i).winner;
			results[index]++;
		}
		System.out.println(numMatches + " was played, the results are:");
		System.out.println("Player 1 won: " + results[1]);
		System.out.println("Player 2 won: " + results[2]);
		System.out.println("Draws: " + results[0] );
		
	}
	public static void main(String[] args)
    {
		StatisticsRunner s = new StatisticsRunner(new RecursiveAI(false,1), new NoviceAI(false), 100);
		s.run();

		
	}
}
