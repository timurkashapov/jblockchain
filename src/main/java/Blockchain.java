import java.util.ArrayList;
import com.google.gson.GsonBuilder;


/**
 * Application.
 *
 *
 *
 *
 */
public class Blockchain {

    /**
     * Basic blockchain implementation.
     */
    private static ArrayList<Block> blockchain = new ArrayList<>();

    /**
     * Difficulty of blockchain.
     */
    public static int difficulty = 7;

    public static void main(String[] args) {
        
        Blockchain.blockchain.add(new Block("One Block", "0"));
        System.out.println("\nTrying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);
        for (int i = 2; i < 12 ; ++i) {
            Blockchain.blockchain.add(new Block("Two Block", blockchain.get(blockchain.size() - 1).getHash()));
            System.out.println("\nTrying to Mine block " + i + "... ");
            blockchain.get(i - 1).mineBlock(difficulty);
        }
        System.out.println("\n\nBlockchain is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\n\nThe blockchain:");
        System.out.println(blockchainJson);
    }

    /**
     * Check for valid chain.
     * @return approval.
     */
    private static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); ++i) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if ( ! currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }
            if ( ! previousBlock.getHash().equals(previousBlock.calculateHash())) {
                System.out.println("Previous hashes not equal");
                return false;
            }

            if ( ! currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block has not been mined.");
                return false;
            }
        }

        return true;
    }
}
