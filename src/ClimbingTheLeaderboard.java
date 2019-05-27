import java.util.Scanner;

public class ClimbingTheLeaderboard {


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            System.out.print(String.valueOf(result[i]));

            if (i != result.length - 1) {
            	System.out.print("\n");
            }
        }

        scanner.close();
    }
    private static int[] climbingLeaderboard(int[] scores, int[] alice) {

    int[] aliceRank = new int[alice.length];
    int rank[]=new int [scores.length];
    rank[0]=1;
    for (int i=1;i<scores.length;i++)
    {
        if (scores[i] == scores[i-1])
        {rank[i]=rank[i-1];}
        else
        {
            rank[i]= rank[i-1]+1;
        }
    }
    
    for (int j=0;j<alice.length;j++)
    {
        int aliceScore = alice[j];
        // If Alice's score less than the least score in the Score panel
        if (aliceScore < scores[scores.length - 1])
        {
            aliceRank[j] = rank[scores.length-1] +1;
        }
        // If Alice's score is greater than the first score of the score panel
        else if (aliceScore > scores[0])
        {
            aliceRank[j] = 1;
        }
        else
        {
            int index=binarySearch(scores,aliceScore);
            aliceRank[j]= rank[index];
        }
        
    }
    
           return aliceRank;
}

private static int binarySearch(int[] scores, int target)
{
    int low = 0;
    int high = scores.length-1;

    while ( low <= high)
    {
        int mid = low + (high - low) /2;
        if (scores[mid] == target)
        {
            return mid;
        }
        else if (scores[mid] < target && target < scores[mid-1])
        {
            return mid;
        }
        else if (scores[mid] > target && target > scores[mid+1])
        {
            return mid + 1;
        }
        else if (scores[mid]  < target)
        {
            high = mid - 1;
        }
        else if (scores[mid] > target)
        {
            low = mid + 1;
        }
    }
    
    return -1;
}

}
