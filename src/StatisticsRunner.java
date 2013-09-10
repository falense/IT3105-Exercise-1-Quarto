import java.util.ArrayList;

import players.ai.NoviceAI;
import players.ai.RandomAI;


public class StatisticsRunner {
	public static void main(String[] args)
    {
		int numMatches = 10;

		ArrayList<GM> gameMasters = new ArrayList<GM>();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		RandomAI rAI = new RandomAI();
		NoviceAI nAI = new NoviceAI();
		for (int i = 0; i < numMatches; i++){
			System.out.println("Starting first");
			GM g = new GM(true,false,0,rAI,nAI);
			Thread t = new Thread(g, "Quarto " + i);
			t.start();
			
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
}
